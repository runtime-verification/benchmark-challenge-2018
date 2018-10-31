package ch.ethz.infsec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CustomEventPattern implements EventPattern {
    private List<String> variables;
    private List<String> events;
    private List<List<String>> arguments;

    private CustomEventPattern(List<String> events, List<List<String>> arguments) {
        this.events = events;
        this.arguments = arguments;

        final List<String> variables = new ArrayList<>();
        for (final List<String> args : this.arguments) {
            for (String arg : args) {
                if (!variables.contains(arg)) {
                    variables.add(arg);
                }
            }
        }
        this.variables = Collections.unmodifiableList(variables);
    }

    private static final Pattern specificationPattern =
            Pattern.compile("([a-zA-Z0-9_-]+)\\(([a-zA-Z0-9_-]+(?:,\\s*[a-zA-Z0-9_-]+)*)?\\)\\s*");
    private static final Pattern argumentDelimiter = Pattern.compile(",\\s*");

    static CustomEventPattern parse(String pattern) throws InvalidEventPatternException {
        final List<String> events = new ArrayList<>();
        final List<List<String>> arguments = new ArrayList<>();

        final Matcher matcher = specificationPattern.matcher(pattern);
        while (matcher.regionStart() < pattern.length()) {
            if (!matcher.lookingAt() || matcher.group(2) == null) {
                throw new InvalidEventPatternException("Syntax error in event pattern");
            }
            events.add(matcher.group(1));
            final String[] args = argumentDelimiter.split(matcher.group(2));
            arguments.add(Collections.unmodifiableList(Arrays.asList(args)));
            matcher.region(matcher.end(), pattern.length());
        }

        if (events.size() != 3) {
            throw new InvalidEventPatternException("Event pattern must contain exactly three events");
        }

        return new CustomEventPattern(Collections.unmodifiableList(events), Collections.unmodifiableList(arguments));
    }

    @Override
    public List<String> getVariables() {
        return variables;
    }

    @Override
    public String getBaseEvent() {
        return events.get(0);
    }

    @Override
    public String getPositiveEvent() {
        return events.get(1);
    }

    @Override
    public String getNegativeEvent() {
        return events.get(2);
    }

    @Override
    public List<String> getArguments(String event) {
        int index = events.indexOf(event);
        if (index < 0) {
            throw new IllegalArgumentException("Unknown event: " + event);
        }
        return arguments.get(index);
    }
}
