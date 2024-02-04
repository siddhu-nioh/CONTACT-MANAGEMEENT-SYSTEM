package contact_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class userDao {
	public userDao(Connection conn) {
		super();
		this.conn = conn;
	}
	

	private Connection conn;
	public static void searchuser() {
		userDao dao = new userDao(DBConnection.getConnection());
		Scanner sc = new Scanner(System.in);
		String regno = sc.next();
		user u1 = dao.viewUser(regno);
		
		if(u1==null) {
			System.out.println("No book with the given ID");
		}
		else {
		System.out.println("Here are the student details");
		System.out.println("Regno = "+u1.getName());
		System.out.println("Password = "+u1.getPassword());
		}
	}
	public user viewUser(String name) {
	    user user = null;
	    try {
	        // Assuming that 'conn' is your database connection object
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE name=?");
	        ps.setString(1, name);
	        ResultSet rs = ps.executeQuery();

	        // Iterate through the result set
	        if (rs.next()) {
	            user = new user();
	            user.setName(rs.getString("name"));  // Assuming 'name' is the column name for the user's name
	            user.setPassword(rs.getString("password"));  // Assuming 'password' is the column name for the password
	        }

	        // Close the result set and statement
	        rs.close();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}


//	public user viewUser(String name) {
//		user st = null;
//		try {
//			PreparedStatement ps = conn.prepareStatement("select *from user where name=?");
//			ps.setString(1, name);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				st = new user();
//				st.setName(rs.getString(2));
//				st.setPassword(rs.getString(3));
//				
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return st;
//	}
}
