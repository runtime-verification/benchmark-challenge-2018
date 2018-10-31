package ch.ethz.infsec;

import java.util.Arrays;
import java.util.List;

abstract class BasicEventPattern implements EventPattern {

    @Override
    public String getBaseEvent() {
        return "A";
    }

    @Override
    public String getPositiveEvent() {
        return "B";
    }

    @Override
    public String getNegativeEvent() {
        return "C";
    }

    static class Star extends BasicEventPattern {

        @Override
        public List<String> getVariables() {
            return Arrays.asList("w", "x", "y", "z");
        }

        @Override
        public List<String> getArguments(String event) {
            switch (event) {
                case "A":
                    return Arrays.asList("w", "x");
                case "B":
                    return Arrays.asList("w", "y");
                case "C":
                    return Arrays.asList("w", "z");
                default:
                    throw new IllegalArgumentException("Unknown event: " + event);
            }
        }
    }

    static class Linear extends BasicEventPattern {

        @Override
        public List<String> getVariables() {
            return Arrays.asList("w", "x", "y", "z");
        }

        @Override
        public List<String> getArguments(String event) {
            switch (event) {
                case "A":
                    return Arrays.asList("w", "x");
                case "B":
                    return Arrays.asList("x", "y");
                case "C":
                    return Arrays.asList("y", "z");
                default:
                    throw new IllegalArgumentException("Unknown event: " + event);
            }
        }
    }

    static class Triangle extends BasicEventPattern {

        @Override
        public List<String> getVariables() {
            return Arrays.asList("x", "y", "z");
        }

        @Override
        public List<String> getArguments(String event) {
            switch (event) {
                case "A":
                    return Arrays.asList("x", "y");
                case "B":
                    return Arrays.asList("y", "z");
                case "C":
                    return Arrays.asList("z", "x");
                default:
                    throw new IllegalArgumentException("Unknown event: " + event);
            }
        }
    }
}
