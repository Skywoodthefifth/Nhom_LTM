package bean;

import java.sql.Date;

public class loginhistory 
{
	private int ID_History;
	private int ID_Account;
	private Date loginDate;
	
	public int getID_History() {
		return ID_History;
	}
	public void setID_History(int iD_History) {
		ID_History = iD_History;
	}
	public int getID_Account() {
		return ID_Account;
	}
	public void setID_Account(int iD_Account) {
		ID_Account = iD_Account;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public loginhistory(int iD_Account, Date loginDate) {
		super();
		ID_Account = iD_Account;
		this.loginDate = loginDate;
	}
	public loginhistory(int iD_History, int iD_Account, Date loginDate) {
		super();
		ID_History = iD_History;
		ID_Account = iD_Account;
		this.loginDate = loginDate;
	}
	
	
}
