package enums;

public enum EmployeeLevel {

    JUNIOR(1, "Entry-level"),
    MID(2, "Experienced"),
    SENIOR(3, "Expert"),
    LEAD(4, "Team Leader"),
    MANAGER(5, "Department Head");

    private final int rank;
    private final String description;

    EmployeeLevel(int rank, String description) {
        this.rank = rank;
        this.description = description;
    }

    public int getRank() {
        return rank;
    }

    public String getDescription() {
        return description;
    }
}
