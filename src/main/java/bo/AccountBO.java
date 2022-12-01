package bo;

import bean.Account;
import bean.loginhistory;

import dao.AccountDAO;
import java.util.List;
public class AccountBO 
{
	private AccountDAO accDAO = new AccountDAO();
	
	public AccountBO() {
        accDAO = new AccountDAO();
    }
	
	public Account findByUsernameAndPassword(String username, String pass) {
		return accDAO.findByUsernameAndPassword(username, pass);
	}
	public boolean insertAccount(Account account)
	{
		return accDAO.insertAccount(account);
	}
	public Account findAccountById(int id)
	{
		return accDAO.findAccountById(id);
	}
	public List<Account> findAllAccount()
	{
		return accDAO.findAllAccount();
	}
	public boolean deleteAccount(int id)
	{
		return accDAO.deleteAccount(id);
	}
	public boolean updateAccount(Account account)
	{
		return accDAO.updateAccount(account);
	}
	public List<loginhistory> getLoginHistory(int ID_Account)
	{
		return accDAO.getLoginHistory(ID_Account);
	}
	public boolean insertloginhistory(loginhistory log)
	{
		return accDAO.insertloginhistory(log);
	}
	public Account findByUsername(String username)
	{
		return accDAO.findByUsername(username);
	}
}
