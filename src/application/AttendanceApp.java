package application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttendanceApp {

    private ArrayList<Student> input_roster;
    private ArrayList<Log> input_log;
    private ArrayList<Log> check_in;

    public AttendanceApp(){

    }

    public AttendanceApp(String roster_file, String log_file){
        StudentRoster roster = new StudentRoster();
        input_roster = roster.load_roster(roster_file);
//        roster.print_collection();
//        roster.print_count();

        AttendanceLog log = new AttendanceLog();
        input_log = log.load_log(log_file);
//        log.print_collection();
//        log.print_count();

//        System.out.println(list_students_not_in_class(not_in_class).get(0).getStudent());

//        Student student1 = new Student("Nolan", "Lucas");
//        list_all_times_checking_in_and_out(student1);

        check_in = list_all_times_checked_in();
//        list_students_late_to_class("18:43:08, 11/3/2022");

//        print_attendance_data_for_student(student1);

//        System.out.println(is_present(student1, "11/2/2022"));
//        list_all_students_checked_in("11/2/2022");
//        list_all_students_checked_in_before("16:57:17", "11/2/2022");
//        list_students_attendance_count(2);
//        System.out.println(get_first_student_to_enter("11/5/2022").getStudent());
//        print_query_list(list_all_students_checked_in("11/2/2022"), "hi");
//        print_query_list(list_all_times_checked_in());

    }

    public ArrayList<Student> list_students_not_in_class(){
        ArrayList<Student> noshow_list = new ArrayList<>();
        for (int i = 0; i < input_roster.size(); i++) {
            boolean noshow = true;
            for (int j = 0; j < input_log.size(); j++) {

                if(input_roster.get(i).getFirst_name().equals(input_log.get(j).getFirst_name()) && input_roster.get(i).getLast_name().equals(input_log.get(j).getLast_name())){
                    noshow = false;
                }
            }
            if(noshow){
                noshow_list.add(input_roster.get(i));
            }
        }

        return noshow_list;
    }

    public void list_all_times_checking_in_and_out(Student s){
        for (int i = 0; i < input_log.size(); i++) {
            if(input_log.get(i).getFirst_name().equals(s.getFirst_name()) && input_log.get(i).getLast_name().equals(s.getLast_name())){
                for (int j = 0; j < input_log.get(i).getTime_date().size(); j++) {
                    System.out.println(input_log.get(i).getLast_name() + ", " + input_log.get(i).getFirst_name() + ", " + input_log.get(i).getTime_date().get(j));
                }
            }
        }
    }

    public ArrayList<Log> list_all_times_checked_in(){
        ArrayList<Log> check_in = new ArrayList<>();
        for (int i = 0; i < input_log.size(); i++) {
            Log swipe = new Log(input_log.get(i).getLast_name(), input_log.get(i).getFirst_name(), input_log.get(i).getTime_date().get(0));
            check_in.add(swipe);
        }

        //for testing
//        for (int i = 0; i < first_swipe.size(); i++) {
//            System.out.println(first_swipe.get(i).getLog2());
//        }

        return check_in;
    }

    public ArrayList<Log> list_students_late_to_class(String start_time, String start_date){
        ArrayList<Log> late_student = new ArrayList<>();
//        List<String> start_time_and_date = Arrays.asList(start_time.split(", "));
        List<String> start_time_list = Arrays.asList(start_time.split(":"));
        List<String> start_date_list = Arrays.asList(start_date.split("/"));
        int hour = Integer.parseInt(start_time_list.get(0));
        int minute = Integer.parseInt(start_time_list.get(1));
        int second = Integer.parseInt(start_time_list.get(2));
        int month = Integer.parseInt(start_date_list.get(0));
        int date = Integer.parseInt(start_date_list.get(1));
        int year = Integer.parseInt(start_date_list.get(2));


        for (int i = 0; i < check_in.size(); i++) {
            boolean late = false;
            String student_time = check_in.get(i).getTime();
            String student_date = check_in.get(i).getDate();
            List<String> student_time_list = Arrays.asList(student_time.split(":"));
            List<String> student_date_list = Arrays.asList(student_date.split("/"));
            int s_hour = Integer.parseInt(student_time_list.get(0));
            int s_minute = Integer.parseInt(student_time_list.get(1));
            int s_second = Integer.parseInt(student_time_list.get(2));
            int s_month = Integer.parseInt(student_date_list.get(0));
            int s_date = Integer.parseInt(student_date_list.get(1));
            int s_year = Integer.parseInt(student_date_list.get(2));
            if(s_year>year){
                late = true;
            }
            else if(s_year==year && s_month>month){
                late = true;
            }
            else if(s_month==month && s_date>date){
                late = true;
            }
            else if(s_hour>hour){
                late = true;
            }
            else if(s_hour==hour && s_minute>minute){
                late = true;
            }
            else if(s_minute==minute && s_second>second){
                late = true;
            }

            if(late){
                late_student.add(check_in.get(i));
            }
        }

        //just for testing
//        for (int i = 0; i < late_student.size(); i++) {
//            System.out.println(late_student.get(i).getLog2());
//        }

        return late_student;
    }

    public void print_attendance_data_for_student(Student s){
        for (int i = 0; i < input_log.size(); i++) {
            if(input_log.get(i).getFirst_name().equals(s.getFirst_name()) && input_log.get(i).getLast_name().equals(s.getLast_name())){
                System.out.println(input_log.get(i).getLog());
            }
        }
    }

    public boolean is_present(Student s, String date){
        for (int i = 0; i < check_in.size(); i++) {

            if(s.getFirst_name().equals(check_in.get(i).getFirst_name()) && s.getLast_name().equals(check_in.get(i).getLast_name()) && date.equals(check_in.get(i).getDate())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Student> list_all_students_checked_in(String date){
        ArrayList<Student> present_list = new ArrayList<>();
        for (int i = 0; i < check_in.size(); i++) {
            Student temp = new Student(check_in.get(i).getLast_name(), check_in.get(i).getFirst_name());
            if(is_present(temp, date)){
                present_list.add(temp);
            }
        }

        //just testing
//        for (int i = 0; i < present_list.size(); i++) {
//            System.out.println(present_list.get(i).getStudent());
//        }
        return present_list;
    }

    public ArrayList<Student> list_all_students_checked_in_before(String time, String date){
        ArrayList<Student> before_list = new ArrayList<>();
        List<String> time_list = Arrays.asList(time.split(":"));
        int hour = Integer.parseInt(time_list.get(0));
        int minute = Integer.parseInt(time_list.get(1));
        int second = Integer.parseInt(time_list.get(2));
        for (int i = 0; i < check_in.size(); i++) {
            List<String> student_time_list = Arrays.asList(check_in.get(i).getTime().split(":"));
            int s_hour = Integer.parseInt(student_time_list.get(0));
            int s_minute = Integer.parseInt(student_time_list.get(1));
            int s_second = Integer.parseInt(student_time_list.get(2));

            Student temp = new Student(check_in.get(i).getLast_name(), check_in.get(i).getFirst_name());
            if(is_present(temp, date) && s_hour<hour){
                before_list.add(temp);
            }
            else if(is_present(temp, date) && s_hour==hour && s_minute<minute){
                before_list.add(temp);
            }
            else if(is_present(temp, date) && s_minute==minute && s_second<second){
                before_list.add(temp);
            }
        }

        //just testing
//        for (int i = 0; i < before_list.size(); i++) {
//            System.out.println(before_list.get(i).getStudent());
//        }

        return before_list;
    }

    public ArrayList<Student> list_students_attendance_count(int total){
        ArrayList<Student> attendance_count_list = new ArrayList<>();
        for (int i = 0; i < input_log.size(); i++) {
            if(input_log.get(i).getTime_date().size()==total){
                Student temp = new Student(input_log.get(i).getLast_name(), input_log.get(i).getFirst_name());
                attendance_count_list.add(temp);
            }
        }

        //just testing
//        for (int i = 0; i < attendance_count_list.size(); i++) {
//            System.out.println(attendance_count_list.get(i).getStudent());
//        }

        return attendance_count_list;
    }

    public Student get_first_student_to_enter(String date){
        ArrayList<Log> today = new ArrayList<>();
        for (int i = 0; i < input_log.size(); i++) {
            for (int j = 0; j < input_log.get(i).getTime_date().size(); j++) {
                List<String> split = Arrays.asList(input_log.get(i).getTime_date().get(j).split(", "));
                if(split.get(1).equals(date)){
                    today.add(input_log.get(i));
                }
            }
        }
        Log earliest = today.get(0);
        List<String> earliest_time_list = Arrays.asList(earliest.getTime().split(":"));
        int earliest_hour = Integer.parseInt(earliest_time_list.get(0));
        int earliest_minute = Integer.parseInt(earliest_time_list.get(1));
        int earliest_second = Integer.parseInt(earliest_time_list.get(2));
        for (int i = 0; i < today.size(); i++) {
            List<String> time_list = Arrays.asList(today.get(i).getTime().split(":"));
            int hour = Integer.parseInt(time_list.get(0));
            int minute = Integer.parseInt(time_list.get(1));
            int second = Integer.parseInt(time_list.get(2));
            boolean update = false;
            if(earliest_hour>hour){
                update = true;
            }
            else if(earliest_hour==hour && earliest_minute>minute){
                update = true;
            }
            else if(earliest_minute==minute && earliest_second>second){
                update = true;
            }

            if(update){
                earliest = today.get(i);
            }
        }
        Student e = new Student(earliest.getLast_name(), earliest.getFirst_name());
        return e;
    }

    public void print_query_list(ArrayList<Log> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getLog());
        }
    }

    public void print_query_list(ArrayList<Student> list, String type){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getStudent());
        }
    }

    public void print_count(ArrayList<?> list){
        System.out.println("There were " + list.size() + " records for this query");
    }

}
