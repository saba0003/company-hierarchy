package com.solvd.companyhierarchy.enums;

public enum ContractType {

    FULL_TIME(40),
    PART_TIME(20),
    INTERN(15),
    FREELANCER(0),
    CONSULTANT(10),
    STRATEGIC_ALLIANCE(0);

    private final int weeklyHours;

    ContractType(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }
}
