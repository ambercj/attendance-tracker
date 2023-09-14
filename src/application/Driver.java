package application;

import java.util.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Driver {

    //static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String letter = "";
        String letter2 = "";
        String log_file = "";
        String roster_file = "";
        String student_first = "";
        String student_last = "";
        String time = "";
        String date = "";
        int attendance_count = 0;
        ArrayList<Student> input_roster;
        ArrayList<Log> input_log;
        AttendanceLog log = new AttendanceLog();
        StudentRoster roster = new StudentRoster();
        AttendanceApp app = new AttendanceApp();

        ArrayList<Student> list_g = new ArrayList<>();
        ArrayList<Log> list_i = new ArrayList<>();
        ArrayList<Log> list_j = new ArrayList<>();
        ArrayList<Student> list_n = new ArrayList<>();
        ArrayList<Student> list_o = new ArrayList<>();
        ArrayList<Student> list_p = new ArrayList<>();



        while(!letter.equals("0")){
            System.out.println("0 - Exit program");
            System.out.println("A - load_log()");
            System.out.println("B - print_collection()");
            System.out.println("C - print_count()");
            System.out.println("D - load_log()");
            System.out.println("E - print_collection()");
            System.out.println("F - print_count()");
            System.out.println("G - list_students_not_in_class()");
            System.out.println("H - list_all_times_checking_in_and_out()");
            System.out.println("I - list_all_times_checked_in()");
            System.out.println("J - list_students_late_to_class()");
            System.out.println("K - get_first_student_to_enter()");
            System.out.println("L - print_attendance_data_for_student()");
            System.out.println("M - is_present()");
            System.out.println("N - list_all_students_checked_in()");
            System.out.println("O - list_all_students_checked_in_before()");
            System.out.println("P - list_students_attendance_count()");
            System.out.println("Q - get_first_student_to_enter()");
            System.out.println("R - print_query_list()");
            System.out.println("S - print_count()");
            System.out.print("Enter the letter for the function you want to run: ");

            letter = scanner.next();

            if(letter.equals("A")){
                System.out.print("Enter the file for the log you want to load: ");
                log_file = scanner.next();
                input_log = log.load_log(log_file);
            }
            else if(letter.equals("B")){
                log.print_collection();
            }
            else if(letter.equals("C")){
                log.print_count();
            }
            else if(letter.equals("D")){
                System.out.print("Enter the file for the roster you want to load: ");
                roster_file = scanner.next();
                input_roster = roster.load_roster(roster_file);
            }
            else if(letter.equals("E")){
                roster.print_count();
            }
            else if(letter.equals("F")){
                roster.print_count();
            }
            else if(letter.equals("G")){
                list_g = app.list_students_not_in_class();
            }
            else if(letter.equals("H")){
                System.out.print("Enter the student first name: ");
                student_first = scanner.next();
                System.out.print("Enter the student last name: ");
                student_last = scanner.next();
                Student s = new Student(student_last, student_first);
                app.list_all_times_checking_in_and_out(s);
            }
            else if(letter.equals("I")){
                list_i = app.list_all_times_checked_in();
            }
            else if(letter.equals("J")){
                System.out.print("Enter the class start time: ");
                time = scanner.next();
                System.out.print("Enter the class start date: ");
                date = scanner.next();
                list_j = app.list_students_late_to_class(time, date);
            }
            else if(letter.equals("K")){
                System.out.print("Enter the date to check: ");
                date = scanner.next();
                Student s = app.get_first_student_to_enter(date);
                System.out.println(s.getStudent());
            }
            else if(letter.equals("L")){
                System.out.print("Enter the student first name: ");
                student_first = scanner.next();
                System.out.print("Enter the student last name: ");
                student_last = scanner.next();
                Student s = new Student(student_last, student_first);
                app.print_attendance_data_for_student(s);
            }
            else if(letter.equals("M")){
                System.out.print("Enter the student first name: ");
                student_first = scanner.next();
                System.out.print("Enter the student last name: ");
                student_last = scanner.next();
                Student s = new Student(student_last, student_first);
                System.out.print("Enter the date to check: ");
                date = scanner.next();
                System.out.println(app.is_present(s, date));
            }
            else if(letter.equals("N")){
                System.out.print("Enter the date to check: ");
                date = scanner.next();
                list_n = app.list_all_students_checked_in(date);
            }
            else if(letter.equals("O")){
                System.out.print("Enter the time to check: ");
                time = scanner.next();
                System.out.println();
                System.out.print("Enter the date to check: ");
                date = scanner.next();
                list_o = app.list_all_students_checked_in_before(time, date);
            }
            else if(letter.equals("P")){
                System.out.print("Enter the attendance count to check: ");
                attendance_count = scanner.nextInt();
                list_p = app.list_students_attendance_count(attendance_count);
            }
            else if(letter.equals("Q")){
                System.out.print("Enter the date to check: ");
                date = scanner.next();
                Student s = app.get_first_student_to_enter(date);
                System.out.println(s.getStudent());
            }
            else if(letter.equals("R")){
                System.out.print("Enter the letter for the function to print: ");
                letter2 = scanner.next();
                if(letter2=="g"){
                    app.print_query_list(list_g, "");
                }
                else if(letter2=="i"){
                    app.print_query_list(list_i);
                }
                else if(letter2=="j"){
                    app.print_query_list(list_j);
                }
                else if(letter2=="n"){
                    app.print_query_list(list_n, "");
                }
                else if(letter2=="o"){
                    app.print_query_list(list_o, "");
                }
                else if(letter2=="p"){
                    app.print_query_list(list_p, "");
                }
                else{
                    System.out.println("There are no data entries for this function. Cannot print this list");
                }
            }
            else if(letter.equals("S")){
                System.out.print("Enter the letter for the function to print: ");
                letter2 = scanner.next();
                if(letter2=="g"){
                    app.print_count(list_g);
                }
                else if(letter2=="i"){
                    app.print_count(list_i);
                }
                else if(letter2=="j"){
                    app.print_count(list_j);
                }
                else if(letter2=="n"){
                    app.print_count(list_n);
                }
                else if(letter2=="o"){
                    app.print_count(list_o);
                }
                else if(letter2=="p"){
                    app.print_count(list_p);
                }
                else{
                    System.out.println("There are no data entries for this function");
                }
            }

            if(log_file!="" && roster_file!=""){
                app = new AttendanceApp(roster_file, log_file);
            }
        }
//        AttendanceApp app = new AttendanceApp("rosters.txt", "dataAllShow1stAnd2ndClass.txt");
//        AttendanceLog log = new AttendanceLog("dataAllShow1stAnd2ndClass.txt");
    }
}