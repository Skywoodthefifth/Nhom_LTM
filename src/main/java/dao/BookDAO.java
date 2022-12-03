package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.*;

public class BookDAO implements IBookDAO
{
	private static final String INSERT_BOOKS_SQL = "INSERT INTO BOOK" + "(Book_title,ID_Category,quantity, publisher, publish_date) VALUES" + "(?,?,?,?,?)";
	private static final String SELECT_ALL_BOOK = "SELECT * FROM BOOK";
	private static final String SELECT_BOOK_BY_ID = "SELECT * FROM BOOK where Id_Book=?";
	private static final String SELECT_BOOK_BY_BOOK_TITLE ="SELECT * FROM BOOK WHERE Book_title LIKE ? ";
	private static final String SELECT_BOOK_BY_PUBLISHER ="SELECT * FROM BOOK WHERE publisher=? ";
	private static final String SELECT_BOOK_BY_ID_CATEGORY ="SELECT * FROM BOOK WHERE Id_Category=? ";
	private static final String DELETE_BOOK_BY_ID = "DELETE FROM BOOK where Id_Book=?";
	private static final String UPDATE_BOOK_SQL = "UPDATE BOOK SET Book_title=?,ID_Category=?, quantity=?, publisher=?, publish_date=? where Id_Book=?";
	private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM BOOKCATEGORY where Id_Category=?";
	private static final String SELECT_CATEGORY_BY_NAME = "SELECT * FROM BOOKCATEGORY where Category=?";
	private static final String INSERT_CATEGORY_SQL = "INSERT INTO BOOKCATEGORY" + "(Category) VALUES" + "(?)";
	private static final String DELETE_CATEGORY_BY_ID = "DELETE FROM BOOKCATEGORY where Id_Category=?";
	private static final String SELECT_ALL_CATEGORY = "SELECT * FROM BOOKCATEGORY";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	static Connection connection = null;
	private DBRepository dbRepository = new DBRepository();

    public BookDAO() {
    	connection = dbRepository.getConnection();
        
    }

