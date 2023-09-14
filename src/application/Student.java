package application;

import java.util.ArrayList;

public class Student {
    private String last_name;
    private String first_name;

    public Student(String last_name, String first_name){
        this.last_name = last_name;
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getStudent(){
        return last_name + ", " + first_name;
    }
}
