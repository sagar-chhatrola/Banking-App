package entity;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Customer;
import service.AccountService;
import service.AccountServiceImpl;

/**
 * Servlet implementation class CreateMultiple
 * This servlte is used for create multiple account for one customer.
 */

public class CreateMultiple extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService=new AccountServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 * 
	 */
	public CreateMultiple() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param name this is String variable which is used for store customer's name
	 * @param pass this is String variable which is used for store customer's pass
	 * @param id this is int variable which is used for store customer's id.
	 * @param status this is int variable which is used for created another account 
	 *        if status==1 then successfully another account created 
	 *          otherwise exception. 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session.getAttribute("customer") != null) {
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			Customer customer = (Customer) session.getAttribute("customer");
			int id = customer.getId();
			try {
				
				int status = accountService.createMultiple(name, pass, id);
				if (status == 1) {
					session.setAttribute("errorMessage", "Successfully Another Account Created.");
					response.sendRedirect("GetAccountInfo");
				}
				else
				{
					request.setAttribute("invalid", "Invalid username or password");
					request.getRequestDispatcher("createMultiple.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			session.setAttribute("errorMessage", "Please Login First.");
			response.sendRedirect("login.jsp");
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
