package company.employee;

import java.math.BigDecimal;
import java.util.Objects;

public class Developer extends Employee {

    private String stack;

    public Developer(String firstName, String lastName, String birthDate, BigDecimal salary, String stack) {
        super(firstName, lastName, birthDate, salary, "Developer");
        this.stack = stack;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    @Override
    public void conductWork() {
        System.out.println(getFullName() + " is coding in " + stack);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Developer other)) return false;
        return Objects.equals(stack, other.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stack);
    }
}
