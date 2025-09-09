package company;

import company.project.Project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Company {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static int companyCounter;

    private String name;
    private LocalDate foundedDate;
    private Department[] departments;
    private Project[] projects;
    private MeetingRoom[] meetingRooms;
    private int departmentCount;
    private int totalEmployeeCount;

    static {
        companyCounter = 0;
        System.out.println("Company class loaded. Counter initialized.");
    }

    public Company(String name, String foundedDate) {
        this.name = name;
        this.foundedDate = parseFoundedDate(foundedDate);
        this.departmentCount = 0;
        this.totalEmployeeCount = 0;
        companyCounter++;
    }

    public void addDepartment(Department department) {
        if (departmentCount < departments.length) {
            departments[departmentCount++] = department;
            totalEmployeeCount += department.getEmployeeCount();
        } else {
            System.out.println("No more space for departments!");
        }
    }

    public void removeDepartment(Department department) {
        if (departmentCount == 0) {
            System.out.println("No departments to remove!");
            return;
        }
        for (int i = 0; i < departmentCount; i++) {
            if (department.equals(departments[i])) {
                for (int j = i; j < departmentCount - 1; j++)
                    departments[j] = departments[j + 1];
                departments[departmentCount - 1] = null;
                departmentCount--;
                totalEmployeeCount -= department.getEmployeeCount();
                System.out.println("Department " + department.getName() + " removed.");
                return;
            }
        }
        System.out.println("Department not found!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoundedDate() {
        return getFormattedFoundedDate();
    }

    public void setFoundedDate(String foundedDate) {
        this.foundedDate = parseFoundedDate(foundedDate);
    }

    public Department[] getDepartments() {
        return departments;
    }

    public void setDepartments(Department[] departments) {
        this.departments = departments;
        updateTotalEmployees();
    }

    public Project[] getProjects() {
        return projects;
    }

    public void setProjects(Project[] projects) {
        this.projects = projects;
    }

    public MeetingRoom[] getMeetingRooms() {
        return meetingRooms;
    }

    public void setMeetingRooms(MeetingRoom[] meetingRooms) {
        this.meetingRooms = meetingRooms;
    }

    public int getDepartmentCount() {
        return departmentCount;
    }

    public int getTotalEmployeeCount() {
        return totalEmployeeCount;
    }

    public static int getCompanyCounter() {
        return companyCounter;
    }

    public String getFormattedFoundedDate() {
        return foundedDate.format(FORMATTER);
    }

    /** AUX */
    private void updateTotalEmployees() {
        int total = 0;
        for (int i = 0; i < departmentCount; i++)
            if (departments[i] != null)
                total += departments[i].getEmployeeCount();
        this.totalEmployeeCount = total;
    }

    /** AUX */
    private LocalDate parseFoundedDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format, expected dd/MM/yyyy: " + dateStr);
        }
    }
}
