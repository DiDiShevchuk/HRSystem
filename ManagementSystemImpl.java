package task9OOP;

import task9OOP.pojo.EmployeeType;
import task9OOP.pojo.department.Department;
import task9OOP.pojo.employee.Economist;
import task9OOP.pojo.employee.Employee;
import task9OOP.pojo.employee.Manager;
import task9OOP.pojo.employee.Programmer;
import task9OOP.pojo.project.ProjectTech;


import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class ManagementSystemImpl implements ManagementSystem {
    private static final int YEAR = 12;
    private List<Department> departments; // список отделов
    List<ProjectTech> projectTechList; // список проектов

    public ManagementSystemImpl(List<Department> departments, List<ProjectTech> projectTechList) {
        this.departments = departments;
        this.projectTechList = projectTechList;
    }

    @Override
    public Employee getEmployeeById(int id) {
        for (Department department : departments) {
            Employee employee = department.getEmployeeByIdToDepartment(id);
            if (employee != null) {
                return employee;
            }
        }
        return null;
    }

    public void createEmployee() { // получить данные о новом сотруднике от пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите отдел: 1- Общий отдел; 2 - ИТ отдел; 3 - отдел управления; 4 - отдел экономистов)");
        int departSelect = scanner.nextInt();  // вводит отдел, в котором будет работать новый сотрудник
        String depart = "";
        switch (departSelect) {
            case 1 -> depart = "Общий отдел";
            case 2 -> depart = "ИТ отдел";
            case 3 -> depart = "отдел управления";
            case 4 -> depart = "отдел экономистов";
        }
        Department needDepartment = null;
        for (Department department : departments) {
            if (department.getName().equalsIgnoreCase(depart)) {  // ищем искомый отдел, который ввёл пользователь
                needDepartment = department; // присваиваем значение найденного отдела в переменную needDepartment
            }
        }
        if (needDepartment == null) {
            throw new RuntimeException("Департамент не найден");
        }
        String type = "";
        boolean isFlag = false;
        while (!isFlag) {
            System.out.print("Введите тип сотрудника (Economist/Programmer/Manager): ");
            try {
                type = scanner.nextLine(); // заменить на Enum
                if (!EmployeeType.contains(type)) {
                    throw new IllegalArgumentException("Тип сотрудника не найден. Введите заново: ");
                } else {
                    isFlag = true; // выход из цикла, тип сотрудника введен верно
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.print("фамилия: ");
        String firstName = scanner.nextLine();
        System.out.print("имя: ");
        String lastName = scanner.nextLine();
        System.out.print("оклад: ");
        int salary = scanner.nextInt();
        scanner.nextLine();
        System.out.println("дата приёма на работу (в формате год-месяц-день): ");
        String startDateStr = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateStr);
        Employee newEmployee = getNewEmployeeOfType(type.toUpperCase(), firstName, lastName, salary, startDate, needDepartment);
        needDepartment.addEmployee(newEmployee); // добавляем нового сотрудника в отдел
    }


    private Employee getNewEmployeeOfType(String type, String firstName, String lastName, int salary, LocalDate startDate, Department department) { // создаем сотрудника в конкретный отдел от пользователя
        return switch (type.toUpperCase()) {
            case "ECONOMIST" -> new Economist(firstName, lastName, salary, startDate, department);
            case "PROGRAMMER" -> new Programmer(firstName, lastName, salary, startDate, department);
            case "MANAGER" -> new Manager(firstName, lastName, salary, startDate, department);
            default -> throw new IllegalArgumentException("Неизвестный тип сотрудника: " + type);
        };
    }

    public int getEmployeeIdFromUser() { // получить от пользователя id сотрудника
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите id сотрудника: ");
        int id = scanner.nextInt();
        return id;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = new ArrayList<>();
        for (Department department : departments) {
            allEmployees.addAll(department.getEmployees());
        }
        return allEmployees;
    }

    public void getPrintAllEmployees() { // вывод в консоль списка всех сотрудников
        System.out.println("Список всех сотрудников отдела:");
        for (Employee employee : getAllEmployees()) {
            System.out.println(employee);
        }
//        System.out.println(getAllEmployees()); // список всех сотрудников
//        System.out.println("Количество сотрудников  " + departments.toString());
    }

    public void deleteEmployee(int id) { // уволить сотрудника
        Employee employee = getEmployeeById(id); // получаем нужного сотрудника по id
        if (employee != null) {
            Department department = employee.getDepartment(); // находим отдел, в котором работает сотрудник
            if (department != null) {
                department.deleteEmployeeDepartment(employee); // удаляем сотрудника из найденного отдела
                System.out.println("Сотрудник с ID " + id + " успешно уволен.");
            } else {
                System.out.println("Сотрудник с ID " + id + " не найден");
            }
        }
    }

    public void salaryInMonth(int id) { // зарплата сотрудника в месяц
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            int salary = employee.getSalary();
            System.out.println("Зарплата " + employee.getFirstName() + " " + salary + " руб в месяц");
        }
    }

    public void salaryForYear(int id) { // зарплата сотрудника за год
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            int salary = employee.getSalary() * YEAR;
            System.out.println("Зарплата " + employee.getFirstName() + " " + salary + " руб в год");
        }
    }

    public void salaryIncrease(int id, int percent) { // повышение зарплаты
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            int salaryIncrease = (employee.getSalary() / 100) * percent;
            System.out.println(" Зарплата повысилась: " + salaryIncrease + " руб.");
        } else {
            System.out.println("Сотрудник с данным id не найден");
        }
    }

    public void annualBonus(int id) { // годовой бонус
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.annualBonus();
        } else {
            System.out.println("Сотрудник с данным id не найден");
        }
    }

    public void workExperience(int id) {  // стаж работы
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            Period period = Period.between(employee.getStartDate(), LocalDate.now());
            System.out.println("Стаж работы: " + employee.getLastName() + " " + employee.getFirstName());
            System.out.println("Год: " + period.getYears());
            System.out.println("Месяц: " + period.getMonths());
            System.out.println("Дней: " + period.getDays());
        } else {
            System.out.println("Сотрудник с данным id не найден");
        }
    }

    public void getEmployeeForProjectFromUser(int id) { // получить данные о сотруднике для проекта от пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название проекта: (ИТ-проект/Стратегический проект/Маркетинговый проект) ");
        String project = scanner.nextLine();

        ProjectTech needProjectTech = null;
        for (ProjectTech projectTech : projectTechList) {
            if (projectTech.getName().equalsIgnoreCase(project)) {  // ищем искомый проект, который ввёл пользователь
                needProjectTech = projectTech; // присваиваем значение найденного проекта в переменную needProjectTech
            }
        }
        if (needProjectTech == null) {
            throw new RuntimeException("Проект не найден");
        }
        addEmployeeProjectId(id, needProjectTech);

    }

    public void addEmployeeProjectId(int id, ProjectTech projectTech) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            projectTech.addEmployeeProject(employee);
        } else {
            System.out.println("Сотрудник с данным id не найден");

        }
    }

    public void deleteEmployeeFromProject(int id) { // исключаем сотрудника из проекта
        Employee employee = getEmployeeById(id);
        if (employee == null) {
            System.out.println("Сотрудник с ID " + id + " не найден");
            return;
        }
        for (ProjectTech project : projectTechList) {
            project.deleteEmployeeProject(employee);
            System.out.println("Сотрудник с ID " + id + " исключен с проекта " + project.getName());
        }
    }


    public List<Employee> getAllEmployeesInProject() { // все сотрудники в проекте
        List<Employee> allEmployeesInProject = new ArrayList<>();
        for (ProjectTech projectTech : projectTechList) {
            allEmployeesInProject.addAll(projectTech.getProjectTeam());
        }
        return allEmployeesInProject;
    }

    public void getPrintAllEmployeesInProject() { // вывод в консоль списка всех сотрудников в проекте
        System.out.println("Список всех сотрудников проекта:");
        for (Employee employee : getAllEmployeesInProject()) {
            System.out.println(employee);
        }
//        System.out.println(getAllEmployeesInProject()); // кол-во всех сотрудников
//        System.out.println("Количество сотрудников в проекте: " + projectTechList.toString());
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}



