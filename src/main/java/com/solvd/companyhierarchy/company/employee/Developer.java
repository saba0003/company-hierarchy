package com.solvd.companyhierarchy.company.employee;

import com.solvd.companyhierarchy.enums.EmployeeLevel;
import com.solvd.companyhierarchy.enums.Stack;

import java.math.BigDecimal;
import java.util.Objects;

public class Developer extends Employee {

    private Stack stack;

    public Developer(String firstName, String lastName, String birthDate, BigDecimal salary, Stack stack, EmployeeLevel level) {
        super(firstName, lastName, birthDate, salary, level);
        this.stack = stack;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void conductWork() {
        System.out.println(getFullName() + " is coding in " + stack);
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
