package com.solvd.companyhierarchy.demo;

import com.solvd.companyhierarchy.company.Company;
import com.solvd.companyhierarchy.company.Department;
import com.solvd.companyhierarchy.company.meetingroom.MeetingProcessor;
import com.solvd.companyhierarchy.company.meetingroom.MeetingRoom;
import com.solvd.companyhierarchy.company.employee.Developer;
import com.solvd.companyhierarchy.company.employee.Employee;
import com.solvd.companyhierarchy.company.employee.Manager;
import com.solvd.companyhierarchy.company.meetingroom.MeetingRoomSession;
import com.solvd.companyhierarchy.company.project.Project;
import com.solvd.companyhierarchy.company.project.Task;
import com.solvd.companyhierarchy.company.project.WorkItem;
import com.solvd.companyhierarchy.contract.Client;
import com.solvd.companyhierarchy.contract.Contract;
import com.solvd.companyhierarchy.enums.*;
import com.solvd.companyhierarchy.exception.MissingDescriptionException;
import com.solvd.companyhierarchy.functionals.TaskAssigner;
import com.solvd.companyhierarchy.utils.LinkedList;
import com.solvd.companyhierarchy.utils.annotations.CustomAuditable;
import com.solvd.companyhierarchy.utils.service.PayrollService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

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

            company.setDepartments(departments);
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
            Consumer<String> printSeniors = fullName -> log.info("Senior Employee: {}", fullName);
            Predicate<Employee> isSenior = e -> e.getLevel() == EmployeeLevel.SENIOR;
            Function<Employee, String> toName = Employee::getFullName;

            List<String> seniorsWithFullNames = company.getDepartments().values().stream()
                    .flatMap(dep -> dep.getEmployees().stream())
                    .filter(isSenior)
                    .map(toName)
                    .toList();

            seniorsWithFullNames.forEach(printSeniors);
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
                log.info(item1);
            } catch (MissingDescriptionException e) {
                log.error("Work item creation failed: {}", e.getMessage());
            } finally {
                log.info("Final project state: {}", websiteProject.getStatus());
                log.info("Assigned tasks: {}", websiteProject.getWorkItems());
            }
        };

        // Meeting room
        Runnable meetingRoomRunnable = () -> {
            MeetingProcessor processor = new MeetingProcessor();
            MeetingRoom room = new MeetingRoom("Conference Room", 12);

            processor.processMeeting(room, LocalDateTime.now().plusHours(2),
                    (r, dt) -> log.info("Lambda received: {} at {}", r.getIdentifier(), dt)
            );
        };

        // Client & Contract
        Runnable clientAndContractRunnable = () -> {
            try {
                Class<Client> clientClass = Client.class;
                Client client = clientClass
                        .getConstructor(String.class, String.class)
                        .newInstance("Acme Corp", "Retail");

                for (Method method : clientClass.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(CustomAuditable.class)) {
                        CustomAuditable ann = method.getAnnotation(CustomAuditable.class);
                        log.info("Audit -> category: {} | invoking {} with arg: {}",
                                ann.value(), method.getName(), "Acme Inc");
                        if (method.getName().equals("setName"))
                            method.invoke(client, "Acme Inc");
                        else if (method.getName().equals("setIndustry"))
                            method.invoke(client, "Technology");
                    }
                }

                Class<Contract> contractClass = Contract.class;
                Class<ContractType> contractTypeClass = ContractType.class;

                ContractType contractType = Enum.valueOf(contractTypeClass, "STRATEGIC_ALLIANCE");

                Contract contract = contractClass
                        .getConstructor(String.class, clientClass, double.class, contractTypeClass)
                        .newInstance("C-1001", client, 20000.0, contractType);

                Class<Company> companyClass = Company.class;
                companyClass.getMethod("setContracts", List.class).invoke(company, List.of(contract));

                Method getContractId = contractClass.getMethod("getContractId");
                Method getClient = contractClass.getMethod("getClient");
                Method getName = clientClass.getMethod("getName");
                Method getValue = contractClass.getMethod("getValue");

                String contractId = (String) getContractId.invoke(contract);
                Object contractClient = getClient.invoke(contract);
                String clientName = (String) getName.invoke(contractClient);
                Double value = (Double) getValue.invoke(contract);

                log.info("Contract {} signed with {} worth {}$", contractId, clientName, value);

                Class<MeetingRoomSession> meetingRoomSessionClass = MeetingRoomSession.class;
                Class<MeetingType> meetingTypeClass = MeetingType.class;

                MeetingType meetingType = Enum.valueOf(meetingTypeClass, "TRAINING");

                MeetingRoom meetingRoom = company.getMeetingRooms().iterator().next();

                MeetingRoomSession session = meetingRoomSessionClass
                        .getConstructor(
                                meetingRoom.getClass(),
                                LocalDateTime.class,
                                meetingTypeClass
                        )
                        .newInstance(
                                meetingRoom,
                                LocalDateTime.of(2025, 9, 12, 18, 30),
                                meetingType
                        );

                Method holdMeeting = meetingRoomSessionClass.getMethod("holdMeeting", String.class);
                holdMeeting.invoke(session, "Quarterly Planning");

                Method close = meetingRoomSessionClass.getMethod("close");
                close.invoke(session);

            } catch (Exception e) {
                log.error(Arrays.toString(e.getStackTrace()));
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
