package company;

import company.employee.Employee;
import exception.DepartmentFullException;
import exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {

    private String name;
    private List<Employee> employees;
    private final int capacity;

    public Department(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>(capacity);
    }

    public void hireEmployee(Employee employee) {
        if (employees.size() >= capacity)
            throw new DepartmentFullException("No more space for employees in " + name);
        employees.add(employee);
    }

    public void fireEmployee(Employee employee) {
        if (employees.isEmpty()) {
            System.out.println("No employees to remove!");
            return;
        }
        if ((employees.remove(employee)))
            System.out.println("Employee " + employee.getFullName() + " removed.");
        else
            throw new EmployeeNotFoundException("Employee not found in department " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEmployeeCount() {
        return employees.size();
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
