package contact_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String url="jdbc:mysql://localhost:3306/contactinfo";
	private static String username="root";
	private static String password="root";
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
}