package com.happyineo.addribute.type;

public enum LogType {
    DEBUG("debug",0),
    INFO("info",100),
    ERROR("error",200),
    WARNING("warning",300),
    CRITICAL("critical",400);

    private final String logLevel;  // レベル
    private final int serious;  // 深刻度

    LogType(String logLevel,int serious) {
        this.logLevel = logLevel;
        this.serious = serious;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public int getSerious() {
        return serious;
    }

}
