package bank.com;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		mainOperation();
		System.out.println("Thanks for using the application. ");
	}

	@SuppressWarnings("resource")
	public static void mainOperation() {
//		UserSystem admin = new UserSystem();
		Scanner sc = new Scanner(System.in);
		Scanner str = new Scanner(System.in);
		int ch = 0, opr = 1, pin = 0, uid = 1;
		String username = "null", adminId = "", passcode = "";
		UserSystem sys = new UserSystem();
		sys = sys.addUser(sys, uid, 1234, "Lionel", 1000);

		while (opr != 0) {

			System.out.println("Enter operation. Enter zer0 to exit. ");
			System.out.println("1. Login ");
			System.out.println("2. Login as system administrator ");
			System.out.println("3. About ");
			System.out.print("Choice: ");
			ch = sc.nextInt();

			switch (ch) {
			case 0: {
				System.out.println("Exiting .. ");
				break;
			}
			case 1: {
				System.out.print("Enter User ID: ");
				uid = sc.nextInt();
				System.out.print("Enter PIN: ");
				pin = sc.nextInt();

				if (sys.isLogged(sys, uid, pin)) {
					// create session and display user operations.
					while (opr != 0) {

						sys.userOperation();
						opr = sc.nextInt();

						switch (opr) {
						case 0: {
							System.out.println("Exiting .. ");
							break;
						}
						case 1: {
							sys.getUser(sys, uid); // complete . exception handling req.
							break;
						}
						case 2: {
							sys.updateBalance(sys, uid, opr); // complete . exception handling req.
							break;
						}
						case 3: {
							sys.updateBalance(sys, uid, opr); // complete . exception handling req.
							break;
						}
						case 4: {
							System.out.println("Currently working on the functionality...");
							break;
						}

						case 5: {
							System.out.println("Enter operation. Enter zer0 to exit");
							System.out.println("1. Update username ");
							System.out.println("2. Update Pin ");
							System.out.println("3.Exit ");
							opr = sc.nextInt();

							switch (opr) {
							case 0: {
								System.out.println("Exiting .. ");
								break;
							}
							case 1: {
								sys.updateUsername(sys, uid);
								break;
							}
							case 2: {
								sys.updatePin(sys, uid);
								break;
							}

							case 3: {
								System.out.println("Exiting .. ");
							}

							default:
								System.out.println("Invalid operations. Exiting .. ");
							}

							break;
						}

						default:
							System.out.println("Invalid choice");
						}

					}
				} else {
					System.out.println("Wrong Credentials..");
				}
				break;
			}

			case 2: {
				System.out.print("Enter Admin ID: ");
				adminId = str.nextLine();
				System.out.print("Enter Password: ");
				passcode = str.nextLine();
				if (sys.adminLog(adminId, passcode)) {
					while (opr != 0) {
						sys.adminOperation();
						opr = sc.nextInt(); // select a choice from admin menu

						switch (opr) {
						case 1: {
							System.out.print("Enter User name: ");
							username = str.nextLine();
							uid = sys.size(sys) + 1;
							sys = sys.addUser(sys, uid, 1234, username, 1000);
							break;
						}

						case 2: {
							System.out.print("Enter User id: ");
							uid = sc.nextInt();
							sys.deleteUserById(sys, uid);
							break;
						}

						case 3: {
							System.out.print("Enter User id: ");
							uid = sc.nextInt();
							sys.updateUsername(sys, uid);
							break;
						}

						case 4: {
							System.out.print("Enter User id: ");
							uid = sc.nextInt();
							sys.getUser(sys, uid);
							break;
						}

						case 5: {
							sys.printUsers(sys);
						}

						}

					}
				} else {
					System.out.println("Wrong Credentials..");
				}

				break;
			}

			case 3: {
				sys.about();
				break;
			}

			default:
				System.out.println("Invalid choice");
			}

			// if logged in as user, call user operations else admin operations. endless
			// run.
			return;
		}
		sc.close();
		str.close();
	}
}
