package ch.ethz.infsec;

import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.distribution.ZipfDistribution;
import org.apache.commons.math3.random.RandomGenerator;

import java.util.*;

final class PositiveNegativeGenerator extends AbstractEventGenerator {
    private static final int NEGATIVE_SKEW_SHIFT = 1_000_000;

    private final EventPattern eventPattern;
    private final Map<String, Integer> variableMap;
    private final List<Integer> baseVariables;
    private final List<Integer> positiveVariables;
    private final List<Integer> negativeVariables;

    private final IntegerDistribution[] distributions;
    private final IntegerDistribution[] violationDistributions;
    private final int[] shifts;
    private final int[] negativeShifts;

    private float baseRatio = 0.33f;
    private float positiveRatio = 0.33f;
    private float violationProbability = 0.01f;

    private int positiveWindow = 100;
    private int negativeWindow = 100;

    private float baseProbability;
    private float positiveProbability;

    private static final class ScheduledData implements Comparable<ScheduledData> {
        final long timestamp;
        final int[] attributes;

        ScheduledData(long timestamp, int[] attributes) {
            this.timestamp = timestamp;
            this.attributes = attributes;
        }

        @Override
        public int compareTo(ScheduledData other) {
            return Long.compare(timestamp, other.timestamp);
        }
    }

    private final PriorityQueue<ScheduledData> positiveQueue = new PriorityQueue<>();
    private final PriorityQueue<ScheduledData> negativeQueue = new PriorityQueue<>();

    private static List<Integer> lookupVariables(Map<String, Integer> variableMap, List<String> variables) {
        List<Integer> indexes = new ArrayList<>(variables.size());
        for (String variable : variables) {
            indexes.add(variableMap.get(variable));
        }
        return indexes;
    }

    PositiveNegativeGenerator(RandomGenerator random, int eventRate, int indexRate, long firstTimestamp, EventPattern eventPattern) {
        super(random, eventRate, indexRate, firstTimestamp);

        this.eventPattern = eventPattern;
        variableMap = new HashMap<>();
        {
            int index = 0;
            for (String variable : eventPattern.getVariables()) {
                variableMap.put(variable, index++);
            }

            baseVariables = lookupVariables(variableMap, eventPattern.getArguments(eventPattern.getBaseEvent()));
            positiveVariables = lookupVariables(variableMap, eventPattern.getArguments(eventPattern.getPositiveEvent()));
            negativeVariables = lookupVariables(variableMap, eventPattern.getArguments(eventPattern.getNegativeEvent()));
        }

        final int variables = variableMap.size();
        distributions = new IntegerDistribution[variables];
        violationDistributions = new IntegerDistribution[variables];
        shifts = new int[variables];
        negativeShifts = new int[variables];
        for (int i = 0; i < variables; ++i) {
            distributions[i] = new UniformIntegerDistribution(random, 0, 999_999_999);
            violationDistributions[i] = distributions[i];
        }
    }

    void setZipfExponent(String variable, double exponent, int offset) {
        final int index = variableMap.get(variable);
        distributions[index] = new ZipfDistribution(random, 1_000_000_000, exponent);
        shifts[index] = offset;
        negativeShifts[index] = offset + NEGATIVE_SKEW_SHIFT;
    }

    void setEventDistribution(float baseRatio, float positiveRatio, float violationProbability) {
        if (baseRatio + positiveRatio > 1.0f) {
            throw new IllegalArgumentException("The sum of base and positive ratios cannot be larger than 1.");
        }
        if (baseRatio > positiveRatio) {
            throw new IllegalArgumentException("The base ratio cannot be larger than the positive ratio.");
        }
        if (violationProbability > baseRatio) {
            throw new IllegalArgumentException("The violation probability cannot be larger than the base ratio.");
        }
        if (violationProbability > 1.0f - baseRatio - positiveRatio) {
            throw new IllegalArgumentException("The violation probability cannot be larger than the negative ratio.");
        }

        this.baseRatio = baseRatio;
        this.positiveRatio = positiveRatio;
        this.violationProbability = violationProbability;
    }

    void setPositiveWindow(int window) {
        positiveWindow = window;
    }

    void setNegativeWindow(int window) {
        negativeWindow = window;
    }

    @Override
    void initialize() {
        baseProbability = (baseRatio - violationProbability) / (1.0f - violationProbability);
        positiveProbability = positiveRatio / (1.0f - baseRatio);
    }

    private boolean flipCoin(float p) {
        return random.nextFloat() < p;
    }

