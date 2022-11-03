package bookshopsubscriber;
import bookshoppublisher.BookshopPublisher;

import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


public class BookshopSubscriberActivator implements BundleActivator {

	ServiceReference BookshopServiceReference, BookshopServiceReference2;
	
	public void start(BundleContext arg0) throws Exception {
		
		System.out.println("Bookshop Subscriber started ");
		
		BookshopServiceReference = arg0.getServiceReference(BookshopPublisher.class.getName());
		BookshopPublisher bookshopObj = (BookshopPublisher)arg0.getService(BookshopServiceReference);
		
		Scanner scanner = new Scanner(System.in);
		int choice = 1;
		String userName,pass = null;
		byte[] hashedPass;
		
			System.out.println("------------	 Wellcome to our Bookshop	------------");
			System.out.println("------------	 Press 1 for Register		------------");
			System.out.println("------------	 Press 2 for Login		------------");
			
			choice = scanner.nextInt();
		
		if(choice == 1) {
			
			System.out.print("\nPlease enter a user Name : \n");
			userName = scanner.next();
			
			Boolean userVerify = bookshopObj.checkUser(userName);
			
			if(!userVerify) {
				String status = null;
				
				while(status != "OK") {
				System.out.print("Please enter Password : \n");
				pass = scanner.next();
				
				status = bookshopObj.checkPassword(pass);
				if(status != "OK") {
					System.out.println(status);
				}
			}
				
			hashedPass = bookshopObj.genarateHashedPass(pass);
			System.out.println("Encrypted Password: " + hashedPass);
			System.out.println("\nLoarding....");
			scanner.nextInt();
			}
			
			else {
				System.out.println("Username: " + userName +" already exist in the system");
				System.out.println("Please try again ! ");
				scanner.nextInt();
			}
			
		}
		
		else if(choice == 2) {
			System.out.print("Enter user Name : \n");
			scanner.nextInt();
		}else System.out.println("Unknown input Please try again ");
		scanner.nextInt();
		
	}
	
	

	public void stop(BundleContext arg0) throws Exception {
		System.out.print("Bookshop Subscriber Stopped ");
		arg0.ungetService(BookshopServiceReference);
	}

}
