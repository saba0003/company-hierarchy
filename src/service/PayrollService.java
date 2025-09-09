package service;

import company.employee.Employee;

import java.math.BigDecimal;

public class PayrollService {

    private static double taxRate = 0.1; // 10% tax

    private PayrollService() {
        throw new UnsupportedOperationException("Utility class should not be instantiated!");
    }

    public static void processPayroll(Employee employee) {
        BigDecimal gross = employee.getSalary();
        BigDecimal tax = gross.multiply(BigDecimal.valueOf(taxRate));
        BigDecimal net = gross.subtract(tax);
        System.out.println("Payroll processed for " + employee.getFullName() + " | Gross: " + gross + " | Net: " + net);
    }

    public static double getTaxRate() {
        return taxRate;
    }

    public static void setTaxRate(double taxRate) {
        PayrollService.taxRate = taxRate;
    }
}
