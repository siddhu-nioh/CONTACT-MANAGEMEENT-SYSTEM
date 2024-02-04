package contact_management;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

public class partner {
	private int s_no;
	private String name;
	private String organization_name;
	private int phone_number;
	private String mail_id;
	private String Designation;
    private String adress;
    private java.util.Date task_meetingDate;
    private static int task_reminder;
	
    public String getname() {
		return name;
	}
    public int gets_no() {
		return s_no;
	}
	
	public String getorganization_name() {
		return organization_name;
	}
	
	public int getphone_number() {
		return phone_number;
	}
	
	public String getmail_id() {
		return mail_id;
	}
	public String getDesignation() {
		return Designation;
	}
	public String getadress() {
		return adress;
	}
	public String getFormattedTaskMeetingDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        format.setTimeZone(TimeZone.getTimeZone("UTC+2"));
        return format.format(this.task_meetingDate);
    }
	public static int gettask_reminder() {
		return task_reminder ;
	}
	
	
	
	/* 
	 ==============================
	 	Setters (To Set the Data) 
	 ==============================
	 */
	public void setname(String name) {
		this.name = name;
	}
	public void sets_no(int s_no) {
		this.s_no = s_no;
	}
	
	public void setorganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	
	public void setphone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	
	public void setmail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	public void setDesignation(String Designation ) {
		this.Designation= Designation;}
	public void setadress(String adress ) {
		this.adress= adress;}
	 public void setTaskMeetingDate(String task_meetingDate) throws ParseException {
	        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	        format.setTimeZone(TimeZone.getTimeZone("UTC+2"));
	        this.task_meetingDate = format.parse(task_meetingDate);
	    }
	public void settask_reminder(int task_reminder) {
		this.task_reminder = task_reminder;
	}
	public Date getTaskMeetingDate() {
        return this.task_meetingDate;
    }
}
