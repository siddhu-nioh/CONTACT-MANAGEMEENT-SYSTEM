package contact_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class partnerDao {
	public partnerDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	private Connection conn;

	public boolean addContact(partner b) {
		boolean f = false; 
		try {
			String query = "insert into partner(s_no,name,organization_name,phone_number,mail_id,Designation,adress,task_meetingDate) values(?,?,?,?,?,?)";
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
	public boolean updateContact(partner b) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement("update partner set name=?,organization_name=?,phone_number=?,mail_id=?,task_meetingDate=? where s_no=?");
			ps.setString(1, b.getname());
			ps.setString(2, b.getorganization_name());
			ps.setInt(3, b. getphone_number());
			ps.setString(4, b.getmail_id());
			ps.setString(5, b.getadress());
			ps.setString(6, b.getDesignation());
			ps.setString(7, b.getFormattedTaskMeetingDate());
			
			int i = ps.executeUpdate();
			if(i==1) {
				f = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public boolean deleteContact(int s_no) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement("delete from partner where s_no=?");
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
	public partner searchContact(int s_no) {
		partner b = new partner();
		try {
			PreparedStatement ps = conn.prepareStatement("select *from partner where s_no=?");
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
	public partner searchTask( int s_no) {
		partner b = new partner();
		try {
			PreparedStatement ps = conn.prepareStatement("select *from partner where s_no=?");
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
	public partner searchRemind( int s_no) {
		partner b = new partner();
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
	public List<partner> getAllContacts(){
		@SuppressWarnings("rawtypes")
		List<partner> list = new ArrayList();
		partner b = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("select *from partner");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				b = new partner();
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
	public int updateTaskReminder(int s_no) throws Exception { // a new function for taskfollowup but in this we allow above for certain errors in history and report
        PreparedStatement ps = conn.prepareStatement("UPDATE client SET task_reminder = task_reminder + 1 WHERE s_no = ?");
        ps.setInt(1, s_no);

        return ps.executeUpdate();
    }
//	public partner taskFollowUp(int s_no) throws Exception {
////       vendor no=searchContact(s_no);
////        if ( no!=null && task_meetingDate!= null && LocalDate.now().isEqual(task_meetingDate)) {
//            //System.out.println("hello,your meeting is scheduled today ");
//            PreparedStatement ps = conn.prepareStatement("update partner set task_reminder+=1 where s_no=?");
//            partner b =null;
//            ps.setInt(1, b. gettask_reminder());
//            
//            int i = ps.executeUpdate();
//			return b;
//           
//}
//            
//	public void ContactHistory(int s_no,int task_reminder) throws Exception {
//        
//
//		partnerDao dao=new partnerDao(DBConnection.getConnection());
//        try (Scanner sc = new Scanner(System.in)) {
//		}
//        partner st = dao.searchContact(s_no);
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
