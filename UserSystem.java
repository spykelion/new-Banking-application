package bank.com;

import java.util.Scanner;

// All system operations here: [ user + system administrator ]
// Auto generate userid

public class UserSystem {
	// system administrator can carry out CRUD operations......
	private final int admin_pin = 1234;
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
	
	public int session() {
		
		
		return 1;
	}
	
	public void adminOperation() {
		System.out.println("Enter operation");
		System.out.println("1. Add user ");
		System.out.println("2. Delete user ");
		System.out.println("3. Update user info. Exception [Pin] ");
		System.out.println("4. Search user and print info ");
		System.out.println("5. Show database with all user info"
				+ ". NB: Pin is hidden for security reasons."); // write to a file.
		
	}
	
	public void userOperation() {
		System.out.println("Enter operation");
		System.out.println("1. Check account balance ");
		System.out.println("2. Deposit ");
		System.out.println("3. Withdraw ");
		System.out.println("4. Calculate Interest ");
		System.out.println("5. AboutMe . Download account info"); // write to a file.
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
//		System.out.println("Success. Welcome " + newuser.name); // For debugging.
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
	
//	public void getUserByName(UserSystem users, String username) {
//		
//	}
	public boolean isLogged(UserSystem users, int uid, int pin) {
	
		User user = users.head;
		boolean login = false;
		while (user != null) {
			if (user.id == uid) {
				if(user.pin == pin) {
					login = true;
					break;
				}
			}
			user = user.next;
		}
		
		return login;
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

	public void updateUsername(UserSystem users, int uid) {
		String newName = "newUser-" + uid;
		User currentUser = users.head;
		if (getUser(users, uid) == -1) {
			userError();
			return;
		}

		while (currentUser != null && currentUser.id != uid) {
			currentUser = currentUser.next;
		}

		if (currentUser != null) {
			System.out.println("User: " + currentUser.name + " changed to " + newName);
			currentUser.name = newName;
			return;
		}

	}

	// works only when the user logs in or a current session has been created..
	public void updatePin(UserSystem users, int uid) {
		final String msg = "number of digits in PIN must be >=4 and PIN must be greater ZERO";
		int newpin = 54321; /// user has to enter new pin
		if (getUser(users, uid) == -1) {
			userError();
			return;
		}
		User user = users.head;

		while (user != null) {
			if (user.id == uid) {
				if (newpin > 0) { // Scan new pin.. length must be >=4 digits.
					if ((int) (Math.log10(newpin) + 1) >= 4) {
						user.pin = newpin;
						System.out.println("PIN successfully Updated. Don't share");
						return;
						// update pin
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

	public void updateBalance(UserSystem users, int uid) {
		Scanner sc = new Scanner(System.in);
		final String msg = "Amount is greater than balance";
		int choice = 1, amount = 0;
		// Cash in or cash out..
		System.out.println("Enter amount to deposit");
		amount = sc.nextInt();
		
//		System.out.println("1. Deposit ");
//		System.out.println("2. Withdraw ");

		if (getUser(users, uid) == -1) {
			userError();
			return;
		}
		
		User user = users.head;

		while (user != null) {
			if (user.id == uid) {
				user.balance += amount;
				return;
			}
			user = user.next;
		}
		
		if (user == null) {
			System.out.println("No such user with uid [" + uid + "] exist");
		}

	}
}
