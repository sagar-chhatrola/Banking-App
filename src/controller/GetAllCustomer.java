package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class GetAllCustomer
 * This servlet is used for get all customer
 */
@WebServlet("/GetAllCustomer")
public class GetAllCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Customer> allCustomerList;
	CustomerService customerService=new CustomerServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllCustomer() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param allCustomerList is ArrayList variable which is used to store all customer List.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 try {
			 HttpSession session =request.getSession(false);
			 allCustomerList=new ArrayList<Customer>();
			 allCustomerList=customerService.getAllCustomer();
			 session.setAttribute("allCustomer", allCustomerList);
			 response.sendRedirect("admin.jsp");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
