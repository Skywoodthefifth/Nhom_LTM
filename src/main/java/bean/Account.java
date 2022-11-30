package bean;

public class Account 
{
	private int ID_Account;
    private String username;
    private String password;
	public int getID_Account() {
		return ID_Account;
	}
	public void setID_Account(int iD_Account) {
		ID_Account = iD_Account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account(int iD_Account, String username, String password) {
		super();
		ID_Account = iD_Account;
		this.username = username;
		this.password = password;
	}
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
    
    
}
