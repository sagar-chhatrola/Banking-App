package entity;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.CustomerDao;
import pojo.Customer;

/**
 * Servlet implementation class Login
 */
// @WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		try {
			int status = CustomerDao.signIn(name, pass);
			if (status == 1) {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setPass(pass);

				int customerId = CustomerDao.getCustomerId(name);
				customer.setId(customerId);
				
				Customer loginUser=CustomerDao.getUserProfile(customerId);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
