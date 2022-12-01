package dao;

import bean.Account;
import bean.loginhistory;

import java.util.List;

public interface IAccountDAO {
    
    boolean insertAccount(Account account);
    Account findAccountById(int id);
    List<Account> findAllAccount();
    boolean deleteAccount(int id);
    boolean updateAccount(Account account);
    boolean insertloginhistory(loginhistory log);
    List<loginhistory> getLoginHistory(int ID_Account);
    
    Account findByUsernameAndPassword(String username,String password);
    Account findByUsername(String username);
}
