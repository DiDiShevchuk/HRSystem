package task9OOP.pojo.employee;

import task9OOP.pojo.department.Department;

import java.time.LocalDate;

public class Manager extends Employee {
    private static final int YEAR = 12;
    private static final int BONUS = 25;

    public Manager(String firstName, String lastName, int salary, LocalDate startDate, Department department) {
        super(firstName, lastName, salary, startDate, department);
    }

    @Override
    public void annualBonus() {
        int salaryBonus = (this.getSalary() * YEAR) / BONUS;
        System.out.println("Бонус менеджера:  " + salaryBonus + " руб");
    }
}

