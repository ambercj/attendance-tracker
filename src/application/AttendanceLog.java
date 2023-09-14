package application;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class AttendanceLog {

    private ArrayList<Log> log_list;
    private int count = 0;

    public AttendanceLog(){

    }

    public ArrayList<Log> load_log(String file){
        log_list = new ArrayList<>();

        try{
            Scanner sc = new Scanner(new File(file));

            while(sc.hasNextLine()){
                count += 1;
                boolean entered = false;
                String line = sc.nextLine();
                List<String> student_info = Arrays.asList(line.split(", "));
                //System.out.println(student_info);
                String last_name = student_info.get(0);
                String first_name = student_info.get(1);
                String time = student_info.get(2);
                String date = student_info.get(3);

                //if student exists
                for (int i = 0; i < log_list.size(); i++) {
                    if(log_list.get(i).getFirst_name().equals(first_name) && log_list.get(i).getLast_name().equals(last_name)){
//                        student_list.get(i).setLeave_time(time);
//                        student_list.get(i).setLeave_date(date);
                        log_list.get(i).addTime_and_Date(time, date);
                        entered = true;
                    }
                }

                //if student does not exist yet
                if(!entered){
                    Log l = new Log(last_name, first_name, time, date);
                    log_list.add(l);
                }

            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        //just testing
//        for (int i = 0; i < student_list.size(); i++) {
//            System.out.println(student_list.get(i).getFirst_name() + " " + student_list.get(i).getLast_name());
//        }
        return log_list;
    }

    public void print_collection(){
        for (int i = 0; i < log_list.size(); i++) {
            System.out.println(log_list.get(i).getLog());
        }

    }

    public void print_count(){
        System.out.println("The number of logs read are: " + count);
    }

    public int getCount() {
        return count;
    }
}
