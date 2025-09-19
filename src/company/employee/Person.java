package company.employee;

import exception.MissingBirthDateException;
import exception.MissingNameException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import static utils.DateTimeUtils.*;

abstract class Person {

    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;

    protected Person(String firstName, String lastName, String birthDate) {
        if (firstName == null || firstName.isBlank())
            throw new MissingNameException("First name cannot be null or blank!");
        if (lastName == null || lastName.isBlank())
            throw new MissingNameException("Last name cannot be null or blank!");
        if (birthDate == null || birthDate.isBlank())
            throw new MissingBirthDateException("Birth date cannot be null or blank!");
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = parseDate(birthDate);
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
        this.birthDate = parseDate(birthDate);
    }

    public int getAge() {
        return calculateAge(birthDate);
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
        return formatDate(birthDate);
    }

    /** AUX */
    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
