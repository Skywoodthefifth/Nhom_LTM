package bean;

public class BookCategory 
{
	private int ID_Category;
	private String Category;
	public int getID_Category() {
		return ID_Category;
	}
	public void setID_Category(int iD_Category) {
		ID_Category = iD_Category;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public BookCategory(int iD_Category, String category) {
		super();
		ID_Category = iD_Category;
		Category = category;
	}
	
	
}
