package ch.ethz.infsec;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class CsvReplayer {
    static abstract class OutputItem {
        final long emissionTime;
        boolean isLast = false;

        OutputItem(long emissionTime) {
            this.emissionTime = emissionTime;
        }

        abstract void write(Writer writer) throws IOException;

        abstract void reportDelivery(Reporter reporter, long startTime);
    }

    static abstract class DatabaseBuffer extends OutputItem {
        DatabaseBuffer(long emissionTime) {
            super(emissionTime);
        }

        abstract void addEvent(String csvLine, String relation, int indexBeforeData);

        abstract int getNumberOfEvents();

        @Override
        void reportDelivery(Reporter reporter, long startTime) {
            reporter.reportDelivery(this, startTime);
        }
    }

    static final class MonpolyDatabaseBuffer extends DatabaseBuffer {
        private final long timestamp;
        private final Map<String, StringBuilder> relations = new HashMap<>();
        private int numberOfEvents = 0;

        MonpolyDatabaseBuffer(long timestamp, long emissionTime) {
            super(emissionTime);
            this.timestamp = timestamp;
        }

        @Override
        void addEvent(String csvLine, String relation, int indexBeforeData) {
            ++numberOfEvents;

            StringBuilder relationBuilder = relations.get(relation);
            if (relationBuilder == null) {
                relationBuilder = new StringBuilder();
                relations.put(relation, relationBuilder);
            }

            relationBuilder.append('(');
            int currentIndex = indexBeforeData;
            while (0 <= currentIndex && currentIndex < csvLine.length()) {
                currentIndex = csvLine.indexOf('=', currentIndex);
                if (currentIndex >= 0) {
                    currentIndex += 1;
                    while (currentIndex < csvLine.length() && Character.isSpaceChar(csvLine.charAt(currentIndex)))
                        currentIndex += 1;
                    int startIndex = currentIndex;
                    currentIndex = csvLine.indexOf(',', startIndex);
                    boolean more = currentIndex >= 0;
                    if (!more)
                        currentIndex = csvLine.length();
                    relationBuilder.append(csvLine.substring(startIndex, currentIndex).trim());
                    if (more)
                        relationBuilder.append(',');
                    currentIndex += 1;
                }
            }
            relationBuilder.append(')');
        }

        @Override
        int getNumberOfEvents() {
            return numberOfEvents;
        }

        @Override
        void write(Writer writer) throws IOException {
            writer.append('@').append(Long.toString(timestamp));
            for (HashMap.Entry<String, StringBuilder> entry : relations.entrySet()) {
                writer.append(' ').append(entry.getKey()).append(entry.getValue());
            }
            writer.append('\n');
        }
    }

    static final class CsvDatabaseBuffer extends DatabaseBuffer {
        private final ArrayList<String> lines = new ArrayList<>();

        CsvDatabaseBuffer(long emissionTime) {
            super(emissionTime);
        }

        @Override
        void addEvent(String csvLine, String relation, int indexBeforeData) {
            lines.add(csvLine);
        }

        @Override
        int getNumberOfEvents() {
            return lines.size();
        }

        @Override
        void write(Writer writer) throws IOException {
            for (String line : lines) {
                writer.append(line).append('\n');
            }
        }
    }

    static final class TimestampItem extends OutputItem {
        static final String TIMESTAMP_PREFIX = "###";

        private final String line;

        TimestampItem(long emissionTime, long startTimestamp) {
            super(emissionTime);

            long timestamp = startTimestamp + emissionTime;
            this.line = TIMESTAMP_PREFIX + Long.toString(timestamp) + "\n";
        }

        @Override
        public void write(Writer writer) throws IOException {
            writer.write(line);
        }

        @Override
        void reportDelivery(Reporter reporter, long startTime) {
        }
    }

    interface DatabaseBufferFactory {
        DatabaseBuffer createDatabaseBuffer(long timestamp, long emissionTime);
    }

    static final class MonpolyDatabaseBufferFactory implements DatabaseBufferFactory {
        @Override
        public DatabaseBuffer createDatabaseBuffer(long timestamp, long emissionTime) {
            return new MonpolyDatabaseBuffer(timestamp, emissionTime);
        }
    }

    static final class CsvDatabaseBufferFactory implements DatabaseBufferFactory {
        @Override
        public DatabaseBuffer createDatabaseBuffer(long timestamp, long emissionTime) {
            return new CsvDatabaseBuffer(emissionTime);
        }
    }

    static class InputWorker implements Runnable {
        private final BufferedReader input;
        private final double timeMultiplier;
        private final long timestampInterval;
        private final DatabaseBufferFactory factory;
        private final LinkedBlockingQueue<OutputItem> queue;

        private boolean successful = false;

        private DatabaseBuffer databaseBuffer = null;
        private long firstTimestamp;
        private long currentTimepoint;

        private long absoluteStartMillis = -1;
        private long nextTimestampToEmit;

        InputWorker(BufferedReader input, double timeMultiplier, long timestampInterval, DatabaseBufferFactory factory, LinkedBlockingQueue<OutputItem> queue) {
            this.input = input;
            this.timeMultiplier = timeMultiplier;
            this.timestampInterval = timestampInterval;
            this.factory = factory;
            this.queue = queue;
        }

        public void run() {
            try {
                String line;
                while ((line = input.readLine()) != null) {
                    processRecord(line);
                }

                if (databaseBuffer == null) {
                    databaseBuffer = factory.createDatabaseBuffer(0, -1);
                }
                emitBuffer(databaseBuffer, true);

                successful = true;
            } catch (IOException e) {
                e.printStackTrace(System.err);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        boolean isSuccessful() {
            return successful;
        }

        private void processRecord(String line) throws InterruptedException {
            String relation;
            long timepoint;
            long timestamp;

            int startIndex;
            int currentIndex = 0;

            while (Character.isSpaceChar(line.charAt(currentIndex))) currentIndex += 1;
            startIndex = currentIndex;
            currentIndex = line.indexOf(',', startIndex);
            relation = line.substring(startIndex, currentIndex).trim();
            currentIndex += 1;

            currentIndex = line.indexOf('=', currentIndex) + 1;
            while (Character.isSpaceChar(line.charAt(currentIndex))) currentIndex += 1;
            startIndex = currentIndex;
            currentIndex = line.indexOf(',', startIndex);
            timepoint = Long.valueOf(line.substring(startIndex, currentIndex).trim());
            currentIndex += 1;

            currentIndex = line.indexOf('=', currentIndex) + 1;
            while (Character.isSpaceChar(line.charAt(currentIndex))) currentIndex += 1;
            startIndex = currentIndex;
            currentIndex = line.indexOf(',', startIndex);
            if (currentIndex < 0)
                currentIndex = line.length();
            timestamp = Long.valueOf(line.substring(startIndex, currentIndex).trim());
            currentIndex += 1;

            if (databaseBuffer == null) {
                databaseBuffer = factory.createDatabaseBuffer(timestamp, 0);
                firstTimestamp = timestamp;
                currentTimepoint = timepoint;
            } else if (timepoint != currentTimepoint) {
                emitBuffer(databaseBuffer, false);

                long nextEmission;
                if (timeMultiplier > 0.0) {
                    nextEmission = Math.round((double) (timestamp - firstTimestamp) / timeMultiplier * 1000.0);
                } else {
                    nextEmission = 0;
                }
                databaseBuffer = factory.createDatabaseBuffer(timestamp, nextEmission);
                currentTimepoint = timepoint;
            }

            databaseBuffer.addEvent(line, relation, currentIndex);
        }

        private void emitBuffer(DatabaseBuffer databaseBuffer, boolean isLast) throws InterruptedException {
            long emissionTime = databaseBuffer.emissionTime;

            if (timestampInterval > 0) {
                if (absoluteStartMillis < 0) {
                    absoluteStartMillis = System.currentTimeMillis();
                    nextTimestampToEmit = 0;
                }

                while (nextTimestampToEmit < emissionTime) {
                    queue.put(new TimestampItem(nextTimestampToEmit, absoluteStartMillis));
                    nextTimestampToEmit += timestampInterval;
                }
            } else {
                if (isLast) {
                    databaseBuffer.isLast = true;
                }
            }

            queue.put(databaseBuffer);

            if (timestampInterval > 0 && isLast) {
                TimestampItem item = new TimestampItem(emissionTime, absoluteStartMillis);
                item.isLast = true;
                queue.put(item);
            }
        }
    }

    interface Reporter extends Runnable {
        void reportUnderrun();

        void reportDelivery(DatabaseBuffer databaseBuffer, long startTime);
    }

    static class NullReporter implements Reporter {
        @Override
        public void reportUnderrun() {
        }

        @Override
        public void reportDelivery(DatabaseBuffer databaseBuffer, long startTime) {
        }

        @Override
        public void run() {
        }
    }

    static class IntervalReporter implements Reporter {
        static final long INTERVAL_MILLIS = 1000L;

        private final boolean verbose;

        private volatile boolean running = true;
        private long startTime;
        private long lastReport;

        private int underruns = 0;
        private int indices = 0;
        private int indicesSinceLastReport = 0;
        private int totalEvents = 0;
        private int eventsSinceLastReport = 0;
        private long currentDelay = 0;
        private long delaySum = 0;
        private long maxDelay = 0;
        private long maxDelaySinceLastReport = 0;

        IntervalReporter(boolean verbose) {
            this.verbose = verbose;
        }

        @Override
        public synchronized void reportUnderrun() {
            ++underruns;
        }

        @Override
        public synchronized void reportDelivery(DatabaseBuffer databaseBuffer, long startTime) {
            long now = System.nanoTime();

            ++indices;
            ++indicesSinceLastReport;

            int events = databaseBuffer.getNumberOfEvents();
            totalEvents += events;
            eventsSinceLastReport += events;

            long elapsedMillis = (now - startTime) / 1_000_000L;
            currentDelay = Math.max(0, elapsedMillis - databaseBuffer.emissionTime);
            delaySum += currentDelay;
            maxDelay = Math.max(maxDelay, currentDelay);
            maxDelaySinceLastReport = Math.max(maxDelaySinceLastReport, currentDelay);

            if (databaseBuffer.isLast) {
                doReport();
                running = false;
            }
        }

        private synchronized void doReport() {
            if (!running) return;

            long now = System.nanoTime();

            double totalSeconds = (double) (now - startTime) / 1e9;
            double deltaSeconds = (double) (now - lastReport) / 1e9;

            double indexRate = (double) indicesSinceLastReport / deltaSeconds;
            double eventRate = (double) eventsSinceLastReport / deltaSeconds;
            double delaySeconds = (double) currentDelay / 1000.0;
            double totalAverageDelaySeconds = (double) delaySum / ((double) indices * 1000.0);
            double maxDelaySeconds = (double) maxDelay / 1000.0;
            double currentMaxDelaySeconds = (double) maxDelaySinceLastReport / 1000.0;

            if (verbose) {
                System.err.printf(
                        "%5.1fs: %8.1f indices/s, %8.1f events/s, %6.3fs delay, %6.3fs peak delay, %6.3fs max. delay, %6.3fs avg. delay, %9d indices, %9d events, %6d underruns\n",
                        totalSeconds, indexRate, eventRate, delaySeconds, currentMaxDelaySeconds, maxDelaySeconds, totalAverageDelaySeconds, indices, totalEvents, underruns);
            } else {
                System.err.printf("%5.1f   %8.1f %8.1f   %6.3f %6.3f %6.3f %6.3f\n",
                        totalSeconds, indexRate, eventRate, delaySeconds, currentMaxDelaySeconds, maxDelaySeconds, totalAverageDelaySeconds);
            }

            indicesSinceLastReport = 0;
            eventsSinceLastReport = 0;
            currentDelay = 0;
            maxDelaySinceLastReport = 0;

            lastReport = now;
        }

        @Override
        public void run() {
            try {
                long schedule;
                synchronized (this) {
                    startTime = System.nanoTime();
                    lastReport = startTime;
                    schedule = startTime;
                }

                while (running) {
                    schedule += INTERVAL_MILLIS * 1_000_000L;
                    long now = System.nanoTime();
                    long waitMillis = Math.max(0L, (schedule - now) / 1_000_000L);
                    Thread.sleep(waitMillis);
                    doReport();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class OutputWorker implements Runnable {
        private final BufferedWriter output;
        private final LinkedBlockingQueue<OutputItem> queue;
        private final Reporter reporter;
        private final Thread inputThread;

        private boolean successful = false;

        OutputWorker(BufferedWriter output, LinkedBlockingQueue<OutputItem> queue, Reporter reporter, Thread inputThread) {
            this.output = output;
            this.queue = queue;
            this.reporter = reporter;
            this.inputThread = inputThread;
        }

        public void run() {
            try {
                long startTime = 0L;
                boolean isFirst = true;
                OutputItem outputItem;
                do {
                    if (isFirst) {
                        outputItem = queue.take();
                        startTime = System.nanoTime();
                        isFirst = false;
                    } else {
                        outputItem = queue.poll();
                        if (outputItem == null) {
                            reporter.reportUnderrun();
                            outputItem = queue.take();
                        }
                    }

                    long now = System.nanoTime();
                    long elapsedMillis = (now - startTime) / 1_000_000L;
                    long waitMillis = outputItem.emissionTime - elapsedMillis;
                    if (waitMillis > 1L) {
                        Thread.sleep(waitMillis);
                    }

                    outputItem.write(output);
                    output.flush();

                    outputItem.reportDelivery(reporter, startTime);
                } while (!outputItem.isLast);

                successful = true;
            } catch (IOException e) {
                e.printStackTrace(System.err);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (!successful) {
                inputThread.interrupt();
            }
        }

        boolean isSuccessful() {
            return successful;
        }
    }

    private static void invalidArgument() {
        System.err.print("Error: Invalid argument.\n" +
                "Usage: [-v|-vv] [-a <acceleration>] [-q <buffer size>] [-m] [-t <interval>] [-o <host>:<port>] [<file>]\n");
        System.exit(1);
    }

    public static void main(String[] args) {
        String inputFilename = null;
        double timeMultiplier = 1.0;
        DatabaseBufferFactory databaseBufferFactory = new CsvDatabaseBufferFactory();
        long timestampInterval = -1;
        String outputHost = null;
        int outputPort = 0;
        Reporter reporter = new NullReporter();
        int queueCapacity = 1024;

        try {
            for (int i = 0; i < args.length; ++i) {
                switch (args[i]) {
                    case "-v":
                        reporter = new IntervalReporter(false);
                        break;
                    case "-vv":
                        reporter = new IntervalReporter(true);
                        break;
                    case "-a":
                        if (++i == args.length) {
                            invalidArgument();
                        }
                        timeMultiplier = Double.parseDouble(args[i]);
                        break;
                    case "-q":
                        if (++i == args.length) {
                            invalidArgument();
                        }
                        queueCapacity = Integer.parseInt(args[i]);
                        break;
                    case "-m":
                        databaseBufferFactory = new MonpolyDatabaseBufferFactory();
                        break;
                    case "-t":
                        if (++i == args.length) {
                            invalidArgument();
                        }
                        timestampInterval = Long.parseLong(args[i]);
                        break;
                    case "-o":
                        if (++i == args.length) {
                            invalidArgument();
                        }
                        String parts[] = args[i].split(":", 2);
                        if (parts.length != 2) {
                            invalidArgument();
                        }
                        outputHost = parts[0];
                        outputPort = Integer.parseInt(parts[1]);
                        break;
                    default:
                        if (args[i].startsWith("-") || inputFilename != null) {
                            invalidArgument();
                        }
                        inputFilename = args[i];
                        break;
                }
            }
        } catch (NumberFormatException e) {
            invalidArgument();
        }

        BufferedReader inputReader;
        if (inputFilename == null) {
            inputReader = new BufferedReader(new InputStreamReader(System.in));
        } else {
            try {
                inputReader = new BufferedReader(new FileReader(inputFilename));
            } catch (FileNotFoundException e) {
                System.err.print("Error: " + e.getMessage() + "\n");
                System.exit(1);
                return;
            }
        }

        BufferedWriter outputWriter;
        if (outputHost == null) {
            outputWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        } else {
            try {
                ServerSocket serverSocket = new ServerSocket(outputPort, 1, InetAddress.getByName(outputHost));
                Socket outputSocket = serverSocket.accept();
                System.err.printf("Client connected: %s\n", outputSocket.getInetAddress().getHostAddress());
                outputWriter = new BufferedWriter(new OutputStreamWriter(outputSocket.getOutputStream()));
            } catch (IOException e) {
                System.err.print("Error: " + e.getMessage() + "\n");
                System.exit(1);
                return;
            }
        }

        Thread reporterThread = new Thread(reporter);
        reporterThread.setDaemon(true);
        reporterThread.start();

        LinkedBlockingQueue<OutputItem> queue = new LinkedBlockingQueue<>(queueCapacity);
        InputWorker inputWorker = new InputWorker(inputReader, timeMultiplier, timestampInterval, databaseBufferFactory, queue);
        Thread inputThread = new Thread(inputWorker);
        inputThread.start();
        OutputWorker outputWorker = new OutputWorker(outputWriter, queue, reporter, inputThread);
        Thread outputThread = new Thread(outputWorker);
        outputThread.start();

        try {
            inputThread.join();
            if (!inputWorker.isSuccessful()) {
                outputThread.interrupt();
            }
            outputThread.join();
            reporterThread.join(1000);
        } catch (InterruptedException ignored) {
        }

        if (!(inputWorker.isSuccessful() && outputWorker.isSuccessful())) {
            System.exit(1);
        }
    }
}
