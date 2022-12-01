package dao;

import bean.Book;
import bean.BookCategory;

import java.util.List;

public interface IBookDAO 
{
	boolean insertBook(Book book);
	boolean insertCategory(String Category);
    Book findBookById(int id);
    List<Book> findBooksByBook_title(String Book_title);
    List<Book> findBooksBypublisher(String publisher);
    List<Book> findBooksById_Category(int Id_Category);
    List<Book> findAllBook();
    List<Book> sortBookbypublish_date(List<Book> books);
    List<Book> sortBookbyquantity(List<Book> books);
    boolean deleteBook(int id);
    boolean updateBook(Book book);
    BookCategory findCategorybyID(int id);
    BookCategory findCategorybyName(String Category);
    List<BookCategory> findAllCategory();
    
    
}
