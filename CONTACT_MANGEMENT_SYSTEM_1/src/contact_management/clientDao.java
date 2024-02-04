package contact_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class clientDao {
	public clientDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	private Connection conn;

	public boolean addContact(client b) {
		boolean f = false;
		try {
			String query = "insert into client(s_no,name,organization_name,phone_number,mail_id,Designation,adress,task_meetingDate) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, b.gets_no());
			ps.setString(2, b.getname());
			ps.setString(3, b.getorganization_name());
			ps.setInt(4, b. getphone_number());
			ps.setString(5, b.getmail_id());
			ps.setString(6, b.getadress());
			ps.setString(7, b.getDesignation());
			ps.setString(8, b.getFormattedTaskMeetingDate());			
			int i = ps.executeUpdate();
			if(i==1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public boolean updateContact(client b) {
	    boolean f = false;
	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE client SET name=?, organization_name=?, phone_number=?, adress=?, mail_id=?, Designation=?, task_meetingDate=? WHERE s_no=?");
	        ps.setString(1, b.getname());
	        ps.setString(2, b.getorganization_name());
	        ps.setInt(3, b.getphone_number());
	        ps.setString(4, b.getadress());
	        ps.setString(5, b.getmail_id());
	        ps.setString(6, b.getDesignation());
	        
	        // Convert String date to java.sql.Date
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        java.util.Date parsedDate = dateFormat.parse(b.getFormattedTaskMeetingDate());
	        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
	        ps.setDate(7, sqlDate);

	        ps.setInt(8, b.gets_no());

	        int i = ps.executeUpdate();
	        if (i == 1) {
	            f = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return f;
	}
	public boolean deleteContact(int s_no) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement("delete from client where s_no=?");
			ps.setInt(1, s_no);
			int i = ps.executeUpdate();
			if(i>=1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public client searchContact(int s_no) {
		client b = new client();
		try {
			PreparedStatement ps = conn.prepareStatement("select *from client where s_no=?");
			ps.setInt(1, s_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				b.setname(rs.getString(1));
				b.setorganization_name(rs.getString(2));
				b.setphone_number(rs.getInt(3));
				b.setmail_id(rs.getString(4));
				b.setDesignation(rs.getString(5));
				b.setadress(rs.getString(6));
				b.setTaskMeetingDate(rs.getString(7));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	public client searchTask( int s_no) {
		client b = new client();
		try {
			PreparedStatement ps = conn.prepareStatement("select *from client where s_no=?");
			ps.setInt(1, s_no);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				b.setTaskMeetingDate(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	public client searchRemind( int s_no) {
		client b = new client();
		try {
			PreparedStatement ps = conn.prepareStatement("select *from client where s_no=?");
			ps.setInt(1, s_no);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				b.settask_reminder(rs.getInt(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	public List<client> getAllContacts(){
		@SuppressWarnings("rawtypes")
		List<client> list = new ArrayList();
		client b = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("select *from client");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				b = new client();
				b.sets_no(rs.getInt(1));
				b.setname(rs.getString(2));
				b.setorganization_name(rs.getString(3));
				b.setphone_number(rs.getInt(4));
				b.setmail_id(rs.getString(5));
				b.setDesignation(rs.getString(6));
				b.setadress(rs.getString(7));
				b.setTaskMeetingDate(rs.getString(8));
				list.add(b);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
//	public client taskFollowUp(int s_no) throws Exception {
////       vendor no=searchContact(s_no);
////        if ( no!=null && task_meetingDate!= null && LocalDate.now().isEqual(task_meetingDate)) {
//            //System.out.println("hello,your meeting is scheduled today ");
//            PreparedStatement ps = conn.prepareStatement("update client set task_reminder+=1 where s_no=?");
//            client b =null;
//            ps.setInt(1, b. gettask_reminder());
//            
//            int i = ps.executeUpdate();
//			return b;
//           
//}
	public int updateTaskReminder(int s_no) throws Exception { // a new function for taskfollowup but in this we allow above for certain errors in history and report
        PreparedStatement ps = conn.prepareStatement("UPDATE client SET task_reminder = task_reminder + 1 WHERE s_no = ?");
        ps.setInt(1, s_no);

        return ps.executeUpdate();
    }
//	public void ContactHistory(int s_no,int task_reminder) throws Exception {
//        
//
//		clientDao dao=new clientDao(DBConnection.getConnection());
//        try (Scanner sc = new Scanner(System.in)) {
//		}
//        client st = dao.searchContact(s_no);
//		if(st==null) {
//			System.out.println("something went wrong");
//		}
//		else {
//		System.out.println("the contact history of the person is ");
//		System.out.println(" no .of times he contacted is "+st.gettask_reminder());
//		
//		}
//    }
}
