package bank.com;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		mainOperation();
	}
	

	public static void mainOperation() {
		Scanner sc = new Scanner(System.in);
		int ch = 0, pin=0, uid=1;
		String username = "null";
		UserSystem sys = new UserSystem();
		System.out.println("Enter operation");
		System.out.println("1. Login ");
		System.out.println("2. Login as system administrator ");
		System.out.println("3. About ");
		System.out.print("Choice: ");
//		ch = sc.nextInt();

		switch (ch) {
		case 1: {
			System.out.print("Enter User ID: ");
			uid = sc.nextInt();
			System.out.print("Enter PIN: ");
			pin = sc.nextInt();
						
			if(sys.isLogged(sys, uid, pin)) {
					// create session and display user operations.
				int opr = 0;
				sys.userOperation();
				opr = sc.nextInt();
				
				switch(opr) {
				case 1: {
					sys.getUser(sys, uid); // complete . exception handling req.
					break;
				}
				case 2: {
					sys.updateBalance(sys, uid); // complete . exception handling req.
					break;
				}
				default:
					System.out.println("Invalid choice");
			}
				
				} else {
					System.out.println("Wrong Credentials..");
			}
			break;
		}
		case 2: {

			break;
		}
		case 3: {

			break;
		}
		default:
			System.out.println("Invalid choice");
		}

		sc.close();
		
//		UserSystem users = new UserSystem(); 
		// below are administrative operations . Write to a file every time a user is created..
		sys = sys.addUser(sys, 1, 1234, "Lionel", 1000);
		sys = sys.addUser(sys, 2, 1234, "Spyke", 369);
		sys = sys.addUser(sys, 3, 1234, "James", 346);
		sys = sys.addUser(sys, 4, 1234, "eCer", 520);
		sys = sys.addUser(sys, 5, 1234, "kefreg", 10634);
//		System.out.println();
//		sys.printUsers(sys);
//		sys.getUser(sys, 5);
//		sys.deleteUserById(sys, 54);
//		sys.printUsers(sys);
		sys.updatePin(sys, 1);
//		sys.printUsers(sys);
//		System.out.println(sys);
//		System.out.println(sys);

		// if logged in as user, call user operations else admin operations. endless
		// run.
		return;
	}
}
