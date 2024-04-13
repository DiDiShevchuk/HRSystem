package task9OOP.pojo.department;

import task9OOP.EmployeeFileManager;
import task9OOP.pojo.employee.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Department implements Serializable {
    private List<Employee> employees = new ArrayList<>();
    private String name;

    public Department(String name) {
        this.name = name;
        this.employees = getEmployees();
    }
    public Employee getEmployeeByIdToDepartment(int id) { // получаем по id сотрудника
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
    public void addEmployee(Employee employee) { // добавляем сотрудника в отдел
        employees.add(employee);
        employee.changeDepartment(this);
        employee.setDepartment(this);
        System.out.println(employee.getFirstName() + " " + employee.getLastName() + " принят в " + this.name);
        EmployeeFileManager.writeToFileEmployees(employees,"data.txt" );
    }

    public void deleteEmployeeDepartment(Employee employee) { // удалили сотрудника из списка List<Employee> employees
        employees.remove(employee);
        System.out.println(employee.getFirstName() + employee.getLastName() + " уволен из отдела " + this.name);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return ": " + employees.size();
    }
}

