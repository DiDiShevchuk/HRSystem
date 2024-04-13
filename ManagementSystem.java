package task9OOP;

import task9OOP.pojo.employee.Employee;

public interface ManagementSystem {
    Employee getEmployeeById(int id);

    void getPrintAllEmployees();

    int getEmployeeIdFromUser();
    void getEmployeeForProjectFromUser(int id);

    void deleteEmployee(int id);

    void salaryInMonth(int id);

    void salaryForYear(int id);

    void salaryIncrease(int id, int percent);

    void annualBonus(int id);

    void workExperience(int id);
}

