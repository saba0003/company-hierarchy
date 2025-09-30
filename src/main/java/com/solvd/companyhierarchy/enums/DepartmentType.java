package com.solvd.companyhierarchy.enums;

public enum DepartmentType {

    DEVELOPMENT("Writes code", true),
    HR("Manages people", false),
    SALES("Sells products", true),
    FINANCE("Handles money", false),
    SUPPORT("Helps customers", true);

    private final String responsibility;
    private final boolean revenueGenerating;

    DepartmentType(String responsibility, boolean revenueGenerating) {
        this.responsibility = responsibility;
        this.revenueGenerating = revenueGenerating;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public boolean isRevenueGenerating() {
        return revenueGenerating;
    }
}
