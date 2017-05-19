package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Customer;
import service.CustomerService;
import service.CustomerServiceImpl;

/**
 * Servlet implementation class getUserProfileServlet
 * this servlet is used for get customer profile data.
 */
@WebServlet("/getUserProfileServlet")
public class GetUserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService=new CustomerServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserProfileServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param customerId this is int variable for store customer's  id
	 * @param loginUser this is a Customer class variable that is used to store customer's profile data.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		int customerId =(int) session.getAttribute("customerId");
		Customer loginUser=null;
		try {
			loginUser = customerService.getUserProfile(customerId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		session.setAttribute("loginUser",loginUser);
		response.sendRedirect("profile.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
