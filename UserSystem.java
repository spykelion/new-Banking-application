package bank.com;

import java.util.Scanner;

// All system operations here: [ user + system administrator ]
// Auto generate userid

public class UserSystem {
	// system administrator can carry out CRUD operations......
	private final String adminID = "admin1";
	private final String passcode = "pass1234";
	User head;

	class User {
		private int id;
		private int pin;
		private String name;
		private int balance;
		User next;

//		User(){} 
		User(int uid, int upin, String uname, int ubalance) {
			id = uid;
			pin = upin;
			name = uname;
			balance = ubalance;
			next = null;
		}

		// user can carry out private operations after a session has been created.

		public String toString() {
			return "ID: " + this.id + " - Name: " + this.name + " - Balance: " + this.balance;
		}

	} // end User.class.

	public String getAminId() {
		return this.adminID;
	}

	public String getPasscode() {
		return this.passcode;
	}

	public boolean adminLog(String id, String pass) {
		System.out.print(".........................");
		if (this.adminID.equals(id) && this.passcode.equals(pass)) {
			return true;
		}
		return false;
	}

	public void adminOperation() { // update db after every opr Except[4]
		System.out.println("Enter operation. Enter zer0 to exit. ");
		System.out.println("1. Add user ");
		System.out.println("2. Delete user ");
		System.out.println("3. Update user info. Exception [Pin] ");
		System.out.println("4. Search user and print info ");
		System.out.println("5. Show database with all user info" + ". NB: Pin is hidden for security reasons.");
	}

	public void userOperation() {
		System.out.println("Enter operation. Enter zer0 to exit...");
		System.out.println("1. Check account balance ");
		System.out.println("2. Deposit ");
		System.out.println("3. Withdraw ");
		System.out.println("4. Calculate Interest ");
		System.out.println("5. Update your info. ");
		System.out.println("6. AboutMe . Download account info (including previous transactions)"); // write to a file.
	}

	public int size(UserSystem users) {
		int size = 0;
		User currentUser = users.head;

		while (currentUser != null) {
			size++;
			currentUser = currentUser.next;
		}

		return size;
	}

	public UserSystem addUser(UserSystem users, int id, int pin, String name, int balance) {
		User newuser = new User(id, pin, name, balance);

		if (users.head == null) {
			users.head = newuser;
		} else {
			User last = users.head;
			while (last.next != null) {
				last = last.next;
			}
			last.next = newuser;
		}

		newuser.next = null;
		System.out.println("Successfully added \'" + newuser.name + "\' -- Uid: " + newuser.id); // For debugging.
		return users;
	}

	public void printUsers(UserSystem users) {
		if (size(users) == 0) {
			System.out.println("No users");
			return;
		}
		User user = users.head;
		while (user != null) {
			System.out.println(user.toString());
			user = user.next;
		}

		return;
	}

	public boolean isLogged(UserSystem users, int uid, int pin) {

		User user = users.head;
		while (user != null) {
			if (user.id == uid) {
				if (user.pin == pin) {
					return true;
				}
			}
			user = user.next;
		}

		return false;

	}

	public int getUser(UserSystem users, int uid) {
		if (size(users) == 0) {
			System.out.println("No users");
			return -1;
		}

		User user = users.head;

		while (user != null) {
			if (user.id == uid) {
				System.out.println("Name: " + user.name + " - Balance: " + user.balance);
				return 0;
			}
			user = user.next;
		}
		if (user == null) {
			System.out.println("No such user with uid [" + uid + "] exist");
		}

		return 0;
	}

	public void deleteUserById(UserSystem users, int uid) {
		User prev = null;
		User currentUser = users.head;
		if (getUser(users, uid) == -1) {
			System.out.println("No users.. Oops!");
			return;
		}

		// check if it is the first user in the list
		if (currentUser != null && currentUser.id == uid) {
			currentUser = currentUser.next;
			return;
		}

		while (currentUser != null && currentUser.id != uid) {
			prev = currentUser;
			currentUser = currentUser.next;
		}

		if (currentUser != null && prev != null) {
			prev.next = currentUser.next;
			System.out.println("User: " + currentUser.name + " deleted");
			return;
		}

		if (currentUser == null) {
			System.out.println("User not found. Oops!");
		}
	}

	// Or allow users to update their names.
	@SuppressWarnings("resource")
	public void updateUsername(UserSystem users, int uid) {
		Scanner str = new Scanner(System.in);
		String newName = "newUser-" + uid; // previously
		User currentUser = users.head;
		if (getUser(users, uid) == -1) {
			userError();
			str.close();
			return;
		}

		while (currentUser != null && currentUser.id != uid) {
			currentUser = currentUser.next;
		}

		if (currentUser != null) {
			System.out.print("Enter new User name: ");
			newName = str.nextLine();
			System.out.println("User: " + currentUser.name + " successfully changed to " + newName);
			currentUser.name = newName;
			return;
		}
//		str.close();
	}

	// works only when the user logs in or a current session has been created..
	@SuppressWarnings("resource")
	public void updatePin(UserSystem users, int uid) {
		Scanner str = new Scanner(System.in);
		final String msg = "Pin Error. number of digits in PIN must be >=4 and PIN must be greater ZERO";
		int newpin = 54321; /// user has to enter new pin
		if (getUser(users, uid) == -1) {
			userError();
			str.close();
			return;
		}
		User user = users.head;

		while (user != null) {
			if (user.id == uid) {
				System.out.println("pin must be alteast 4 digits and must be non-zer0: ");
				System.out.print("Enter New pin: ");
				newpin = str.nextInt();
				if (newpin > 0) { // Scan new pin.. length must be >=4 digits.
					if ((int) (Math.log10(newpin) + 1) >= 4) {
						user.pin = newpin;// update pin
						System.out.println("PIN successfully Updated. Don't share");
						return;

					} else {
						System.out.println(msg);
						return;
					}

				} else {
					System.out.println(msg);
					return;
				}

			}
			user = user.next;
		}
		if (user == null) {
			System.out.println("No such user with uid [" + uid + "] exist");
		}

	}

	private void userError() {
		System.out.println("No such user exist...");
	}

	public void updateBalance(UserSystem users, int uid, int oprChoice) {
		Scanner sc = new Scanner(System.in);
		User user = users.head;
		final String msg = "Not enough balance";
		final String account_msg = "Account updated: New balance - " + user.balance;
		int amount = 0;

		while (user != null) {
			if (user.id == uid) {
				if (oprChoice == 2) {
					System.out.print("Enter amount to deposit:  ");
					amount = sc.nextInt();
					user.balance += amount;
					System.out.print(account_msg);
					System.out.println("  .Exiting.....");
					sc.close();
					return;
				} else if (oprChoice == 3) {
					System.out.print("Enter amount to Withdraw:  ");
					amount = sc.nextInt();
					sc.close();
					if (amount > user.balance) {
						System.out.print(msg);
					} else {
						user.balance -= amount;
						System.out.print(account_msg);
						System.out.println("  .Exiting.....");
					}
					return;
				}
				sc.close();
				return;
			}
			user = user.next;
		}
		sc.close();

	}

	public void calculateInterest(UserSystem users, int uid) {

	}

	public void about() {
		System.out.println("...................***...........................****.......****......");
		System.out.println("** ... C O M P L E T E  B A N K I N G  S Y S T E M .................**");
		System.out.println("....................V1.0..............................................");
		System.out.println("................... By ...............................................");
		System.out.println("**................ SPYKE LIONEL.....................................**");
		System.out.println(".........................................................*.........");
	}
}
