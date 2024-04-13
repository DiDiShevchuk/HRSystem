package task9OOP.pojo.project;

import task9OOP.pojo.employee.Employee;

import java.util.ArrayList;
import java.util.List;


public class ProjectTech {
    private List<Employee> projectTeams = new ArrayList<>();
    private String name;

    public ProjectTech(String name) {
        this.name = name;
        this.projectTeams = getProjectTeam();
    }
    public void addEmployeeProject(Employee employee) {  // добавить сотрудника в проект
        if (!projectTeams.contains(employee)) {
            projectTeams.add(employee);

            System.out.println(employee.getLastName() + " " + employee.getFirstName() + " добален в проект");
        } else {
            System.out.println("Сотрудник с данным id не найден");
        }
    }
    public void deleteEmployeeProject(Employee employee) { // удалить сотрудника из проекта
        if (projectTeams.remove(employee)) {
            System.out.println(employee.getLastName() + " исключён из проекта");
        } else {
            System.out.println("Сотрудник " + employee.getLastName() + " не найден в проекте");
        }
    }
    public List<Employee> getProjectTeam() {
        return projectTeams;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ": " + projectTeams.size();
    }
}

