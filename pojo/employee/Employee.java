package task9OOP.pojo.employee;


import task9OOP.pojo.department.Department;

import java.io.Serializable;
import java.time.LocalDate;

import java.util.Objects;


public abstract class Employee implements Serializable{
    private static int count = 0; // переменная счётчик для создания новых сотрудников
    private int id;
    private String firstName;
    private String lastName;

    private int salary;
    private LocalDate startDate;
    private Department department;
    // добавить проект


    public Employee(String firstName, String lastName, int salary, LocalDate startDate, Department department) {
        this.id = ++count; // генерим уникальный id, каждый раз когда создаётся новый сотрудник
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.startDate = startDate;
        this.department = department;
    }

    public void changeDepartment(Department department) {   // меняем отдел
        this.department = department;
    }


    public abstract void annualBonus(); // годовая премия


    @Override
    public String toString() {
        return "Сотрудник: { " +
                "id=" + id + " " +
                firstName + " " + lastName + '\'' +
                ", оклад: " + salary +
                ", начало работы: " + startDate +
                ", отдел: " + department +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && salary == employee.salary && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(startDate, employee.startDate) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary, startDate, department);
    }
}

