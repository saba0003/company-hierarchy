package com.solvd.companyhierarchy.company;

import com.solvd.companyhierarchy.company.employee.Employee;
import com.solvd.companyhierarchy.enums.DepartmentType;
import com.solvd.companyhierarchy.exception.DepartmentFullException;
import com.solvd.companyhierarchy.exception.EmployeeNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {

    private static final Logger log = LogManager.getLogger(Department.class);

    private String name;
    private List<Employee> employees;
    private final int capacity;
    private final DepartmentType departmentType;

    public Department(String name, int capacity, DepartmentType departmentType) {
        this.name = name;
        this.employees = new ArrayList<>(capacity);
        this.capacity = capacity;
        this.departmentType = departmentType;
    }

    public void hireEmployee(Employee employee) {
        if (employees.size() >= capacity)
            throw new DepartmentFullException("No more space for employees in " + name);
        employees.add(employee);
    }

    public void fireEmployee(Employee employee) {
        if (employees.isEmpty()) {
            log.info("No employees to remove!");
            return;
        }
        if ((employees.remove(employee)))
            log.info("Employee {} removed.", employee.getFullName());
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

    public DepartmentType getDepartmentType() {
        return departmentType;
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
