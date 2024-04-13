package task9OOP;

import task9OOP.pojo.department.Department;
import task9OOP.pojo.employee.Economist;
import task9OOP.pojo.employee.Employee;
import task9OOP.pojo.project.ProjectTech;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //        List<Employee> savedEmployees = EmployeeFileManager.inputEmployees("data.txt");
        List<Department> departments = new ArrayList<>(); // создала список отделов
        Department department = new Department("Общий отдел");
        Department itDepartment = new Department("ИТ отдел");
        Department managerDepartment = new Department("отдел управления");
        Department economistDepartment = new Department("отдел экономистов");
        departments.add(department);
        departments.add(itDepartment);
        departments.add(managerDepartment);
        departments.add(economistDepartment);

        List<ProjectTech> projectTechList = new ArrayList<>();
        ProjectTech projectIT = new ProjectTech("ИТ-проект");
        ProjectTech StrategicProject = new ProjectTech("Стратегический проект");
        ProjectTech marketingProject = new ProjectTech("Маркетинговый проект");
        projectTechList.add(projectIT);
        projectTechList.add(StrategicProject);
        projectTechList.add(marketingProject);

        ManagementSystemImpl managementSystem = new ManagementSystemImpl(departments, projectTechList); // через интерфейс
//        Employee employee1 = new Economist("Тест", "Тест", 150000, LocalDate.now(), economistDepartment);
//        economistDepartment.addEmployee(employee1);
        int select;

        do {

            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.println("----   Система управления сотрудниками банка   ---");
            System.out.println("--------------------------------------------------");
            System.out.println("--- 1 Нанять сотрудника                        ---");
            System.out.println("--- 2 Уволить сотрудника                       ---");
            System.out.println("--- 3 Узнать оклад сотрудника                  ---");
            System.out.println("--- 4 Узнать оклад сотрудника за год           ---");
            System.out.println("--- 5 Узнать премию сотрудника за год          ---");
            System.out.println("--- 6 Повысить оклад сотрудника                ---");
            System.out.println("--- 7 Узнать стаж работы сотрудника            ---");
            System.out.println("--- 8 Добавить сотрудника в проект             ---");
            System.out.println("--- 9 Убрать сотрудника из проекта             ---");
            System.out.println("--- 10 Показать сотрудников в проекте          ---");
            System.out.println("--- 11 список всех сотрудников                 ---");
            System.out.println("--- 0 Выйти                                    ---");
            System.out.println("--------------------------------------------------");
            System.out.print("--- Введите действие, которые вы хотите выбрать :   ");

            select = scanner.nextInt();
            scanner.nextLine();

            switch (select) {
                case 1 -> {
                    managementSystem.createEmployee();
                }
                case 2 -> {
                    int id = managementSystem.getEmployeeIdFromUser();
                    managementSystem.deleteEmployee(id);
                }
                case 3 -> {
                    int id = managementSystem.getEmployeeIdFromUser();
                    managementSystem.salaryInMonth(id);
                }

                case 4 -> {
                    int id = managementSystem.getEmployeeIdFromUser();
                    managementSystem.salaryForYear(id);
                }
                case 5 -> {
                    int id = managementSystem.getEmployeeIdFromUser();
                    managementSystem.annualBonus(id);
                }
                case 6 -> {
                    int id = managementSystem.getEmployeeIdFromUser();
                    System.out.print("Введите процент повышения оклада : ");
                    int percent = scanner.nextInt();
                    managementSystem.salaryIncrease(id, percent);
                }
                case 7 -> {
                    int id = managementSystem.getEmployeeIdFromUser();
                    managementSystem.workExperience(id);
                }
                case 8 -> {
                    int id = managementSystem.getEmployeeIdFromUser();
                    managementSystem.getEmployeeForProjectFromUser(id);
                }
                case 9 -> {
                    int id = managementSystem.getEmployeeIdFromUser();
                    managementSystem.deleteEmployeeFromProject(id);
                }
                case 10 -> {
                    managementSystem.getPrintAllEmployeesInProject();
                }
                case 11 -> {
                    managementSystem.getPrintAllEmployees();
                }
                case 12 -> {
                    List<Employee> empls = departments.stream().flatMap(d -> d.getEmployees().stream()).collect(Collectors.toList());
                    EmployeeFileManager.writeToFileEmployees(empls, "data.txt");
                }
                case 13 -> {
                    List<Employee> employees = EmployeeFileManager.inputEmployees("data.txt");
                    System.out.println(employees);
                }
            }

        } while (select != 0);
        scanner.close();
    }
}


