package contact_management;

public class user {
	private String name;
	private static String password;
	
	//getters
	public String getName() {
		return name;
	}
	public static String getPassword() {
		return password;
	}
	
	//setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
}
}