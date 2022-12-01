package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBRepository {
    
	private String jdbcURL = "jdbc:mysql://localhost:3306/?user=root";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    public DBRepository() {
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            Statement stmt = connection.createStatement();
			String use = "use LTM";
			stmt.executeUpdate(use);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
