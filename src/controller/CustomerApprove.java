package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

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
 * Servlet implementation class CustomerApprove
 * This servlet is used for customer approval or disApproval
 * @author sagar
 */
@WebServlet("/CustomerApprove")
public class CustomerApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService=new CustomerServiceImpl();
	private static final Logger _log=Logger.getLogger(CustomerApprove.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerApprove() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param customerId this is int variable which is used for get customerId
	 * @param approve this is boolean variable which used for customer approval status
	 * @param allCustomerList this is ArrayList variable which used for store all Customer List of bank
	 * @param customerType this is String variable 
	 *        which is used to stores customers Type e.g. All,Approve,Non-Approve
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		boolean approve=Boolean.parseBoolean(request.getParameter("approve"));
		ArrayList<Customer> allCustomerList;
		String customerType=request.getParameter("customerType");
		CustomerService customerService=new CustomerServiceImpl();
		_log.info(customerId+"inside customer approve  "+approve);
		try {
			customerService.customerApprove(customerId, approve);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		_log.info(request.getParameter("customerType"));
		
		
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
		 session.setAttribute("customerType",customerType);
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
