package company;

import company.employee.Employee;

import java.util.Objects;

public class Department {

    private String name;
    private Employee[] employees;
    private int employeeCount;

    public Department(String name, int maxEmployees) {
        this.name = name;
        this.employees = new Employee[maxEmployees];
        this.employeeCount = 0;
    }

    public void hireEmployee(Employee employee) {
        if (employeeCount < employees.length)
            employees[employeeCount++] = employee;
        else
            System.out.println("No more space for employees in " + name);
    }

    public void fireEmployee(Employee employee) {
        if (employeeCount == 0) {
            System.out.println("No employees to remove!");
            return;
        }
        for (int i = 0; i < employeeCount; i++) {
            if (employee.equals(employees[i])) {
                for (int j = i; j < employeeCount - 1; j++)
                    employees[j] = employees[j + 1];
                employees[employeeCount - 1] = null;
                employeeCount--;
                System.out.println("Employee " + employee.getFullName() + " removed.");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Department other)) return false;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
