package utils.service;

import company.employee.Employee;

import java.math.BigDecimal;

public final class PayrollService {

    private static final String CURRENCY = "USD";

    private static double taxRate = 0.1; // 10% tax

    static {
        System.out.println("PayrollService loaded with default tax rate " + taxRate * 100 + "%");
    }

    private PayrollService() {
        throw new UnsupportedOperationException("Utility class should not be instantiated!");
    }

    public static BigDecimal processPayroll(Employee employee) {
        BigDecimal gross = employee.getSalary();
        BigDecimal tax = gross.multiply(BigDecimal.valueOf(taxRate));
        BigDecimal net = gross.subtract(tax);
        System.out.println("Payroll processed for " + employee.getFullName() + " | Gross: " + gross + " | Net: " + net);
        return net;
    }

    public static double getTaxRate() {
        return taxRate;
    }

    public static void setTaxRate(double taxRate) {
        PayrollService.taxRate = taxRate;
    }

    public static void logTransaction(Payable payable) {
        System.out.println("Transaction logged for: " + payable.calculatePay() + " " + CURRENCY);
    }
}
