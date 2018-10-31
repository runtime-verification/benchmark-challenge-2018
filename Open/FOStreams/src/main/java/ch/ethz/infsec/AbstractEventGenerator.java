package ch.ethz.infsec;

import org.apache.commons.math3.random.RandomGenerator;

abstract class AbstractEventGenerator {
    final RandomGenerator random;

    private final int eventsPerIndex;
    private final long timeIncrement;
    private final long firstTimestamp;

    private long currentIndex = -1;
    private long currentEmissionTime = -1;
    private long currentTimestamp = 0;

    AbstractEventGenerator(RandomGenerator random, int eventRate, int indexRate, long firstTimestamp) {
        if (eventRate < indexRate || indexRate < 1 || firstTimestamp < 0) {
           throw new IllegalArgumentException();
        }

        this.random = random;
        this.eventsPerIndex = eventRate / indexRate;
        this.timeIncrement = 1000000000L / indexRate;
        this.firstTimestamp = firstTimestamp;
    }

    abstract void appendNextEvent(StringBuilder builder, long timestamp);

    abstract void initialize();

    String nextDatabase() {
        ++currentIndex;
        if (currentEmissionTime < 0) {
            currentEmissionTime = firstTimestamp * 1000000000L;
        } else {
            currentEmissionTime += timeIncrement;
        }
        currentTimestamp = currentEmissionTime / 1000000000L;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < eventsPerIndex; ++i) {
            appendNextEvent(builder, currentTimestamp);
            builder.append('\n');
        }

        return builder.toString();
    }

    void appendEventStart(StringBuilder builder, String relation) {
        builder
                .append(relation)
                .append(", tp=")
                .append(currentIndex)
                .append(", ts=")
                .append(currentTimestamp);
    }

    void appendAttribute(StringBuilder builder, String name, int value) {
        builder
                .append(", ")
                .append(name)
                .append('=')
                .append(value);
    }
}
