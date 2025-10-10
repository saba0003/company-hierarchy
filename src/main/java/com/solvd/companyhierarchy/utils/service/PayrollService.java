package com.solvd.companyhierarchy.utils.service;

import com.solvd.companyhierarchy.company.employee.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;

public final class PayrollService {

    private static final Logger log = LogManager.getLogger(PayrollService.class);

    private static final String CURRENCY = "USD";

    private static double taxRate = 0.1; // 10% tax

    static {
        log.info("PayrollService loaded with default tax rate {}%", taxRate * 100);
    }

    private PayrollService() {
        throw new UnsupportedOperationException("Utility class should not be instantiated!");
    }

    public static BigDecimal processPayroll(Employee employee) {
        UnaryOperator<BigDecimal> calculateTax = gross -> gross.multiply(BigDecimal.valueOf(taxRate));
        BigDecimal gross = employee.getSalary();
        BigDecimal tax = calculateTax.apply(gross);
        BigDecimal net = gross.subtract(tax);
        log.info("Payroll processed for {} | Gross: {} | Net: {}", employee.getFullName(), gross,  net);
        return net;
    }

    public static void processPayroll(List<Employee> employees) {
        employees.forEach(PayrollService::processPayroll);
    }

    public static double getTaxRate() {
        return taxRate;
    }

    public static void setTaxRate(double taxRate) {
        PayrollService.taxRate = taxRate;
    }

    public static void logTransaction(Payable payable) {
        log.info("Transaction logged for: {} {}", payable.calculatePay(), CURRENCY);
    }
}
