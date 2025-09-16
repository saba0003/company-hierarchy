package company;

import company.meetingroom.MeetingRoom;
import company.project.Project;
import contract.Contract;
import exception.CompanyFullException;
import exception.DepartmentNotFoundException;

import java.time.LocalDate;

import static utils.DateUtils.*;

public class Company {

    private static int companyCounter;

    private String name;
    private LocalDate foundedDate;
    private Department[] departments;
    private Project[] projects;
    private Contract[] contracts;
    private MeetingRoom[] meetingRooms;
    private int departmentCount;
    private int totalEmployeeCount;

    static {
        companyCounter = 0;
        System.out.println("Company class loaded. Counter initialized.");
    }

    public Company(String name, String foundedDate) {
        this.name = name;
        this.foundedDate = parseDate(foundedDate);
        this.departmentCount = 0;
        this.totalEmployeeCount = 0;
        companyCounter++;
    }

    public void addDepartment(Department department) {
        if (departmentCount >= departments.length)
            throw new CompanyFullException("No more space for departments in company " + name);
        departments[departmentCount++] = department;
        totalEmployeeCount += department.getEmployeeCount();
    }

    public void removeDepartment(Department department) {
        if (departmentCount == 0)
            throw new DepartmentNotFoundException("No departments to remove in company " + name);
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
        throw new DepartmentNotFoundException("Department " + department.getName() + " not found in company " + name);
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
        this.foundedDate = parseDate(foundedDate);
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

    public Contract[] getContracts() {
        return contracts;
    }

    public void setContracts(Contract[] contracts) {
        this.contracts = contracts;
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
        return formatDate(foundedDate);
    }

    /** AUX */
    private void updateTotalEmployees() {
        int total = 0;
        for (int i = 0; i < departmentCount; i++)
            if (departments[i] != null)
                total += departments[i].getEmployeeCount();
        this.totalEmployeeCount = total;
    }
}
