package contact_management;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class main{
	// we are creating function for add contact for vendor
public static void addContact1() throws ParseException {
	
	Scanner sc = new Scanner(System.in);
	System.out.println("ENTER s no:-");
	int s_no = sc.nextInt();
	
	System.out.println("ENTER name:-");
	String name = sc.next();

	System.out.println("ENTER THE organization NAME :");
	String organization_name = sc.next();

	System.out.println("ENTER THE phone number :-");
	int phone_number = sc.nextInt();

	System.out.println("ENTER  mail id:-");
	String email_id = sc.next();

	System.out.println("ENTER designation:-");
	String Designation = sc.next();
	
	System.out.println("ENTER adress:-");
	String adress = sc.next();
	
	System.out.println("ENTER task follow up date:-");
	String task_meetingDate = sc.next();

	// Setting the data to beans
	vendor b = new vendor();
	b.sets_no(s_no);
	b.setname(name);
	b.setorganization_name(organization_name);
	b.setphone_number(phone_number);
	b.setmail_id(email_id);
	b.setDesignation(Designation);
	b.setadress(adress);
	b.setTaskMeetingDate(task_meetingDate);

	// getting the method from dao.
	vendorDao dao = new vendorDao(DBConnection.getConnection());
	boolean f = dao.addContact(b);
	if (f) {
		System.out.println("******************contact ADDED SUCESSFULLY*****************************");
	} else {
		System.out.println("SOMETHING WENT WRONG");
		
	}
}

// deleting  and updating contascts for vendor

public static void deleteContact1() {
	Scanner sc = new Scanner(System.in);
	System.out.println("ENTER serial no of the contact TO DELETE :-");
	int id = sc.nextInt();
	vendor b = new vendor();
	b.sets_no(id);
	vendorDao dao = new vendorDao(DBConnection.getConnection());
	boolean f = dao.deleteContact(id);

	if (f) {
		System.out.println("CONTACT DETAILS DELETED SUCESSFULLY");
	} else {
		System.out.println("SOMETHING WENT WRONG");
	}

}

public static void updateContact1() throws ParseException {
	Scanner sc = new Scanner(System.in);
	vendor b = new vendor();
	System.out.println("ENTER s no:-");
	int s_no = sc.nextInt();

	System.out.println("ENTER name:-");
	String name = sc.next();

	System.out.println("ENTER THE organization NAME :");
	String organization_name = sc.next();

	System.out.println("ENTER THE phone number :-");
	int phone_number = sc.nextInt();

	System.out.println("ENTER  mail id:-");
	String email_id = sc.next();

	System.out.println("ENTER designation:-");
	String Designation = sc.next();
	
	System.out.println("ENTER adress:-");
	String adress = sc.next();
	
	System.out.println("ENTER task follow up date:-");
	String task_meetingDate = sc.next();
	b.sets_no(s_no);
	b.setname(name);
	b.setorganization_name(organization_name);
	b.setphone_number(phone_number);
	b.setmail_id(email_id);
	b.setDesignation(Designation);
	b.setadress(adress);
	b.setTaskMeetingDate(task_meetingDate);

	vendorDao dao = new vendorDao(DBConnection.getConnection());

	boolean f = dao.updateContact(b);
	if (f) {
		System.out.println("CONTACT DETAILS UPDATED SUCESSFULLY");
	} else {
		System.out.println("SOMETHING WENT WRONG");
	}
}

public static void searchContact1() {
	vendorDao dao = new vendorDao(DBConnection.getConnection());
	Scanner sc = new Scanner(System.in);
	int id = sc.nextInt();
	vendor b = dao.searchContact(id);

	if (b == null) {
		System.out.println("NO CONTACT WITH THE GIVEN ID");
	} else {
		System.out.println("HERE  ARE THE CONTACT  DETAILS");
		System.out.println("CONTACT NAME = " + b.getname());
		System.out.println("ORGANIZATION NAME = " + b.getorganization_name());
		System.out.println("PHONE NUMBER= " + b.getphone_number());
		System.out.println("MAIL ID =" + b.getmail_id());
		System.out.println("DESIGNATION =" + b.getDesignation());
		System.out.println("ADRESS  =" + b.getadress());
		System.out.println("TASK FOLLOW UP DATE =" + b.getFormattedTaskMeetingDate());
	}
}

public static void viewAllContacts1() {
	vendorDao dao = new vendorDao(DBConnection.getConnection());
	List<vendor> list = dao.getAllContacts();
	for (vendor b : list) {
		System.out.println(" VENDOR CONTACT DETAILS :-------------->");
		System.out.println("---------------------------");
		System.out.println("HERE  ARE THE CONTACT  DETAILS");
		System.out.println("CONTACT s_no = " + b.gets_no());
		System.out.println("CONTACT NAME = " + b.getname());
		System.out.println("ORGANIZATION NAME = " + b.getorganization_name());
		System.out.println("PHONE NUMBER= " + b.getphone_number());
		System.out.println("MAIL ID =" + b.getmail_id());
		System.out.println("DESIGNATION =" + b.getDesignation());
		System.out.println("ADRESS  =" + b.getadress());
		System.out.println("TASK FOLLOW UP DATE =" + b.getFormattedTaskMeetingDate());
		System.out.println("-----------------------------");

	}
}
public static void taskFollowUp1() throws Exception {try {
    vendorDao dao = new vendorDao(DBConnection.getConnection());
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter client s_no:");
    int s_no = sc.nextInt();

    vendor vendor = dao.searchTask(s_no);

    if (vendor != null) {
        LocalDate today = LocalDate.now();
        LocalDate taskMeetingDate = vendor.getTaskMeetingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (today.isEqual(taskMeetingDate)) {
            System.out.println("Hello, you have a meeting today.");
            dao.updateTaskReminder(s_no);
        }else {
        	if(today.isAfter(taskMeetingDate)){
        		System.out.println(" hello , your meeting date is not today "+vendor.getFormattedTaskMeetingDate());
        		System.out.println(" have a great day");
        	}else {
        		System.out.println(" hello ,Its look like you have catched up your meetings  "+vendor.getFormattedTaskMeetingDate());
        		System.out.println(" have a great day");
        		
        	}
        }
    }else {
    	System.out.println(" something went wrong ");
		System.out.println(" try again");
    	
    
    }
} catch (Exception e) {
    e.printStackTrace();
}}
// contact history
public static void contactHistory1() throws Exception {
	System.out.println("ENTER THE S_NO OF THE CONTACT:");
	vendorDao dao = new vendorDao(DBConnection.getConnection());
	Scanner sc = new Scanner(System.in);
    int s_no = sc.nextInt();
	vendor vendor = dao.searchRemind(s_no);

    if (vendor != null) {
        System.out.println("Contact History for client with s_no " + s_no);
        System.out.println("Task DONE : " + vendor.gettask_reminder());
    } else {
        System.out.println("Client not found with s_no " + s_no);
    }
} 

public static void Report1() throws Exception {
			vendorDao dao = new vendorDao(DBConnection.getConnection());
			System.out.println("ENTER THE S_NO OF THE CONTACT:");
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			vendor b = dao.searchRemind(id);
	  
			if(vendor.gettask_reminder()>0) {  // i canged tis to static if not working alternative things rechage to nornal int
				System.out.println("the contact history of the person is nice as meetings are followed up as schuduled -------------> ");
				
				System.out.println(" total no of successful contacted meeetings are : "+vendor.gettask_reminder());
				
				
			}
			else {
				System.out.println("something went wrong");
			}
}

// client info--------------------------
		
		public static void addContact2() throws ParseException {
			Scanner sc = new Scanner(System.in);
			System.out.println("ENTER s no:-");
			int s_no = sc.nextInt();
			System.out.println("ENTER name:-");
			String name = sc.next();

			System.out.println("ENTER THE organization NAME :");
			String organization_name = sc.next();

			System.out.println("ENTER THE phone number :-");
			int phone_number = sc.nextInt();

			System.out.println("ENTER  mail id:-");
			String email_id = sc.next();

			System.out.println("ENTER designation:-");
			String Designation = sc.next();
			
			System.out.println("ENTER adress:-");
			String adress = sc.next();
			
			System.out.println("ENTER task follow up date:-");
			String task_meetingDate = sc.next();

			// Setting the data to beans
			client b = new client();
			b.sets_no(s_no);
			b.setname(name);
			b.setorganization_name(organization_name);
			b.setphone_number(phone_number);
			b.setmail_id(email_id);
			b.setDesignation(Designation);
			b.setadress(adress);
			b.setTaskMeetingDate(task_meetingDate);

			// getting the method from dao.
			clientDao dao = new clientDao(DBConnection.getConnection());
			boolean f = dao.addContact(b);
			if (f) {
				System.out.println("******************contact ADDED SUCESSFULLY*****************************");
			} else {
				System.out.println("SOMETHING WENT WRONG");
				
			}
		}



		public static void deleteContact2() {
			Scanner sc = new Scanner(System.in);
			System.out.println("ENTER serial no of the contact TO DELETE :-");
			int id = sc.nextInt();
			client b = new client();
			b.sets_no(id);
			clientDao dao = new clientDao(DBConnection.getConnection());
			boolean f = dao.deleteContact(id);

			if (f) {
				System.out.println("CONTACT DETAILS DELETED SUCESSFULLY");
			} else {
				System.out.println("SOMETHING WENT WRONG");
			}

		}

		public static void updateContact2() throws ParseException {
			
			client b = new client();
			Scanner sc = new Scanner(System.in);
			System.out.println("ENTER s no:-");
			int s_no = sc.nextInt();
			System.out.println("ENTER name:-");
			String name = sc.next();

			System.out.println("ENTER THE organization NAME :");
			String organization_name = sc.next();

			System.out.println("ENTER THE phone number :-");
			int phone_number = sc.nextInt();

			System.out.println("ENTER  mail id:-");
			String email_id = sc.next();

			System.out.println("ENTER designation:-");
			String Designation = sc.next();
			
			System.out.println("ENTER adress:-");
			String adress = sc.next();
			
			System.out.println("ENTER task follow up date:-");
			String task_meetingDate = sc.next();

			// Setting the data to beans
			
			b.sets_no(s_no);
			b.setname(name);
			b.setorganization_name(organization_name);
			b.setphone_number(phone_number);
			b.setmail_id(email_id);
			b.setDesignation(Designation);
			b.setadress(adress);
			b.setTaskMeetingDate(task_meetingDate);
			
			clientDao dao = new clientDao(DBConnection.getConnection());

			boolean f = dao.updateContact(b);
			if (f) {
				System.out.println("CONTACT DETAILS UPDATED SUCESSFULLY");
			} else {
				System.out.println("SOMETHING WENT WRONG");
			}
		}

		public static void searchContact2() {
			clientDao dao = new clientDao(DBConnection.getConnection());
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			client b = dao.searchContact(id);

			if (b == null) {
				System.out.println("NO CONTACT WITH THE GIVEN ID");
			} else {
				System.out.println("HERE  ARE THE CONTACT  DETAILS");
				System.out.println("CONTACT NAME = " + b.getname());
				System.out.println("ORGANIZATION NAME = " + b.getorganization_name());
				System.out.println("PHONE NUMBER= " + b.getphone_number());
				System.out.println("MAIL ID =" + b.getmail_id());
				System.out.println("DESIGNATION =" + b.getDesignation());
				System.out.println("ADRESS  =" + b.getadress());
				System.out.println("TASK FOLLOW UP DATE =" + b.getFormattedTaskMeetingDate());
			}
		}

		public static void viewAllContacts2() {
			clientDao dao = new clientDao(DBConnection.getConnection());
			List<client> list = dao.getAllContacts();
			for (client b : list) {
				System.out.println("Client cat CONTACT DETAILS :-------------->");
				System.out.println("---------------------------");
				System.out.println("HERE  ARE THE CONTACT  DETAILS");
				System.out.println("CONTACT s_no = " + b.gets_no());
				System.out.println("CONTACT NAME = " + b.getname());
				System.out.println("ORGANIZATION NAME = " + b.getorganization_name());
				System.out.println("PHONE NUMBER= " + b.getphone_number());
				System.out.println("MAIL ID =" + b.getmail_id());
				System.out.println("DESIGNATION =" + b.getDesignation());
				System.out.println("ADRESS  =" + b.getadress());
				System.out.println("TASK FOLLOW UP DATE =" + b.getFormattedTaskMeetingDate());
				System.out.println("-----------------------------");

			}
		}
		/*this is working for the
		 * task follow up2 method and update reminder in client dao for every thing for theese two had to be implemented like this
		 * and by this contact history has to be printd and report*/
		public static void taskFollowUp2() { 
		try {
            clientDao dao = new clientDao(DBConnection.getConnection());
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter client s_no:");
            int s_no = sc.nextInt();

            client client = dao.searchTask(s_no);

            if (client != null) {
                LocalDate today = LocalDate.now();
                LocalDate taskMeetingDate = client.getTaskMeetingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if (today.isEqual(taskMeetingDate)) {
                    System.out.println("Hello, you have a meeting today."+client.getFormattedTaskMeetingDate());
                    dao.updateTaskReminder(s_no);
                }else {
                	if(today.isBefore(taskMeetingDate)){
                		System.out.println(" hello , your meeting date is not today "+client.getFormattedTaskMeetingDate());
                		System.out.println(" have a great day");
                	}else {
                		System.out.println(" hello ,Its look like you have catched up your meetings  ");
                		System.out.println(" have a great day");
                		
                	}
                }
            }else {
            	System.out.println(" something went wrong ");
        		System.out.println(" try again");
            	
            }
		} 
         catch (Exception e) {
            e.printStackTrace();
        }
    }
  
		public static void contactHistory2() throws Exception {
			System.out.println("ENTER THE S_NO OF THE CONTACT:");
			clientDao dao = new clientDao(DBConnection.getConnection());
		
			Scanner sc = new Scanner(System.in);
		    int s_no = sc.nextInt();
			client client = dao.searchRemind(s_no);

		    if (client != null) {
		        System.out.println("Contact History for client with s_no " + s_no);
		        System.out.println("Tasks DONE: " + client.gettask_reminder());
		    } else {
		        System.out.println("Client not found with s_no " + s_no);
		    }
		} 
				public static void Report2() throws Exception {
					clientDao dao = new clientDao(DBConnection.getConnection());
					System.out.println("ENTER THE S_NO OF THE CONTACT:");
					Scanner sc = new Scanner(System.in);
					int id = sc.nextInt();
					client b = dao.searchRemind(id);
			  
					if(client.gettask_reminder()>0) {  // i canged tis to static if not working alternative things rechage to nornal int
						System.out.println("the contact history of the person is nice as meetings are followed up as schuduled -------------> ");
						
						System.out.println(" total no of successful contacted meeetings are : "+client.gettask_reminder());
						
						
					}
					else {
						System.out.println("something went wrong");
					}}
// partner info

				public static void addContact3() throws ParseException {
					Scanner sc = new Scanner(System.in);
					System.out.println("ENTER s no:-");
					int s_no = sc.nextInt();
					System.out.println("ENTER name:-");
					String name = sc.next();

					System.out.println("ENTER THE organization NAME :");
					String organization_name = sc.next();

					System.out.println("ENTER THE phone number :-");
					int phone_number = sc.nextInt();

					System.out.println("ENTER  mail id:-");
					String email_id = sc.next();

					System.out.println("ENTER designation:-");
					String Designation = sc.next();
					
					System.out.println("ENTER adress:-");
					String adress = sc.next();
					
					System.out.println("ENTER task follow up date:-");
					String task_meetingDate = sc.next();

					// Setting the data to beans
					partner b=new partner();
					b.sets_no(s_no);
					b.setname(name);
					b.setorganization_name(organization_name);
					b.setphone_number(phone_number);
					b.setmail_id(email_id);
					b.setDesignation(Designation);
					b.setadress(adress);
					b.setTaskMeetingDate(task_meetingDate);

					// getting the method from dao.
					partnerDao dao = new partnerDao(DBConnection.getConnection());
					boolean f = dao.addContact(b);
					if (f) {
						System.out.println("******************contact ADDED SUCESSFULLY*****************************");
					} else {
						System.out.println("SOMETHING WENT WRONG");
						
					}
				}



				public static void deleteContact3() {
					Scanner sc = new Scanner(System.in);
					System.out.println("ENTER serial no of the contact TO DELETE :-");
					int id = sc.nextInt();
					partner b = new partner();
					b.sets_no(id);
					partnerDao dao = new partnerDao(DBConnection.getConnection());
					boolean f = dao.deleteContact(id);

					if (f) {
						System.out.println("CONTACT DETAILS DELETED SUCESSFULLY");
					} else {
						System.out.println("SOMETHING WENT WRONG");
					}

				}

				public static void updateContact3() throws ParseException {
					Scanner sc = new Scanner(System.in);
					System.out.println("ENTER s no:-");
					int s_no = sc.nextInt();
					System.out.println("ENTER name:-");
					String name = sc.next();

					System.out.println("ENTER THE organization NAME :");
					String organization_name = sc.next();

					System.out.println("ENTER THE phone number :-");
					int phone_number = sc.nextInt();

					System.out.println("ENTER  mail id:-");
					String email_id = sc.next();

					System.out.println("ENTER designation:-");
					String Designation = sc.next();
					
					System.out.println("ENTER adress:-");
					String adress = sc.next();
					
					System.out.println("ENTER task follow up date:-");
					String task_meetingDate = sc.next();

					// Setting the data to beans
					partner b = new partner();
					b.sets_no(s_no);
					b.setname(name);
					b.setorganization_name(organization_name);
					b.setphone_number(phone_number);
					b.setmail_id(email_id);
					b.setDesignation(Designation);
					b.setadress(adress);
					b.setTaskMeetingDate(task_meetingDate);

					partnerDao dao = new partnerDao(DBConnection.getConnection());

					boolean f = dao.updateContact(b);
					if (f) {
						System.out.println("CONTACT DETAILS UPDATED SUCESSFULLY");
					} else {
						System.out.println("SOMETHING WENT WRONG");
					}
				}

				public static void searchContact3() {
					partnerDao dao = new partnerDao(DBConnection.getConnection());
					Scanner sc = new Scanner(System.in);
					int id = sc.nextInt();
					partner b = dao.searchContact(id);

					if (b == null) {
						System.out.println("NO CONTACT WITH THE GIVEN ID");
					} else {
						System.out.println("HERE  ARE THE CONTACT  DETAILS");
						System.out.println("CONTACT NAME = " + b.getname());
						System.out.println("ORGANIZATION NAME = " + b.getorganization_name());
						System.out.println("PHONE NUMBER= " + b.getphone_number());
						System.out.println("MAIL ID =" + b.getmail_id());
						System.out.println("DESIGNATION =" + b.getDesignation());
						System.out.println("ADRESS  =" + b.getadress());
						System.out.println("TASK FOLLOW UP DATE =" + b.getFormattedTaskMeetingDate());
					}
				}

				public static void viewAllContacts3() {
					partnerDao dao = new partnerDao(DBConnection.getConnection());
					List<partner> list = dao.getAllContacts();
					for (partner b : list) {
						System.out.println("partner CONTACT DETAILS :-------------->");
						System.out.println("---------------------------");
						System.out.println("HERE  ARE THE CONTACT  DETAILS");
						System.out.println("CONTACT s_no = " + b.gets_no());
						System.out.println("CONTACT NAME = " + b.getname());
						System.out.println("ORGANIZATION NAME = " + b.getorganization_name());
						System.out.println("PHONE NUMBER= " + b.getphone_number());
						System.out.println("MAIL ID =" + b.getmail_id());
						System.out.println("DESIGNATION =" + b.getDesignation());
						System.out.println("ADRESS  =" + b.getadress());
						System.out.println("TASK FOLLOW UP DATE =" + b.getFormattedTaskMeetingDate());
						System.out.println("-----------------------------");

					}
				}
				public static void taskFollowUp3() { try {
		            partnerDao dao = new partnerDao(DBConnection.getConnection());
		            Scanner sc = new Scanner(System.in);

		            System.out.println("Enter client s_no:");
		            int s_no = sc.nextInt();

		            partner partner = dao.searchTask(s_no);

		            if (partner != null) {
		                LocalDate today = LocalDate.now();
		                LocalDate taskMeetingDate = partner.getTaskMeetingDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		                if (today.isEqual(taskMeetingDate)) {
		                    System.out.println("Hello, you have a meeting today."+partner.getFormattedTaskMeetingDate());
		                    dao.updateTaskReminder(s_no);
		                }else {
		                	if(today.isAfter(taskMeetingDate)){
		                		System.out.println(" hello , your meeting date is not today "+partner.getFormattedTaskMeetingDate());
		                		System.out.println(" have a great day");
		                	}else {
		                		System.out.println(" hello ,Its look like you have catched up your meetings  ");
		                		System.out.println(" have a great day");
		                		
		                	}
		                }
		            }else {
		            	System.out.println(" something went wrong ");
		        		System.out.println(" try again");
		            	
		            }
				} 
		         catch (Exception e) {
		            e.printStackTrace();
		        }}
				         
				public static void contactHistory3() throws Exception {
					System.out.println("ENTER THE S_NO OF THE CONTACT:");
					partnerDao dao = new partnerDao(DBConnection.getConnection());
				
					Scanner sc = new Scanner(System.in);
				    int s_no = sc.nextInt();
					partner partner = dao.searchRemind(s_no);

				    if (partner != null) {
				        System.out.println("Contact History for client with s_no " + s_no);
				        System.out.println("Tasks DONE: " + partner.gettask_reminder());
				    } else {
				        System.out.println("Client not found with s_no " + s_no);
				    }
				} 
						public static void Report3() throws Exception {
							partnerDao dao = new partnerDao(DBConnection.getConnection());
							System.out.println("ENTER THE S_NO OF THE CONTACT:");
							Scanner sc = new Scanner(System.in);
							int id = sc.nextInt();
							partner b = dao.searchRemind(id);
					  
							if(partner.gettask_reminder()>0) {  // i canged tis to static if not working alternative things rechage to nornal int
								System.out.println("the contact history of the person is nice as meetings are followed up as schuduled -------------> ");
								
								System.out.println(" total no of successful contacted meeetings are : "+partner.gettask_reminder());
								
								
							}
							else {
								System.out.println("something went wrong");
							}
							}						
public static void main(String[] args) throws Exception {
	System.out.println("*****contactinfo LOGIN page********");
		Scanner sc = new Scanner(System.in);
		boolean value=true;
		while (value) {
			System.out.println("*****ADMIN LOGIN********");/// the users are already given in mysql and for those certain users the login will be done
				System.out.println("ENTER YOUR USER NAME: ");
				String username = sc.next();
				System.out.println("ENTER PASSWORD");
				String upassword = sc.next();
		userDao userDao = new userDao(DBConnection.getConnection());
		user u1 = userDao.viewUser(username);

		if (u1 != null && user.getPassword().equals(upassword)) {
			System.out.println("*******************");
			System.out.println("LOGIN SUCCESFULL");
			System.out.println("*******************");

			boolean value1 = true;
			while (value1) {
				System.out.println("SELECT A CATEGORY");
				System.out.println("1.VENDOR ");
				System.out.println("2.CLIENT ");
				System.out.println("3.PARTNER ");
				int option = sc.nextInt();
				switch(option) {
				case 1:
					boolean value2=true;
					while (value2) {
					System.out.println("*****************vendor  CATEGORY*****************");
					System.out.println("SELECT YOUR CHOICE");
					System.out.println("1.ADD A CONTACT INFO");
					System.out.println("2.DELETE A CONTACT INFO");
					System.out.println("3.UPDATE A CONTACT INFO");
					System.out.println("4.SEARCH A CONTACT INFO");
					System.out.println("5.TASK FOLLOW UP");  // WIIL SEND A REMINDER FOR MEETINGS FOLLOW UP
					System.out.println("6.CONTACT HISTORY WITH ALL"); // WHEN MEETING DONE CONTACT HISTORY OF A PERSON WILL BE UPDATED
					System.out.println("7.CONTACTING THROUGH MAIL");// USE LINKS TO DIRECTLY OPEN 
					System.out.println("8.CONTACTING THROUGH WHATSAPP");
					System.out.println("9.VIEW ALL CONTACTS INFO OF CLIENT");
					System.out.println("10.VIEW ALL CONTACTS INFO OF ORGANIZATION");
					System.out.println("11.VIew analysys report of contact");
					System.out.println("12.logout");// if he followed through meetings weel print that he he  maintained schedules well 
					// if he followed through meetings weel print that he he  maintained schedules well 
					int choice1=sc.nextInt();
					switch(choice1) {
					case 1:
						System.out.println("*** ADD A CONTACT ***");
						addContact1();
						break;
					case 2:
						System.out.println("*** DELETE A CONTACT ***");
						deleteContact1();
						break;
					case 3:
						System.out.println("*** UPDATE CONTACT DETAILS ***");
						updateContact1();
						break;
					case 4:
						System.out.println("*** SEARCH A CONTACT ***");
						searchContact1();
						break;
					case 5:
						System.out.println("*** TASK FOLLOW UP ***");
						taskFollowUp1();
						break;
					case 6:
						System.out.println("*** CONTACT HISTORY WITH ALL ***");
						contactHistory1();
						break;
					case 7:
						System.out.println("*** CONTACTING THROUGH MAIL ***");
						Desktop desktop = Desktop.getDesktop();
						URI oURL1 = new URI("https://mail.google.com/");
						desktop.browse(oURL1);
						
						break;
					case 8:
						System.out.println("*** CONTACTING THROUGH WHATSAPP ***");
						Desktop desktop1 = Desktop.getDesktop();
						URI oURL11 = new URI("https://web.whatsapp.com/");
						desktop1.browse(oURL11);
						
						break;
					case 9:
						System.out.println("*** VIEW ALL CONTACTS INFO OF CLIENT ***");
						viewAllContacts1();
						break;
					case 10:
						System.out.println("***VIEW ALL CONTACTS INFO OF ORGANIZATION***");
						viewAllContacts1();
						viewAllContacts2();
						viewAllContacts3();
						
						break;
					case 11:
						System.out.println("*** VIew analysys report of contact ***");
						Report1();
						
						break;
					case 12:
						System.out.println("*** THANKS FOR USING ***");
						System.out.println("*** Logged out successfully ***");
						value2 = false;
						break;
					}
					}
					break;
				case 2:	
					boolean value3=true;
					while (value3) {
					System.out.println("*****************client CATEGORY*****************");
					System.out.println("1.ADD A CONTACT INFO");
					System.out.println("2.DELETE A CONTACT INFO");
					System.out.println("3.UPDATE A CONTACT INFO");
					System.out.println("4.SEARCH A CONTACT INFO");
					System.out.println("5.TASK FOLLOW UP");  // WIIL SEND A REMINDER FOR MEETINGS FOLLOW UP
					System.out.println("6.CONTACT HISTORY WITH ALL"); // WHEN MEETING DONE CONTACT HISTORY OF A PERSON WILL BE UPDATED
					System.out.println("7.CONTACTING THROUGH MAIL");// USE LINKS TO DIRECTLY OPEN 
					System.out.println("8.CONTACTING THROUGH WHATSAPP");
					System.out.println("9.VIEW ALL CONTACTS INFO OF CLIENT");
					System.out.println("10.VIEW ALL CONTACTS INFO OF ORGANIZATION");
					System.out.println("11.VIew analysys report of contact"); // if he followed through meetings weel print that he he  maintained schedules well 
					System.out.println("12.logout");// i
					int choice2=sc.nextInt();
					switch(choice2) {
					case 1:
						System.out.println("*** ADD A CONTACT ***");
						addContact2();
						break;
					case 2:
						System.out.println("*** DELETE A CONTACT ***");
						deleteContact2();
						break;
					case 3:
						System.out.println("*** UPDATE CONTACT DETAILS ***");
						updateContact2();
						break;
					case 4:
						System.out.println("*** SEARCH A CONTACT ***");
						searchContact2();
						break;
					case 5:
						System.out.println("*** TASK FOLLOW UP ***");
						taskFollowUp2();
						break;
					case 6:
						System.out.println("*** CONTACT HISTORY WITH ALL ***");
						contactHistory2();
						break;
					case 7:
						System.out.println("*** CONTACTING THROUGH MAIL ***");
						Desktop desktop = Desktop.getDesktop();
						URI oURL1 = new URI("https://mail.google.com/");
						desktop.browse(oURL1);
						
						break;
					case 8:
						System.out.println("*** CONTACTING THROUGH WHATSAPP ***");
						Desktop desktop1 = Desktop.getDesktop();
						URI oURL11 = new URI("https://web.whatsapp.com/");
						desktop1.browse(oURL11);
						
						break;
					case 9:
						System.out.println("*** VIEW ALL CONTACTS INFO OF CLIENT ***");
						viewAllContacts2();
						break;
					case 10:
						System.out.println("***VIEW ALL CONTACTS INFO OF ORGANIZATION***");
						viewAllContacts1();
						viewAllContacts2();
						viewAllContacts3();
						
						break;
					case 11:
						System.out.println("*** VIew analysys report of contact ***");
						Report2();
						
						break;
					case 12:
						System.out.println("*** THANKS FOR USING ***");
						System.out.println("*** Logged out successfully ***");
						value3 = false;
						break;
					}}
					break;
				case 3:	
					boolean value4=true;
					while (value4) {
					System.out.println("*****************PARTNER CATEGORY*****************");
					System.out.println("1.ADD A CONTACT INFO");
					System.out.println("2.DELETE A CONTACT INFO");
					System.out.println("3.UPDATE A CONTACT INFO");
					System.out.println("4.SEARCH A CONTACT INFO");
					System.out.println("5.TASK FOLLOW UP");  // WIIL SEND A REMINDER FOR MEETINGS FOLLOW UP
					System.out.println("6.CONTACT HISTORY WITH ALL"); // WHEN MEETING DONE CONTACT HISTORY OF A PERSON WILL BE UPDATED
					System.out.println("7.CONTACTING THROUGH MAIL");// USE LINKS TO DIRECTLY OPEN 
					System.out.println("8.CONTACTING THROUGH WHATSAPP");
					System.out.println("9.VIEW ALL CONTACTS INFO OF CLIENT");
					System.out.println("10.VIEW ALL CONTACTS INFO OF ORGANIZATION");
					System.out.println("11.VIew analysys report of contact"); // if he followed through meetings weel print that he he  maintained schedules well 
					System.out.println("12.logout");// i
					int choice3=sc.nextInt();
					switch(choice3) {
					case 1:
						System.out.println("*** ADD A CONTACT ***");
						addContact3();
						break;
					case 2:
						System.out.println("*** DELETE A CONTACT ***");
						deleteContact3();
						break;
					case 3:
						System.out.println("*** UPDATE CONTACT DETAILS ***");
						updateContact3();
						break;
					case 4:
						System.out.println("*** SEARCH A CONTACT ***");
						searchContact3();
						break;
					case 5:
						System.out.println("*** TASK FOLLOW UP ***");
						taskFollowUp3();
						break;
					case 6:
						System.out.println("*** CONTACT HISTORY WITH ALL ***");
						contactHistory3();
						break;
					case 7:
						System.out.println("*** CONTACTING THROUGH MAIL ***");
						Desktop desktop = Desktop.getDesktop();
						URI oURL1 = new URI("https://mail.google.com/");
						desktop.browse(oURL1);
						
						break;
					case 8:
						System.out.println("*** CONTACTING THROUGH WHATSAPP ***");
						Desktop desktop1 = Desktop.getDesktop();
						URI oURL11 = new URI("https://web.whatsapp.com/");
						desktop1.browse(oURL11);
						
						break;
					case 9:
						System.out.println("*** VIEW ALL CONTACTS INFO OF CLIENT ***");
						viewAllContacts3();
						break;
					case 10:
						System.out.println("***VIEW ALL CONTACTS INFO OF ORGANIZATION***");
						viewAllContacts1();
						viewAllContacts2();
						viewAllContacts3();
						
						break;
					case 11:
						System.out.println("*** VIew analysys report of contact ***");
						Report3();
						
						break;
					case 12:
						System.out.println("*** THANKS FOR USING ***");
						System.out.println("*** Logged out successfully ***");
						value4 = false;
						break;
					}
					}
					
				break;
			}
				break;
				
	}
}else {
	System.out.println("invalid credentials try again");
}
		}}}
