package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StudentRoster {

    private ArrayList<Student> student_list;
    private int count = 0;

    public StudentRoster(){

    }

    public ArrayList<Student> load_roster(String file){
        student_list = new ArrayList<>();

        try{
            Scanner sc = new Scanner(new File(file));

            while(sc.hasNextLine()){
                count += 1;
                String line = sc.nextLine();
                List<String> student_info = Arrays.asList(line.split(", "));
                String last_name = student_info.get(0);
                String first_name = student_info.get(1);

                Student s = new Student(last_name, first_name);
                student_list.add(s);

            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return student_list;
    }

    public void print_collection(){
        for (int i = 0; i < student_list.size(); i++) {
            System.out.println(student_list.get(i).getStudent());
        }
    }

    public void print_count(){
        System.out.println("The number of students read are: " + count);
    }
}
