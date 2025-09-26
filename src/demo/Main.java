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
import enums.*;
import exception.MissingDescriptionException;
import functionals.MeetingScheduler;
import functionals.TaskAssigner;
import utils.LinkedList;
import utils.service.PayrollService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        Company company = new Company("TechCorp", "29/11/2010");

        // Company, Departments and Employees
        Runnable companyAndDepartmentsAndEmployeesRunnable = () -> {
            Department devDept = new Department("Development", 10, DepartmentType.DEVELOPMENT);
            Department hrDept = new Department("HR", 5, DepartmentType.HR);
            Map<String, Department> departments = Map.of(
                    devDept.getName(), devDept,
                    hrDept.getName(), hrDept
            );

            Employee e1 = new Developer("Alice", "Liddell", "09/04/2007", BigDecimal.valueOf(8000), Stack.JAVA, EmployeeLevel.JUNIOR);
            Employee e2 = new Developer("Bob", "Marley", "03/08/2004", BigDecimal.valueOf(5000), Stack.PYTHON, EmployeeLevel.SENIOR);
            Employee e3 = new Manager("Diana", "Kruger", "24/12/1998", BigDecimal.valueOf(4000), EmployeeLevel.LEAD, 5);

            BiConsumer<Department, Employee> hireEmployee = Department::hireEmployee;
            hireEmployee.accept(departments.get(devDept.getName()), e1);
            hireEmployee.accept(departments.get(devDept.getName()), e2);
            hireEmployee.accept(departments.get(hrDept.getName()), e3);
        };

        // Payroll
        Runnable payrollRunnable = () -> {
            Consumer<List<Employee>> payrollProcessor = PayrollService::processPayroll;
            payrollProcessor.accept(List.of(company.getDepartments().get("Development").getEmployees().getFirst(),
                    company.getDepartments().get("Development").getEmployees().get(1),
                    company.getDepartments().get("HR").getEmployees().getFirst()));
        };

        // Print employees
        Runnable printer = () -> {
            Consumer<Employee> printEmployee = e -> System.out.println("Employee: " + e.getFullName());
            Predicate<Employee> isSenior = e -> e.getLevel() == EmployeeLevel.SENIOR;
            Function<Employee, String> toName = Employee::getFullName;

            company.getDepartments().get("Development").getEmployees().forEach(e -> {
                printEmployee.accept(e);
                if (isSenior.test(e))
                    System.out.println(" -> Senior detected!");
                System.out.println(" -> Name from Function: " + toName.apply(e));
            });
        };

        // Project and tasks
        Runnable projectsAndTasksRunnable = () -> {
            Supplier<Project> projectCreator = () -> new Project("Default Project", ProjectStatus.PLANNED);
            Project websiteProject = new Project("New Website", ProjectStatus.PLANNED);
            company.setProjects(LinkedList.of(websiteProject, projectCreator.get()));

            try {
                TaskAssigner assigner = (project, tasks) -> project.setTasks(Set.of(tasks));
                WorkItem item1 = new Task("Design UI");
                WorkItem item2 = new Task("Implement Backend");
                assigner.assign(websiteProject, item1, item2);
                item1.markCompleted();
                websiteProject.setStatus(ProjectStatus.IN_PROGRESS);
                System.out.println(item1);
            } catch (MissingDescriptionException e) {
                System.err.println("Work item creation failed: " + e.getMessage());
            } finally {
                System.out.println("Final project state: " + websiteProject.getStatus());
                System.out.println("Assigned tasks: " + websiteProject.getWorkItems());
            }
        };

        // Meeting room
        Runnable meetingRoomRunnable = () -> {
            company.setMeetingRooms(Set.of(new MeetingRoom("Conference Room A", 12)));
            MeetingScheduler scheduler = MeetingRoom::schedule;
            scheduler.schedule(company.getMeetingRooms().iterator().next(),
                    LocalDateTime.of(2025, 9, 12, 18, 30));
        };

        // Client & Contract
        Runnable clientAndContractRunnable = () -> {
            Client client = new Client("Acme Corp", "Retail");
            Contract contract = new Contract("C-1001", client, 20000, ContractType.STRATEGIC_ALLIANCE);
            company.setContracts(List.of(contract));

            System.out.printf("Contract %s signed with %s worth %f$%n", contract.getContractId(), contract.getClient().getName(), contract.getValue());

            try (MeetingRoomSession session = new MeetingRoomSession(
                    company.getMeetingRooms().iterator().next(),
                    LocalDateTime.of(2025, 9, 12, 18, 30),
                    MeetingType.TRAINING)) {
                session.holdMeeting("Quarterly Planning");
            }
        };

        List.of(
                companyAndDepartmentsAndEmployeesRunnable,
                payrollRunnable,
                printer,
                projectsAndTasksRunnable,
                meetingRoomRunnable,
                clientAndContractRunnable
        ).forEach(Runnable::run);
    }
}
