package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import bo.*;
import bean.*;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(name = "/BookManagement", urlPatterns = { "/", "/home", "/BookManagement" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookBO bookBO;
	private AccountBO accountBO;

	public void init() {
		bookBO = new BookBO();
		accountBO = new AccountBO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Book> listBook = null;
		String action = request.getParameter("action");
		String action2 = request.getServletPath();
		
		System.out.println("Welcome GET get in, action=" + action);
		System.out.println("Welcome GET get in, action=" + action2);


		int proverty = 0;

		try {
			switch (action2) {
			case "/edit":
				showEditForm(request, response);
				return;
			case "/delete":
				deleteBook(request, response);
				return;
			case "/new":
				showNewForm(request, response);
				return;
			//case "/search":
			//	proverty = Integer.parseInt(request.getParameter("proverty"));
			//	searchObject(request, response, proverty); // 1 = searchMa, 2= searchTen, 3= searchTenHangSX, lay tu
															// radio button or choose.
			//	return;
			}
		} catch (Exception ex) {
			System.out.print(ex);
			System.out.print(2);
		}

		if (action == null) {
			action = "";
		}
		try {
			switch (action) {
//			case "insert":
//				insertBook(request, response);
//				break;
//			case "update":
//				updateBook(request, response);
//				break;
			case "register":
				request.getRequestDispatcher("register.jsp").forward(request, response);
				break;
			case "login": {
				Cookie arrC[] = request.getCookies();
				for (Cookie c : arrC) {
					if (c.getName().equals("user"))
						request.setAttribute("username", c.getValue());
					if (c.getName().equals("pass"))
						request.setAttribute("password", c.getValue());
				}
				request.getRequestDispatcher("login.jsp").forward(request, response);
				break;
			}
			case "logout":
				logoutUser(request, response);
				break;
			case "search":
					proverty = Integer.parseInt(request.getParameter("proverty"));
					searchObject(request, response, proverty); 
					return;
			default:
				listBook(request, response,0,listBook);
				break;
			}
		} catch (Exception ex) {
			System.out.print(ex);
			System.out.print(1);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String action = request.getParameter("action");
		// String action2 = request.getServletPath();
		System.out.println("Welcome POST get in, action=" + action);
		List<Book> listBook = null;
		if (action == null)
			action = "";
		try {
			switch (action) {

			case "login":
				loginUser(request, response);
				break;
			case "register":
				register(request, response);
				break;
			case "logout":
				logoutUser(request, response);
				break;
			case "insert":
				System.out.println("insert");
				insertBook(request, response);
				break;
			case "update":
				System.out.println("update");
				updateBook(request, response);
				break;
			default:
				listBook(request, response,0,listBook);
				break;
			}
		} catch (Exception ex) {
			System.out.print(ex);
			System.out.print(3);
		}

	}

	private void listBook(HttpServletRequest request, HttpServletResponse response,int transper, List<Book> listBook)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookManagement.jsp");
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		List<loginhistory> LHs = new ArrayList<>();
		if (account == null) {
			request.setAttribute("ID_Account", -1);
		} else {
			request.setAttribute("ID_Account", account.getID_Account());
			LHs = accountBO.getLoginHistory(account.getID_Account());
			request.setAttribute("loginhistory", LHs);
			request.setAttribute("username", account.getUsername());
		}
		
		if (transper == 0)
			listBook = bookBO.findAllBook();
		for (Book b : listBook) {
			b.setCategory(bookBO.findCategorybyID(b.getID_Category()).getCategory());
		}
		request.setAttribute("listBook", listBook);
		
		List<BookCategory> Listcate = bookBO.findAllCategory();
		request.setAttribute("ListCategory", Listcate);
		dispatcher.forward(request, response);
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		System.out.println("insertBook");
		
		String Book_title = request.getParameter("Book_title");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String publisher = request.getParameter("publisher");
		Date publish_date = Date.valueOf(request.getParameter("publish_date"));
		String Category = request.getParameter("Category");
		int ID_Category = bookBO.findCategorybyName(Category).getID_Category();
		Book b = new Book(Book_title, ID_Category, quantity, publisher, publish_date);
		bookBO.insertBook(b);
		response.sendRedirect("BookManagement");
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		System.out.println("updateBook");
		
		int ID_Book = Integer.parseInt(request.getParameter("ID_Book"));
		
		System.out.println("ID_Book= " + ID_Book);
		
		String Book_title = request.getParameter("Book_title");
		
		System.out.println("Book_title= " + Book_title);
		
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		System.out.println("quantity= " + quantity);
		
		String publisher = request.getParameter("publisher");
		
		System.out.println("publisher= " + publisher);
		
		Date publish_date = Date.valueOf(request.getParameter("publish_date"));
		
		System.out.println("publish_date= " + publish_date.toString());
		
		String Category = request.getParameter("Category");
		
		System.out.println("Category= " + Category);
		
		
		int ID_Category = bookBO.findCategorybyName(Category).getID_Category();
		Book b = new Book(ID_Book, Book_title, ID_Category, quantity, publisher, publish_date);
		bookBO.updateBook(b);
		response.sendRedirect("BookManagement");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int ID_Book = Integer.parseInt(request.getParameter("ID_Book"));
		bookBO.deleteBook(ID_Book);
		response.sendRedirect("BookManagement");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("EditBook.jsp");
		System.out.println("edit");
		int id = Integer.parseInt(request.getParameter("ID_Book"));
		Book existingBook = bookBO.findBookById(id);
		existingBook.setCategory(bookBO.findCategorybyID(existingBook.getID_Category()).getCategory());
		List<BookCategory> Listcate = bookBO.findAllCategory();
		request.setAttribute("book", existingBook);
		request.setAttribute("ListCategory", Listcate);
		dispatcher.forward(request, response);

		return;
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditBook.jsp");
		System.out.println("new");
		List<BookCategory> Listcate = bookBO.findAllCategory();
		request.setAttribute("ListCategory", Listcate);
		dispatcher.forward(request, response);
		return;
	}

	public void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("login");
			request.setAttribute("message", null);
			Account model = null;
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			model = accountBO.findByUsernameAndPassword(username, password);
			if (model != null) {
				HttpSession session = request.getSession();
				session.setAttribute("account", model);
				session.setMaxInactiveInterval(3 * 60 * 60);
				request.setAttribute("check", true);
				request.setAttribute("username", model.getUsername());
				request.setAttribute("message", "");
				Date datenow = new Date(System.currentTimeMillis());
				loginhistory log = new loginhistory(model.getID_Account(), datenow);
				accountBO.insertloginhistory(log);
				Cookie u = new Cookie("user", username);
				Cookie p = new Cookie("pass", password);
				u.setMaxAge(60);
				p.setMaxAge(60);
				response.addCookie(u);// luu u va p len tren edge;
				response.addCookie(p);

				response.sendRedirect("BookManagement");
			} else {
				request.setAttribute("message", "dang nhap that bai. username hoac password khong dung");
				request.setAttribute("check", false);
				System.out.println("dang nhap that bai");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("register");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String re_password = request.getParameter("re_password");

			System.out.println("Username: " + username);
			System.out.println("Password: " + password);
			System.out.println("re_password: " + re_password);

			if (!re_password.equals(password)) {
				request.setAttribute("message", "dang ky that bai. Mat khau nhap lai khong chinh xac.");
				request.setAttribute("check", false);
				System.out.println("dang ky that bai. Mat khau nhap lai khong chinh xac.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else {
				System.out.println("Username: " + username);

				if (accountBO.findByUsername(username) != null) {
					request.setAttribute("message", "dang ky that bai. Ten tai khoan da ton tai.");
					request.setAttribute("check", false);
					System.out.println("dang ky that bai. Ten tai khoan da ton tai.");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "");
					Account account = null;
					account = new Account(username, password);
					accountBO.insertAccount(account);
					response.sendRedirect("signup_success.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logoutUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("logout");
		HttpSession session = request.getSession();
		session.removeAttribute("account");
		session.removeAttribute("username");
		response.sendRedirect("BookManagement");
	}

	private void searchObject(HttpServletRequest request, HttpServletResponse response, int proverty)
			throws SQLException, IOException, ServletException {
		
		List<Book> books = bookBO.findAllBook(); //lay toan bo sach
		 // lay toan bo sach
		if (proverty == 1) {

			String IDString = request.getParameter("searchvalue");
			if (IDString.equals("") == false) {
				books = new ArrayList<>(); 
				int id = Integer.parseInt(IDString);// cai o dien thong tin de search
				Book thebook = bookBO.findBookById(id);
				if (thebook != null)
				books.add(thebook);
			}

		} else if (proverty == 2) {
			String TenSach = request.getParameter("searchvalue");
			books = bookBO.findBooksByBook_title(TenSach);
		}
		String Theloai = request.getParameter("searchvalue2");
		List<Book> booklist = books;
		if (!Theloai.equals("0")) {
			booklist = bookBO.findBooksById_Category(Integer.parseInt(Theloai), books); // search them 1 tieu chi thu 2
																						// dua tren tieu chi 1
		}
		System.out.println(proverty);
		for(Book b: booklist)
		{
			System.out.println(b.getBook_title());		
		}
		
		listBook(request,response,1,booklist);
	}
}
