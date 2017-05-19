package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class GetAllCustomerByAjax
 * This servlet is used for get customer By their type.
 */
@WebServlet("/GetAllCustomerByAjax")
public class GetAllCustomerByAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Customer> allCustomerList;
	
	CustomerService customerService=new CustomerServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllCustomerByAjax() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param customerType this is String variable
	 *         which is used for customer's for e.g. All,approve,Non-Approve.
	 * @param allCustomerList this is ArrayList variable 
	 *         which is used to store all customer's list of particular type
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType=request.getParameter("customerType");
		HttpSession session=request.getSession(false);
		 allCustomerList=new ArrayList<Customer>();
		 try {
			if(customerType.equals("All"))
		 {
				
			 allCustomerList=customerService.getAllCustomer();
			
		 }
		 else if(customerType.equals("Approved"))
		 {
			 allCustomerList=customerService.getCustomerByType(true);
			
			 
		 }
		 else if(customerType.equals("Non-Approved")){
			 allCustomerList=customerService.getCustomerByType(false);
			 
		 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 session.setAttribute("customerType", request.getParameter("customerType"));
		 session.setAttribute("allCustomer", allCustomerList);
		 response.sendRedirect("allCustomerByajax.jsp");
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
