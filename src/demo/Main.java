package demo;

import company.Company;
import company.Department;
import company.MeetingRoom;
import company.employee.Developer;
import company.employee.Employee;
import company.employee.Manager;
import company.project.Project;
import company.project.Task;
import company.project.WorkItem;
import contract.Client;
import contract.Contract;
import service.PayrollService;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // Company, Departments and Employees
        Company company = new Company("TechCorp", "29/11/2010");

        Department devDept = new Department("Development", 10);
        Department hrDept = new Department("HR", 5);
        company.setDepartments(new Department[]{devDept, hrDept});

        Employee e1 = new Developer("Alice", "Liddell", "09/04/2007", BigDecimal.valueOf(8000), "Java");
        Employee e2 = new Developer("Bob", "Marley", "03/08/2004", BigDecimal.valueOf(5000), "Python");
        Employee e3 = new Manager("Diana", "Kruger", "24/12/1998", BigDecimal.valueOf(4000), "HR Specialist", 5);

        devDept.hireEmployee(e1);
        devDept.hireEmployee(e2);
        hrDept.hireEmployee(e3);

        // Payroll
        PayrollService.processPayroll(e1);
        PayrollService.processPayroll(e2);
        PayrollService.processPayroll(e3);

        // Project and tasks
        Project project = new Project("New Website");
        company.setProjects(new Project[]{project});

        WorkItem item1 = new Task("Design UI");
        WorkItem item2 = new Task("Implement Backend");
        company.getProjects()[0].setTasks(new WorkItem[]{item1, item2});

        item1.markCompleted();
        System.out.println(item1);

        // Meeting room
        MeetingRoom room = new MeetingRoom("Conference Room A", 12);
        company.setMeetingRooms(new MeetingRoom[]{room});

        room.bookRoom();

        // Client & Contract
        Client client = new Client("Acme Corp", "Retail");
        Contract contract = new Contract("C-1001", company, client, 20000);
        System.out.println("Contract " + contract.getContractId() +
                " signed with " + contract.getClient().getName() +
                " worth $" + contract.getValue());
    }
}
