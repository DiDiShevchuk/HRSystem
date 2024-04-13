package task9OOP;

import task9OOP.pojo.employee.Employee;

import java.io.*;
import java.util.List;

public class EmployeeFileManager {
    public static void writeToFileEmployees(List<Employee> employees, String fileName) { // создаёт файл и записывает в него данные о сотрудниках
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(employees);
            System.out.println("File has been written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> inputEmployees(String fileName) { // читает данные из файла
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Employee>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}



