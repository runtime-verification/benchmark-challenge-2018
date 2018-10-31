package ch.ethz.infsec;

import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CsvStreamGenerator {
    private static void invalidArgument() {
        System.err.print("Error: Invalid argument.\n" +
                "Usage: {-S | -L | -T | -P <pattern>} [-e <event rate>] [-i <index rate>]\n" +
                "       [-t <timestamp>] [-x <violations>] [-w <window size>]\n" +
                "       [-pA <A ratio>] [-pB <B ratio>] [-z <Zipf exponents>] [<seconds>]\n");
        System.exit(1);
    }

    public static void main(String[] args) {
        EventPattern eventPattern = null;
        int eventRate = 10;
        int indexRate = 1;
        float relativeViolations = 0.01f;
        int windowSize = 10;
        float baseRatio = 0.33f;
        float positiveRatio = 0.33f;
        Map<String, Double> zipfExponents = new HashMap<>();
        Map<String, Integer> zipfOffsets = new HashMap<>();
        long firstTimestamp = 0;
        int streamLength = -1;
        String sigFilename = null;
        String formulaFilename = null;

        try {
            for (int i = 0; i < args.length; ++i) {
                switch (args[i]) {
                    case "-S":
                        eventPattern = new BasicEventPattern.Star();
                        break;
                    case "-L":
                        eventPattern = new BasicEventPattern.Linear();
                        break;
                    case "-T":
                        eventPattern = new BasicEventPattern.Triangle();
                        break;
                    case "-P":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        try {
                            eventPattern = CustomEventPattern.parse(args[++i]);
                        } catch (InvalidEventPatternException e) {
                            invalidArgument();
                        }
                        break;
                    case "-e":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        eventRate = Integer.parseInt(args[++i]);
                        break;
                    case "-i":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        indexRate = Integer.parseInt(args[++i]);
                        break;
                    case "-x":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        relativeViolations = Float.parseFloat(args[++i]);
                        break;
                    case "-w":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        windowSize = Integer.parseInt(args[++i]);
                        break;
                    case "-pA":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        baseRatio = Float.parseFloat(args[++i]);
                        break;
                    case "-pB":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        positiveRatio = Float.parseFloat(args[++i]);
                        break;
                    case "-z":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        String exponents[] = args[++i].split(",");
                        for (String exponent : exponents) {
                            String parts[] = exponent.split("=", 2);
                            if (parts.length != 2) {
                                invalidArgument();
                            }
                            if (parts[1].contains("+")) {
                                String subparts[] = parts[1].split("\\+", 2);
                                zipfExponents.put(parts[0], Double.parseDouble(subparts[0]));
                                zipfOffsets.put(parts[0], Integer.parseInt(subparts[1]));
                            } else {
                                zipfExponents.put(parts[0], Double.parseDouble(parts[1]));
                            }
                        }
                        break;
                    case "-t":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        firstTimestamp = Long.parseLong(args[++i]);
                        break;
                    case "-osig":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        sigFilename = args[++i];
                        break;
                    case "-oformula":
                        if (i + 1 == args.length) {
                            invalidArgument();
                        }
                        formulaFilename = args[++i];
                        break;
                    default:
                        if (streamLength >= 0) {
                            invalidArgument();
                        }
                        streamLength = Integer.parseInt(args[i]);
                        if (streamLength < 0) {
                            invalidArgument();
                        }
                }
            }
        } catch (NumberFormatException e) {
            invalidArgument();
        }
        if (eventPattern == null) {
            invalidArgument();
        }

        float violationProbability = relativeViolations / (float) eventRate;

        RandomGenerator random = new JDKRandomGenerator(314159265);
        PositiveNegativeGenerator generator = new PositiveNegativeGenerator(random, eventRate, indexRate, firstTimestamp, eventPattern);
        for (Map.Entry<String, Double> entry : zipfExponents.entrySet()) {
            if (entry.getValue() > 0.0) {
                generator.setZipfExponent(entry.getKey(), entry.getValue(), zipfOffsets.getOrDefault(entry.getKey(), 0));
            }
        }
        generator.setEventDistribution(baseRatio, positiveRatio, violationProbability);
        generator.setPositiveWindow(windowSize);
        generator.setNegativeWindow(windowSize);
        generator.initialize();

        if (sigFilename != null) {
            try (PrintWriter writer = new PrintWriter(sigFilename)) {
                writer.print(generator.getSignature());
            } catch (IOException e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        if (formulaFilename != null) {
            try (PrintWriter writer = new PrintWriter(formulaFilename)) {
                writer.println(generator.getFormula());
            } catch (IOException e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        if (sigFilename != null || formulaFilename != null) {
            return;
        }

        BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int numberOfIndices = streamLength * indexRate;

        try {
            if (streamLength >= 0) {
                for (int i = 0; i < numberOfIndices; ++i) {
                    outputWriter.write(generator.nextDatabase());
                }
            } else {
                while (true) {
                    outputWriter.write(generator.nextDatabase());
                }
            }
            outputWriter.flush();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
}
