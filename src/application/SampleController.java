package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class SampleController {
	
	@FXML Button log_button;
	@FXML Button roster_button;
	@FXML Button submit;
	@FXML Button exit;
	@FXML MenuButton menu;
	@FXML TextField user_input;
	@FXML TextArea text_area = new TextArea();
	@FXML Label instruction;
	@FXML Label valid;
	@FXML TextField first;
	@FXML TextField last;
	@FXML TextField date;
	@FXML TextField time;
	@FXML TextField attendance_count;
	
	
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
    ArrayList<?> recent_list = new ArrayList<>();
    String roster_file = "";
    String log_file = "";
	String store_input = "";
	
	String FIRST_NAME = "";
	String LAST_NAME = "";
	String DATE = "";
	String TIME = "";
	int ATTENDANCE_COUNT = 0;
	
	String recent = "";
	
	
	
	public void get_input(ActionEvent e) {
		store_input = user_input.getText();
	}
	
	
	public void first_name_enter(ActionEvent e) {
		FIRST_NAME = first.getText();
	}
	
	public void last_name_enter(ActionEvent e) {
		LAST_NAME = last.getText();
	}
	
	public void date_enter(ActionEvent e) {
		DATE = date.getText();
	}
	
	public void time_enter(ActionEvent e) {
		TIME = time.getText();
	}
	
	public void attendance_enter(ActionEvent e) {
		ATTENDANCE_COUNT = Integer.parseInt(attendance_count.getText());
	}
	
	public void log_button_pressed(ActionEvent e) {
		input_log = log.load_log(store_input);
		log_file = store_input;
		text_area.clear();
		text_area.setText("Log loaded");
		if(input_roster!=null) {
			valid.setText("yes");
		}
	}
	
	public void roster_button_pressed(ActionEvent e) {
		input_roster = roster.load_roster(store_input);
		roster_file = store_input;
		text_area.clear();
		text_area.setText("Roster loaded");
		if(input_log!=null) {
			valid.setText("yes");
		}
	}
	
	public void B(ActionEvent e) {
		text_area.clear();
		String output = "";
		for (int i = 0; i < input_log.size(); i++) {
			output = output + input_log.get(i).getLog() + "\n";
        }
		text_area.setText(output);
	}

	public void C(ActionEvent e) {
		text_area.clear();
		text_area.setText("The number of logs read are: " + log.getCount());
	}
	
	public void E(ActionEvent e) {
		text_area.clear();
		String output = "";
		for (int i = 0; i < input_roster.size(); i++) {
			output = output + input_roster.get(i).getStudent() + "\n";
        }
		text_area.setText(output);
	}

	public void F(ActionEvent e) {
		text_area.clear();
		text_area.setText("The number of students read are: " + input_roster.size());
	}
	
	public void G(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
		list_g = app.list_students_not_in_class();
		recent_list = list_g;
		recent = "g";
	}

	public void H(ActionEvent e) {
		text_area.clear();
		String output = "";
        Student s = new Student(FIRST_NAME, LAST_NAME);
        app.list_all_times_checking_in_and_out(s);
        
        for (int i = 0; i < input_log.size(); i++) {
            if(input_log.get(i).getFirst_name().equals(s.getFirst_name()) && input_log.get(i).getLast_name().equals(s.getLast_name())){
                for (int j = 0; j < input_log.get(i).getTime_date().size(); j++) {
                	output = output + input_log.get(i).getLast_name() + ", " + input_log.get(i).getFirst_name() + ", " + input_log.get(i).getTime_date().get(j) + "\n";
                }
            }
        }
        text_area.setText(output);
	}
	
	public void I(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
		list_i = app.list_all_times_checked_in();
		recent_list = list_i;
		recent = "i";
	}

	public void J(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
//		instruction.setText("Enter the class start time: ");
//        String time = store_input;
        list_j = app.list_students_late_to_class(TIME, DATE);
        recent_list = list_j;
        recent = "j";
	}
	
	public void K(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
//		instruction.setText("Enter the date to check:  ");
//        String date = store_input;
        Student s = app.get_first_student_to_enter(DATE);
        text_area.setText(s.getStudent());
	}

	public void L(ActionEvent e) {
		text_area.clear();
		String output = "";
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
//		instruction.setText("Enter the student first name: ");
//        String student_first = store_input;
//        instruction.setText("Enter the student last name: ");
//        String student_last = store_input;
        Student s = new Student(LAST_NAME, FIRST_NAME);
        app.print_attendance_data_for_student(s);
        
        for (int i = 0; i < input_log.size(); i++) {
            if(input_log.get(i).getFirst_name().equals(s.getFirst_name()) && input_log.get(i).getLast_name().equals(s.getLast_name())){
                output = output + input_log.get(i).getLog() + "\n";
            }
        }
        text_area.setText(output);
	}
	
	public void M(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
//		instruction.setText("Enter the student first name: ");
//        String student_first = store_input;
//        instruction.setText("Enter the student last name: ");
//        String student_last = store_input;
        Student s = new Student(LAST_NAME, FIRST_NAME);
//        instruction.setText("Enter the date to check: ");
//        String date = store_input;
        text_area.setText(Boolean.toString(app.is_present(s, DATE)));
	}

	public void N(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
//		instruction.setText("Enter the date to check: ");
//        String date = store_input;
        list_n = app.list_all_students_checked_in(DATE);
        recent_list = list_n;
        recent = "n";
	}
	
	public void O(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
//		instruction.setText("Enter the time to check: ");
//        String time = store_input;
//        instruction.setText("Enter the date to check: ");
//        String date = store_input;
        list_o = app.list_all_students_checked_in_before(TIME, DATE);
        recent_list = list_o;
        recent = "o";
	}

	public void P(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
        list_p = app.list_students_attendance_count(ATTENDANCE_COUNT);
        recent_list = list_p;
        recent = "p";
	}
	
	public void Q(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
        Student s = app.get_first_student_to_enter(DATE);
        text_area.setText(s.getStudent());
	}

	public void R(ActionEvent e) {
		text_area.clear();
		String output = "";
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
		if(recent=="g") {
			for (int i = 0; i < list_g.size(); i++) {
	            output = output + list_g.get(i).getStudent() + "\n";
	        }
		}
		else if(recent=="i") {
			for (int i = 0; i < list_i.size(); i++) {
	            output = output + list_i.get(i).getLog() + "\n";
	        }
		}
		else if(recent=="j") {
			for (int i = 0; i < list_j.size(); i++) {
	            output = output + list_j.get(i).getLast_name() + ", " +  list_j.get(i).getFirst_name()+ "\n";
	        }
		}
		else if(recent=="n") {
			for (int i = 0; i < list_n.size(); i++) {
	            output = output + list_n.get(i).getStudent() + "\n";
	        }
		}
		else if(recent=="o") {
			for (int i = 0; i < list_o.size(); i++) {
	            output = output + list_o.get(i).getStudent() + "\n";
	        }
		}
		else if(recent=="p") {
			for (int i = 0; i < list_p.size(); i++) {
	            output = output + list_p.get(i).getStudent() + "\n";
	        }
		}
		
		text_area.setText(output);
	}
	
	public void S(ActionEvent e) {
		text_area.clear();
		AttendanceApp app = new AttendanceApp(roster_file, log_file);
		text_area.setText("There were " + recent_list.size() + " records for this query");
	}
	
	public void close_program(ActionEvent e) {
		Platform.exit();
	}
	
}
