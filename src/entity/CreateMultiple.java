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
 */

public class CreateMultiple extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService=new AccountServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateMultiple() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session.getAttribute("customer") != null) {
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			Customer c = (Customer) session.getAttribute("customer");
			int id = c.getId();
			System.out.println(id);
			try {
				
				int status = accountService.createMultiple(name, pass, id);
				if (status == 1) {
					request.setAttribute("errorMessage", "Successfully Another Account Created.");
					request.getRequestDispatcher("account.jsp").include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("errorMessage", "Please Login First.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
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
