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
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getParameter("name")=="" || request.getParameter("pass")==""){
			request.setAttribute("errorMessage","please enter all details");
			request.getRequestDispatcher("register.jsp").include(request, response);
			
		}
		else{
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String email= request.getParameter("email");
		String gender=request.getParameter("gender");
		System.out.println(gender);
		String date=request.getParameter("birthDate");
		System.out.println(date);
		
		java.sql.Date birthDate = java.sql.Date.valueOf(date);
		Long mobileNumber=Long.parseLong(request.getParameter("mobileNumber"));
		
		int m = 0;
		try {
			m = customerService.chechUserName(name);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		if (m == 1) {
			request.setAttribute("userName", "username is already exist");
			request.getRequestDispatcher("register.jsp").include(request, response);
		} else if (name == null || pass == null) {
			request.setAttribute("errorMessage", "enter valid username and password");
			request.getRequestDispatcher("register.jsp").include(request, response);
		} else {
			try {
				int status = customerService.register(name, pass,email,birthDate,gender,mobileNumber);
				System.out.println(status);
				if (status>0) {
					request.setAttribute("registerMessage", "You are successfully Registerd.");
					request.getRequestDispatcher("index.jsp").include(request, response);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
