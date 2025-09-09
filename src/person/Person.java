package person;

import company.employee.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public abstract class Person {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;

    protected Person(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = parseBirthDate(birthDate);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getBirthDate() {
        return getFormattedBirthDate();
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = parseBirthDate(birthDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee other)) return false;
        return Objects.equals(getFullName(), other.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName());
    }

    /** AUX */
    private String getFormattedBirthDate() {
        return birthDate.format(FORMATTER);
    }

    /** AUX */
    private LocalDate parseBirthDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format, expected dd/MM/yyyy: " + dateStr);
        }
    }
}