    private int[] nextValues() {
        final int[] values = new int[variableMap.size()];
        for (int i = 0; i < values.length; ++i) {
            values[i] = distributions[i].sample() + shifts[i];
        }
        return values;
    }

    private int[] nextValues(List<Integer> variables) {
        final int[] values = new int[variables.size()];
        final boolean negative = (variables == negativeVariables);
        for (int i = 0; i < values.length; ++i) {
            int j = variables.get(i);
            if (negative) {
                values[i] = distributions[j].sample() + negativeShifts[j];
            } else {
                values[i] = distributions[j].sample() + shifts[i];
            }
        }
        return values;
    }

    private int[] nextViolationValues() {
        final int[] values = new int[variableMap.size()];
        for (int i = 0; i < values.length; ++i) {
            values[i] = violationDistributions[i].sample();
        }
        return values;
    }

    private int[] selectAttributes(int[] values, List<Integer> variables) {
        final int[] selected = new int[variables.size()];
        for (int i = 0; i < variables.size(); ++i) {
            int j = variables.get(i);
            selected[i] = values[j];
        }
        return selected;
    }

    private void appendEvent(StringBuilder builder, String relation, int[] attributes) {
        appendEventStart(builder, relation);
        for (int i = 0; i < attributes.length; ++i) {
            appendAttribute(builder, "x" + i, attributes[i]);
        }
    }

    private void appendEventUsingSchedule(StringBuilder builder, long timestamp, String relation, List<Integer> variables, PriorityQueue<ScheduledData> queue) {
        int[] attributes;
        ScheduledData scheduledData = queue.peek();
        if (scheduledData != null && scheduledData.timestamp <= timestamp) {
            queue.remove();
            attributes = scheduledData.attributes;
        } else {
            attributes = nextValues(variables);
        }
        appendEvent(builder, relation, attributes);
    }

    @Override
    void appendNextEvent(StringBuilder builder, long timestamp) {
        if (flipCoin(violationProbability)) {
            int[] values = nextViolationValues();
            long positiveTimestamp = timestamp + random.nextInt(positiveWindow);
            long negativeTimestamp = positiveTimestamp + random.nextInt(negativeWindow);

            appendEvent(builder, eventPattern.getBaseEvent(), selectAttributes(values, baseVariables));
            positiveQueue.add(new ScheduledData(positiveTimestamp, selectAttributes(values, positiveVariables)));
            negativeQueue.add(new ScheduledData(negativeTimestamp, selectAttributes(values, negativeVariables)));

            return;
        }

        if (flipCoin(baseProbability)) {
            int[] values = nextValues();
            long positiveTimestamp = timestamp + random.nextInt(positiveWindow);

            appendEvent(builder, eventPattern.getBaseEvent(), selectAttributes(values, baseVariables));
            positiveQueue.add(new ScheduledData(positiveTimestamp, selectAttributes(values, positiveVariables)));

            return;
        }

        if (flipCoin(positiveProbability)) {
            appendEventUsingSchedule(builder, timestamp, eventPattern.getPositiveEvent(), positiveVariables, positiveQueue);
            return;
        }

        appendEventUsingSchedule(builder, timestamp, eventPattern.getNegativeEvent(), negativeVariables, negativeQueue);
    }

    String getSignature() {
        StringBuilder signature = new StringBuilder();
        for (String event : new String[] {eventPattern.getBaseEvent(), eventPattern.getPositiveEvent(), eventPattern.getNegativeEvent()}) {
            signature.append(event).append('(');
            final int arity = eventPattern.getArguments(event).size();
            for (int i = 0; i < arity; ++i) {
                if (i > 0) {
                    signature.append(',');
                }
                signature.append("int");
            }
            signature.append(")\n");
        }
        return signature.toString();
    }

    private String makeAtom(String event) {
        StringBuilder atom = new StringBuilder();
        atom.append(event).append('(');
        int i = 0;
        for (String variable : eventPattern.getArguments(event)) {
            if (i++ > 0) {
                atom.append(',');
            }
            atom.append(variable);
        }
        atom.append(')');
        return atom.toString();
    }

    String getFormula() {
        return String.format("(ONCE [0,%d) %s) IMPLIES %s IMPLIES ALWAYS [0,%d) NOT %s",
                positiveWindow,
                makeAtom(eventPattern.getBaseEvent()),
                makeAtom(eventPattern.getPositiveEvent()),
                negativeWindow,
                makeAtom(eventPattern.getNegativeEvent()));
    }
}
