package entity;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Customer;
import service.AdminService;
import service.AdminServiceImpl;
import service.CustomerService;
import service.CustomerServiceImpl;

/**
 * Servlet implementation class Login
 */
// @WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService=new CustomerServiceImpl();
	AdminService adminService=new AdminServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
	
		int isAdmin = 0;
		try {
			isAdmin = adminService.isAdmin(name);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		int status=0;
		if(isAdmin==1){
		 try {
			status=adminService.adminLogin(name, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if (status == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("adminName",name);
			
			response.sendRedirect("GetAllCustomer");
		} else {

			request.setAttribute("errorMessage", "invalid username or password");
			request.getRequestDispatcher("login.jsp").include(request, response);
		}
			
	    }
		else{
			try {
			 status = customerService.signIn(name, password);
			System.out.println("hii"+status);
				if (status == 1) {
					Customer customer = new Customer();
					customer.setName(name);
					customer.setPass(password);

					int customerId = customerService.getCustomerId(name);
					customer.setId(customerId);
					
					Customer loginUser=customerService.getUserProfile(customerId);
					HttpSession session = request.getSession();
					session.setAttribute("loginUser",loginUser);
					session.setAttribute("customerId",customerId);
					session.setAttribute("customer", customer);
					response.sendRedirect("profile.jsp");
				} else {

					request.setAttribute("errorMessage", "invalid username or password");
					request.getRequestDispatcher("login.jsp").include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
