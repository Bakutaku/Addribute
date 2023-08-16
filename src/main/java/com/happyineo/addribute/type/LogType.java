package com.happyineo.addribute.type;

public enum LogType {
    INFO("info"),
    ERROR("error"),
    WARNING("warning"),
    CRITICAL("critical");

    private final String logLevel;

    LogType(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogLevel() {
        return logLevel;
    }
}
