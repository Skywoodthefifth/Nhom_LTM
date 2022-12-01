package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.util.List;
import java.sql.Date;

import bo.*;
import bean.*;
/**
 * Servlet implementation class HomeController
 */
@WebServlet(name = "/BookManagement",urlPatterns = {"/", "/home","/BookManagement"})
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		if (action == null) {
            action = "";
        }
		try {
        switch (action) {
        	case "/new":
        		showNewForm(request, response);
        	case "/insert":
        		insertBook(request, response);
        		break;
        	case "/delete":
        		deleteBook(request, response);
        		break;
        	case "/edit":
				showEditForm(request, response);
				break;
        	case "/update":
        		updateBook(request, response);
        		break;
            case "register":
            	request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
            case "login": {
                    Cookie arrC[] = request.getCookies();
                    for (Cookie c : arrC) {
                        if (c.getName().equals("user")) request.setAttribute("username", c.getValue());
                        if (c.getName().equals("pass")) request.setAttribute("password", c.getValue());
                    }
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            }
            case "logout":
                logoutUser(request, response);
                break;
            default:
            	listBook(request, response);
				break;
        }
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String action = request.getParameter("action");
        System.out.println("Welcome home get in");
		if(action == null) action = "";
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
            case "/insert":
        		insertBook(request, response);
        		break;
            case "/update":
        		updateBook(request, response);
        		break;
			default:
				listBook(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void listBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");
			if (account == null) {
				request.setAttribute("ID_Account", -1);
			} else {
				request.setAttribute("ID_Account", account.getID_Account());
			}
			List<Book> listBook = bookBO.findAllBook();
			for (Book b : listBook)
			{
				b.setCategory(bookBO.findCategorybyID(b.getID_Category()).getCategory());
			}
			request.setAttribute("listBook", listBook);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BookManagement.jsp");
			dispatcher.forward(request, response);
			}
	
	private void insertBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException
			{
				String Book_title = request.getParameter("Book_title");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				String publisher = request.getParameter("publisher");
				Date publish_date = Date.valueOf(request.getParameter("publish_date"));
				String Category = request.getParameter("Category");
				int ID_Category = bookBO.findCategorybyName(Category).getID_Category();
				Book b = new Book(Book_title,ID_Category,quantity,publisher,publish_date);
				bookBO.insertBook(b);
				response.sendRedirect("BookManagement");
			}
	private void updateBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException
			{
				String Book_title = request.getParameter("Book_title");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				String publisher = request.getParameter("publisher");
				Date publish_date = Date.valueOf(request.getParameter("publish_date"));
				String Category = request.getParameter("Category");
				int ID_Category = bookBO.findCategorybyName(Category).getID_Category();
				Book b = new Book(Book_title,ID_Category, quantity, publisher, publish_date);
				bookBO.updateBook(b);
				response.sendRedirect("BookManagement");
			}
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException
			{
			int ID_Book = Integer.parseInt(request.getParameter("ID_Book"));
			bookBO.deleteBook(ID_Book);
			response.sendRedirect("BookManagement");
			}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book existingBook = bookBO.findBookById(id);
		existingBook.setCategory(bookBO.findCategorybyID(existingBook.getID_Category()).getCategory());
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditBook.jsp");
		List<BookCategory> Listcate = bookBO.findAllCategory();
		request.setAttribute("book", existingBook);
		request.setAttribute("ListCategory", Listcate);
		dispatcher.forward(request, response);
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditBook.jsp");
		List<BookCategory> Listcate = bookBO.findAllCategory();
		request.setAttribute("ListCategory", Listcate);
		dispatcher.forward(request, response);
	}
	public void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
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
                request.setAttribute("message","");
                Date datenow = new Date(System.currentTimeMillis());
                loginhistory log = new loginhistory(model.getID_Account(),datenow);
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
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

    public void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String re_password = request.getParameter("re_password");
            
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("re_password: " + re_password);
            
            if(!re_password.equals(password))
            {
            	request.setAttribute("message", "dang ky that bai. Mat khau nhap lai khong chinh xac.");
                request.setAttribute("check", false);
                System.out.println("dang ky that bai. Mat khau nhap lai khong chinh xac.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else
            {
            System.out.println("Username: " + username);
            
            if(accountBO.findByUsername(username) != null)
            {
            	request.setAttribute("message", "dang ky that bai. Ten tai khoan da ton tai.");
                request.setAttribute("check", false);
                System.out.println("dang ky that bai. Ten tai khoan da ton tai.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else
            {
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.removeAttribute("account");
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
