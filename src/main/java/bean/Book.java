package bean;

import java.sql.Date;

public class Book 
{
	protected int ID_Book;
	protected String Book_title;
	protected int ID_Category;
	protected int quantity;
	protected String publisher;
	protected Date publish_date;
	protected String Category;
	
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public int getID_Category() {
		return ID_Category;
	}
	public void setID_Category(int iD_Category) {
		ID_Category = iD_Category;
	}
	public int getID_Book() {
		return ID_Book;
	}
	public void setID_Book(int iD_Book) {
		ID_Book = iD_Book;
	}
	public String getBook_title() {
		return Book_title;
	}
	public void setBook_title(String book_title) {
		Book_title = book_title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}
	public Book(int iD_Book, String book_title, int quantity, String publisher, Date publish_date) {
		super();
		ID_Book = iD_Book;
		Book_title = book_title;
		this.quantity = quantity;
		this.publisher = publisher;
		this.publish_date = publish_date;
	}
	public Book(String book_title, int quantity, String publisher, Date publish_date) {
		super();
		Book_title = book_title;
		this.quantity = quantity;
		this.publisher = publisher;
		this.publish_date = publish_date;
	}
	public Book(int iD_Book, String book_title, int iD_Category, int quantity, String publisher, Date publish_date) {
		super();
		ID_Book = iD_Book;
		Book_title = book_title;
		ID_Category = iD_Category;
		this.quantity = quantity;
		this.publisher = publisher;
		this.publish_date = publish_date;
	}
	public Book(String book_title, int iD_Category, int quantity, String publisher, Date publish_date) {
		super();
		Book_title = book_title;
		ID_Category = iD_Category;
		this.quantity = quantity;
		this.publisher = publisher;
		this.publish_date = publish_date;
	}
	
}
