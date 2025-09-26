package company;

import company.meetingroom.MeetingRoom;
import company.project.Project;
import contract.Contract;
import exception.CompanyFullException;
import exception.DepartmentNotFoundException;
import exception.DuplicateDepartmentException;
import utils.LinkedList;

import java.time.LocalDate;
import java.util.*;

import static utils.DateTimeUtils.*;

public class Company {

    private static int companyCounter;

    private String name;
    private LocalDate foundedDate;
    private Map<String, Department> departments;
    private LinkedList<Project> projects;
    private List<Contract> contracts;
    private Set<MeetingRoom> meetingRooms;
    private int totalEmployeeCount;

    static {
        companyCounter = 0;
        System.out.println("Company class loaded. Counter initialized.");
    }

    public Company(String name, String foundedDate) {
        this.name = name;
        this.foundedDate = parseDate(foundedDate);
        this.departments = new HashMap<>();
        this.totalEmployeeCount = 0;
        companyCounter++;
    }

    public void addDepartment(Department department) {
        if (departments.size() >= department.getCapacity())
            throw new CompanyFullException("No more space for departments in company " + name);
        if (departments.containsKey(department.getName()))
            throw new DuplicateDepartmentException("Department already exists: " + department.getName());
        departments.put(department.getName(), department);
        totalEmployeeCount += department.getEmployeeCount();
    }

    public Department removeDepartment(Department department) {
        if (departments.isEmpty())
            throw new DepartmentNotFoundException("No departments to remove in company " + name);
        if (departments.containsKey(department.getName())) {
            totalEmployeeCount -= department.getEmployeeCount();
            System.out.println("Department " + department.getName() + " removed.");
            return departments.remove(department.getName());
        } else {
            throw new DepartmentNotFoundException("Department " + department.getName() + " not found in company " + name);
        }
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

    public Department getDepartment(String name) {
        return departments.get(name);
    }

    public Map<String, Department> getDepartments() {
        return Collections.unmodifiableMap(departments);
    }

    public void setDepartments(Map<String, Department> departments) {
        this.departments = departments;
        updateTotalEmployees();
    }

    public LinkedList<Project> getProjects() {
        return projects;
    }

    public void setProjects(LinkedList<Project> projects) {
        this.projects = projects;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Set<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }

    public void setMeetingRooms(Set<MeetingRoom> meetingRooms) {
        this.meetingRooms = meetingRooms;
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
        for (Map.Entry<String, Department> entry : departments.entrySet())
            if (entry.getValue() != null)
                total += entry.getValue().getEmployeeCount();
        this.totalEmployeeCount = total;
    }
}
