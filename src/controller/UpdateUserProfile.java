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
 * This servlet is used for update customer's profile data
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
	 * @param userName this is String variable which is used for store customer' username.
	 * @param email this is String variable which is used for store customer' email.
	 * @param password this is String variable which is used for store customer' password.
	 * @param gender this is String variable which is used for store customer' gender.
	 * @param mobileNumber this is String variable which is used for store customer' mobileNumber.
	 * @param birthDate this is String variable which is used for store customer' birthDate.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String gender = request.getParameter("gender");
		Long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));
		java.sql.Date birthDate = java.sql.Date.valueOf(request.getParameter("birthDate"));
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		int customerId = customer.getId();
		
		try {
			 customerService.updateProfile(userName, email, password, gender, mobileNumber, birthDate, customerId);
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
