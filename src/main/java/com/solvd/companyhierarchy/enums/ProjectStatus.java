package com.solvd.companyhierarchy.enums;

public enum ProjectStatus {

    PLANNED("Not started yet"),
    IN_PROGRESS("Ongoing"),
    ON_HOLD("Paused"),
    COMPLETED("Finished successfully"),
    CANCELLED("Stopped");

    private final String description;

    ProjectStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
