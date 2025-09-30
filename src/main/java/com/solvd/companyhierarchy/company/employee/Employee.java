package com.solvd.companyhierarchy.company.employee;

import com.solvd.companyhierarchy.enums.EmployeeLevel;
import com.solvd.companyhierarchy.utils.service.Payable;
import com.solvd.companyhierarchy.utils.service.PayrollService;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Employee extends Person implements Payable, Identifiable, Workable {

    private static int idCounter = 1000;

    protected final int id;
    protected BigDecimal salary;
    protected EmployeeLevel level;

    protected Employee(String firstName, String lastName, String birthDate, BigDecimal salary, EmployeeLevel level) {
        super(firstName, lastName, birthDate);
        this.id = idCounter++;
        this.salary = salary;
        this.level = level;
    }

    protected int getIdCounter() {
        return idCounter;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public EmployeeLevel getLevel() {
        return level;
    }

    @Override
    public BigDecimal calculatePay() {
        PayrollService.logTransaction(this);
        return PayrollService.processPayroll(this);
    }

    @Override
    public String getIdentifier() {
        return "Employee " + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Employee other)) return false;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getIdentifier() + ": " + getFullName() + " (" + level.getDescription() + ") | Salary: " + salary;
    }
}
