package entity;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerService;
import service.CustomerServiceImpl;

/**
 * Servlet implementation class Register
 * This servlet is used for register new customers.
 */

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService=new CustomerServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
	 * @param date this is String variable which is used for store customer' birthDate.
	 * @param flag  this is int variable which is used for check userName is already exists or not
	 *        if flag==1 then userName is already exists
	 *          otherwise not.  
	 * @param status this is int variable which is used for new customer registration.
	 *        if status==1 then successfully registration completed.
	 *          otherwise exception.     
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		response.setContentType("text/html");
		String userName = request.getParameter("name");
		String pass = request.getParameter("pass");
		String email= request.getParameter("email");
		String gender=request.getParameter("gender");
		String date=request.getParameter("birthDate");
		java.sql.Date birthDate = java.sql.Date.valueOf(date);
		Long mobileNumber=Long.parseLong(request.getParameter("mobileNumber"));
		
		int flag = 0;
		try {
			flag = customerService.chechUserName(userName);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		if (flag == 1) {
			request.setAttribute("userName", "username is already exist");
			request.getRequestDispatcher("register.jsp").include(request, response);
		} else if (userName == null || pass == null) {
			request.setAttribute("errorMessage", "enter valid username and password");
			request.getRequestDispatcher("register.jsp").include(request, response);
		} else {
			try {
				int status = customerService.register(userName, pass,email,birthDate,gender,mobileNumber);
				if (status>0) {
					request.setAttribute("registerMessage", "You are successfully Registerd.");
					request.getRequestDispatcher("index.jsp").include(request, response);
					
				}
			} catch (SQLException e) {
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
