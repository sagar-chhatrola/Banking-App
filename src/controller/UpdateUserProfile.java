package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Customer;
import service.CustomerService;
import service.CustomerServiceImpl;

/**
 * Servlet implementation class UpdateUserProfile
 */

public class UpdateUserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService=new CustomerServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserProfile() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String gender = request.getParameter("gender");
		Long mobile = Long.parseLong(request.getParameter("mobileNumber"));
		java.sql.Date birthDate = java.sql.Date.valueOf(request.getParameter("birthDate"));
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int customerId = customer.getId();
		
		try {
			 customerService.updateProfile(userName, email, password, gender, mobile, birthDate, customerId);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		response.sendRedirect("http://localhost:8081/OnlineBanking/getUserProfileServlet");

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
