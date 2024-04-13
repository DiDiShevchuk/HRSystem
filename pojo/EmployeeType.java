package task9OOP.pojo;

public enum EmployeeType {
    ECONOMIST,
    PROGRAMMER,
    MANAGER;
    public static boolean contains(String type){
        for(EmployeeType t : values()){
            if(t.name().equalsIgnoreCase(type)){
                return true;
            }
        }
        return false;
    }
}
