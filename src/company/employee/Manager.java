package company.employee;

import java.math.BigDecimal;
import java.util.Objects;

public class Manager extends Employee {

    private int teamSize;

    public Manager(String firstName, String lastName, String birthDate, BigDecimal salary, String role, int teamSize) {
        super(firstName, lastName, birthDate, salary, role);
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
        System.out.println(getFullName() + " is managing a team of " + teamSize + " people");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Manager other)) return false;
        return Objects.equals(teamSize, other.teamSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teamSize);
    }
}