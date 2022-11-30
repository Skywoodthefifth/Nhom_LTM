package dao;

import bean.Account;
import bean.loginhistory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IAccountDAO {
    private static final String INSERT_ACCOUNTS_SQL = "INSERT INTO ACCOUNT" + "(username,password) VALUES" + "(?,?)";
    // fix id_staff
    private static final String SELECT_ALL_ACCOUNT = "SELECT Id_Account,username,password FROM ACCOUNT";
    private static final String SELECT_ACCOUNT_BY_ID = "SELECT * FROM ACCOUNT where Id_Account=?";
    private static final String DELETE_ACCOUNTS_SQL = "DELETE FROM ACCOUNT where Id_Account=?";
    private static final String UPDATE_ACCOUNTS_SQL = "UPDATE ACCOUNT SET username=?, password=? where Id_Account=?";
    private static final String FIND_ACCOUNT_BY_USERNAME_AND_PASSWORD="SELECT * FROM account WHERE username=? and password=?";
    private static final String SELECT_LOGINHISTORY_BY_ID_ACCOUNT = "SELECT * FROM LOGINHISTORY where Id_Account=?";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Connection connection = null;
    private DBRepository dbRepository = new DBRepository();

    public AccountDAO() {
        connection = dbRepository.getConnection();
    }
    // Them Account vao

    
    @Override
    public boolean insertAccount(Account account) {
//        System.out.println(INSERT_ACCOUNTS_SQL);
//        insert(INSERT_ACCOUNTS_SQL, account);
        boolean check=false;
        try {
            PreparedStatement ps = this.connection.prepareStatement(INSERT_ACCOUNTS_SQL);
            System.out.println(ps);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            check=ps.executeUpdate()>0?true:false; // 
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    //Lay 1 account boi id
    @Override
    public Account findAccountById(int id) {
        Account account = null;
       // return (Account) query(SELECT_ACCOUNT_BY_ID, new AccountMapper(), id);
        try (Connection connection = this.connection) {
            PreparedStatement ps = connection.prepareStatement(SELECT_ACCOUNT_BY_ID);
            System.out.println(ps);
            ps.setInt(1, id);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                account = new Account(id, username, password);
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return account;
    }

    @Override
    public List<Account> findAllAccount() {
        List<Account> accountList = new ArrayList<>();
//        accountList = query(SELECT_ALL_ACCOUNT, new AccountMapper());
//        return accountList;
        try (PreparedStatement ps = this.connection.prepareStatement(SELECT_ALL_ACCOUNT);) {
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = null;
                int id = rs.getInt("Id_Account");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Date signupDate = rs.getDate("signup_date");
                LocalDateTime lastlogin = LocalDateTime.parse(rs.getString("last_login"),formatter);
                boolean status= rs.getBoolean("status");
                account = new Account(id, username, password);
                accountList.add(account);
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return accountList;
    }

    @Override
    public boolean deleteAccount(int idAccount) {
        boolean check = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_ACCOUNTS_SQL);
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, idAccount);
            check = (preparedStatement.executeUpdate() > 0);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean updateAccount(Account account) {
        boolean check = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_ACCOUNTS_SQL);
            System.out.println(preparedStatement);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setInt(1, account.getID_Account());
            check = (preparedStatement.executeUpdate() > 0);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return check;
    }

    @Override
    public Account findByUsernameAndPassword(String username, String password) //can bo sung
    {
        
        List<Account> accounts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ACCOUNT_BY_USERNAME_AND_PASSWORD);){
            System.out.println(preparedStatement);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Account account = null;
                int id = rs.getInt("Id_Account"); 
                account = new Account(id, username, password);
                accounts.add(account);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return accounts.isEmpty() ? null : accounts.get(0);
    }
    @Override
    public List<loginhistory> getLoginHistory(int ID_Account)
    {
    	List<loginhistory> LoginHistorys = new ArrayList<>();
    	try (Connection connection = this.connection) {
            PreparedStatement ps = connection.prepareStatement(SELECT_LOGINHISTORY_BY_ID_ACCOUNT);
            System.out.println(ps);
            ps.setInt(1, ID_Account);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	loginhistory loginHistory = null;
                Date loginDate = rs.getDate("loginDate");
                loginHistory = new loginhistory(ID_Account,loginDate);
                LoginHistorys.add(loginHistory);
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return LoginHistorys;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    
}
