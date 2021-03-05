package bank.com;

public class User {
	private int uid;
	private String name;
	private int balance;
	
	public User() {}
	
	public User(int uid, String name, int balance) {
		this.uid = uid;
		this.name = name;
		this.balance = balance;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	

}