	@Override
	public boolean insertBook(Book book) {
		// TODO Auto-generated method stub
		boolean check=false;
        try {connection = dbRepository.getConnection();
        
        	//"INSERT INTO BOOK" + "(Book_title,ID_Category,quantity, publisher, publish_date) VALUES" + "(?,?,?,?,?)"
            PreparedStatement ps = this.connection.prepareStatement(INSERT_BOOKS_SQL);
            System.out.println(ps);
            ps.setString(1, book.getBook_title());
            ps.setInt(2, book.getID_Category());
            ps.setInt(3, book.getQuantity());
            ps.setString(4, book.getPublisher());
            ps.setDate(5, book.getPublish_date());
            check=ps.executeUpdate()>0?true:false; // 
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
	}
	@Override
	public boolean insertCategory(String Category)
	{
		boolean check=false;
        try {connection = dbRepository.getConnection();
            PreparedStatement ps = this.connection.prepareStatement(INSERT_CATEGORY_SQL);
            System.out.println(ps);
            ps.setString(1, Category);
            check=ps.executeUpdate()>0?true:false; // 
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
	}

	@Override
	public Book findBookById(int id) {
		// TODO Auto-generated method stub
		Book book = null;
	       // return (Account) query(SELECT_ACCOUNT_BY_ID, new AccountMapper(), id);
	        try {Connection connection = this.connection;
	            PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_ID);
	            System.out.println(ps);
	            ps.setInt(1, id);
	            System.out.println(ps);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                String Book_title = rs.getString("Book_title");
	                int quantity = rs.getInt("quantity");
	                int ID_Category = rs.getInt("ID_Category");
	                String publisher =rs.getString("publisher");
	                Date date = rs.getDate("publish_date");
	                book = new Book(id, Book_title,ID_Category, quantity, publisher, date);
	            }

	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        
	        
	        return book;
	}

	@Override
	public List<Book> findBooksByBook_title(String Book_title) {
		// TODO Auto-generated method stub
		List<Book> BookList = new ArrayList<>();
//      accountList = query(SELECT_ALL_ACCOUNT, new AccountMapper());
//      return accountList;
		try {Connection connection = this.connection;
            PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_BOOK_TITLE);
            System.out.println(ps);
            ps.setString(1, "%" + Book_title + "%");
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              Book book = null;
              int id = rs.getInt("Id_Book");
              String Book_title2 = rs.getString("Book_title");
              int ID_Category = rs.getInt("ID_Category");
              int quantity = rs.getInt("quantity");
              String publisher = rs.getString("publisher");
              Date Date = rs.getDate("publish_date");
              book = new Book(id, Book_title2,ID_Category, quantity, publisher, Date);
              BookList.add(book);
          }

      } catch (SQLException e) {
          printSQLException(e);
      }
      return BookList;
	}

	@Override
	public List<Book> findBooksBypublisher(String publisher) {
		// TODO Auto-generated method stub
		List<Book> BookList = new ArrayList<>();
//      accountList = query(SELECT_ALL_ACCOUNT, new AccountMapper());
//      return accountList;
		try {Connection connection = this.connection;
            PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_PUBLISHER);
            System.out.println(ps);
            ps.setString(1, publisher);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              Book book = null;
              int id = rs.getInt("Id_Book");
              String Book_title = rs.getString("Book_title");
              int ID_Category = rs.getInt("ID_Category");
              int quantity = rs.getInt("quantity");
              //String publisher = rs.getString("publisher");
              Date Date = rs.getDate("publish_date");
              book = new Book(id, Book_title,ID_Category, quantity, publisher, Date);
              BookList.add(book);
          }

      } catch (SQLException e) {
          printSQLException(e);
      }
      return BookList;
	}
	@Override
	public BookCategory findCategorybyID(int id)
	{
		BookCategory cate = null;
	       // return (Account) query(SELECT_ACCOUNT_BY_ID, new AccountMapper(), id);
	        try {Connection connection = this.connection;
	            PreparedStatement ps = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
	            System.out.println(ps);
	            ps.setInt(1, id);
	            System.out.println(ps);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                String Category = rs.getString("Category");
	                cate = new BookCategory(id, Category);
	            }

	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return cate;
	}
	@Override
	public BookCategory findCategorybyName(String Category)
	{
		BookCategory cate = null;
	       // return (Account) query(SELECT_ACCOUNT_BY_ID, new AccountMapper(), id);
	        try {Connection connection = this.connection;
	            PreparedStatement ps = connection.prepareStatement(SELECT_CATEGORY_BY_NAME);
	            System.out.println(ps);
	            ps.setString(1, Category);
	            System.out.println(ps);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                int Id_Category = rs.getInt("Id_Category");
	                cate = new BookCategory(Id_Category, Category);
	            }

	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return cate;
	}
	
	@Override
	public List<Book> findBooksById_Category(int Id_Category, List<Book> books)
	{
		List<Book> BookList = new ArrayList<>();
//      accountList = query(SELECT_ALL_ACCOUNT, new AccountMapper());
//      return accountList;
		try {Connection connection = this.connection;
            PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_ID_CATEGORY);
            System.out.println(ps);
            ps.setInt(1, Id_Category);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              Book book = null;
              int id = rs.getInt("Id_Book");
              String Book_title = rs.getString("Book_title");
              int quantity = rs.getInt("quantity");
              String publisher = rs.getString("publisher");
              Date Date = rs.getDate("publish_date");
              book = new Book(id, Book_title,Id_Category, quantity, publisher, Date);
              for(Book b: books)
              {
              if(book.getID_Book()==b.getID_Book()) BookList.add(book);
              }
          }

      } catch (SQLException e) {
          printSQLException(e);
      }
      return BookList;
	}
	
	@Override
	public List<Book> findAllBook() {
		// TODO Auto-generated method stub
		List<Book> BookList = new ArrayList<>();
//      accountList = query(SELECT_ALL_ACCOUNT, new AccountMapper());
//      return accountList;
      try 
      {	  connection = dbRepository.getConnection();
    	  PreparedStatement ps = this.connection.prepareStatement(SELECT_ALL_BOOK);
          System.out.println(ps);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              Book book = null;
              int id = rs.getInt("Id_Book");
              String Book_title = rs.getString("Book_title");
              int ID_Category = rs.getInt("ID_Category");
              int quantity = rs.getInt("quantity");
              String publisher = rs.getString("publisher");
              Date Date = rs.getDate("publish_date");
              book = new Book(id, Book_title,ID_Category, quantity, publisher, Date);
              BookList.add(book);
          }

      } catch (SQLException e) {
          printSQLException(e);
      }
      return BookList;
	}
	@Override
	public List<BookCategory> findAllCategory()
	{
		List<BookCategory> BookCategoryList = new ArrayList<>();
//      accountList = query(SELECT_ALL_ACCOUNT, new AccountMapper());
//      return accountList;
      try {
    	  PreparedStatement ps = this.connection.prepareStatement(SELECT_ALL_CATEGORY);
          System.out.println(ps);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
        	  BookCategory bookcate = null;
              int ID_Category = rs.getInt("ID_Category");
              String Category = rs.getString("Category");
              
              bookcate = new BookCategory(ID_Category, Category);
              BookCategoryList.add(bookcate);
          }

      } catch (SQLException e) {
          printSQLException(e);
      }
      return BookCategoryList;
	}

	@Override
	public List<Book> sortBookbypublish_date(List<Book> books) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> sortBookbyquantity(List<Book> books) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBook(int id) {
		// TODO Auto-generated method stub
		boolean check = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_BOOK_BY_ID);
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            check = (preparedStatement.executeUpdate() > 0);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return check;
	}
	// "UPDATE ACCOUNT SET Book_title=?, quantity=?, publisher=?, publish_date=? where Id_Book=?";

	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		boolean check = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_BOOK_SQL);
            System.out.println(preparedStatement);
            preparedStatement.setString(1, book.getBook_title());
            preparedStatement.setInt(2, book.getID_Category());
            preparedStatement.setInt(3, book.getQuantity());
            preparedStatement.setString(4, book.getPublisher());
            preparedStatement.setDate(5, book.getPublish_date());
            
            preparedStatement.setInt(6, book.getID_Book());
            check = (preparedStatement.executeUpdate() > 0);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return check;
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
