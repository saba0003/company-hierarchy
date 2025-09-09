package company.employee;

import person.Person;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Employee extends Person {

    private static int idCounter = 1000;

    protected final int id;
    protected BigDecimal salary;
    protected String role;

    protected Employee(String firstName, String lastName, String birthDate, BigDecimal salary, String role) {
        super(firstName, lastName, birthDate);
        this.id = idCounter++;
        this.salary = salary;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public abstract void conductWork();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Employee other)) return false;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getFullName());
    }

    @Override
    public String toString() {
        return "Employee " + id + ": " + getFullName() + " (" + role + ") | Salary: " + salary;
    }
}
