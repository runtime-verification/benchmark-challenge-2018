package ch.ethz.infsec;

import java.util.List;

interface EventPattern {
    List<String> getVariables();

    String getBaseEvent();

    String getPositiveEvent();

    String getNegativeEvent();

    List<String> getArguments(String event);
}
