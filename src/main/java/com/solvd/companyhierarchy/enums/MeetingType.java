package com.solvd.companyhierarchy.enums;

public enum MeetingType {

    DAILY("Short stand-up", 15),
    WEEKLY("Team sync", 60),
    REVIEW("Project review", 90),
    INTERVIEW("Hiring interview", 45),
    TRAINING("Knowledge session", 120);

    private final String description;
    private final int durationMinutes;

    MeetingType(String description, int durationMinutes) {
        this.description = description;
        this.durationMinutes = durationMinutes;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}
