package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Log {
    private String last_name;
    private String first_name;
    private String time;
    private String date;
    private ArrayList<String> time_date;

    public Log(String last_name, String first_name, String time, String date){
        time_date = new ArrayList<>();
        this.last_name = last_name;
        this.first_name = first_name;
        this.time = time;
        this.date = date;
        time_date.add(time + ", " + date);
    }

    public Log(String last_name, String first_name, String time_and_date){
        List<String> split = Arrays.asList(time_and_date.split(", "));
        this.last_name = last_name;
        this.first_name = first_name;
        this.time = split.get(0);
        this.date = split.get(1);
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getTime_date() {
        return time_date;
    }

    public void addTime_and_Date(String time, String date){
        time_date.add(time + ", " + date);
    }

    public String getLog(){
        String output = last_name + ", " + first_name + " " + time_date;
        return output;
    }

    public String getLog2(){
        String output = last_name + ", " + first_name + ", " + time + ", " + date;
        return output;
    }


}
