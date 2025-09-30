package com.solvd.companyhierarchy.company.employee;

import com.solvd.companyhierarchy.enums.EmployeeLevel;

import java.math.BigDecimal;
import java.util.Objects;

public class Manager extends Employee implements Reportable {

    private int teamSize;

    public Manager(String firstName, String lastName, String birthDate, BigDecimal salary, EmployeeLevel level, int teamSize) {
        super(firstName, lastName, birthDate, salary, level);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    @Override
    public void conductWork() {
        System.out.println(getFullName() + " is yelling at a team of " + teamSize + " people");
    }

    @Override
    public String generateReport() {
        return "Manager " + getFullName() + " oversees " + teamSize + " employees.";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}