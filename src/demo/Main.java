package demo;

import company.Company;
import company.Department;
import company.meetingroom.MeetingRoom;
import company.employee.Developer;
import company.employee.Employee;
import company.employee.Manager;
import company.meetingroom.MeetingRoomSession;
import company.project.Project;
import company.project.Task;
import company.project.WorkItem;
import contract.Client;
import contract.Contract;
import exception.MissingDescriptionException;
import utils.service.PayrollService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

        try {
            WorkItem item1 = new Task("Design UI");
            WorkItem item2 = new Task("Implement Backend");
            company.getProjects()[0].setTasks(new WorkItem[]{item1, item2});
            item1.markCompleted();
            System.out.println(item1);
        } catch (MissingDescriptionException e) {
            System.err.println("Work item creation failed: " + e.getMessage());
        }

        // Meeting room
        MeetingRoom room = new MeetingRoom("Conference Room A", 12);
        company.setMeetingRooms(new MeetingRoom[]{room});

        LocalDateTime meetingDateTime = LocalDateTime.of(2025, 9, 12, 18, 30);
        room.schedule(meetingDateTime);

        // Client & Contract
        Client client = new Client("Acme Corp", "Retail");
        Contract contract = new Contract("C-1001", client, 20000);
        company.setContracts(new Contract[]{contract});

        System.out.printf("Contract %s signed with %s worth %f$%n", contract.getContractId(), contract.getClient().getName(), contract.getValue());

        try (MeetingRoomSession session = new MeetingRoomSession(room, meetingDateTime)) {
            session.holdMeeting("Quarterly Planning");
        }
    }
}
