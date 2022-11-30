package bo;

import bean.Book;
import bean.BookCategory;
import dao.AccountDAO;
import dao.BookDAO;
import java.util.List;

public class BookBO 
{
	private BookDAO bookDAO = new BookDAO();
	
	public BookBO() {
		bookDAO = new BookDAO();
    }
	public boolean insertBook(Book book)
	{
		return bookDAO.insertBook(book);
	}
	public Book findBookById(int id)
	{
		return bookDAO.findBookById(id);
	}
	public List<Book> findBooksByBook_title(String Book_title)
	{
		return bookDAO.findBooksByBook_title(Book_title);
	}
	public List<Book> findBooksBypublisher(String publisher)
	{
		return bookDAO.findBooksBypublisher(publisher);
	}
	public List<Book> findAllBook()
	{
		return bookDAO.findAllBook();
	}
	public List<Book> sortBookbypublish_date(List<Book> books)
	{
		return bookDAO.sortBookbypublish_date(books);
	}
	public List<Book> sortBookbyquantity(List<Book> books)
	{
		return bookDAO.sortBookbyquantity(books);
	}
	public boolean deleteBook(int id)
	{
		return bookDAO.deleteBook(id);
	}
	public boolean updateBook(Book book)
	{
		return bookDAO.updateBook(book);
	}
	public BookCategory findCategorybyID(int id)
	{
		return bookDAO.findCategorybyID(id);
	}
	public BookCategory findCategorybyName(String Category)
	{
		return bookDAO.findCategorybyName(Category);
	}
	public List<Book> findBooksById_Category(int Id_Category)
	{
		return bookDAO.findBooksById_Category(Id_Category);
	}
	public boolean insertCategory(String Category)
	{
		return bookDAO.insertCategory(Category);
	}
}