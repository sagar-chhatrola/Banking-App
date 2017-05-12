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
		/*request.setAttribute("customerType", request.getParameter("customerType"));
		request.getRequestDispatcher("GetAllCustomerByAjax").forward(request, response);
		*/
		
		HttpSession session=request.getSession(false);
		 allCustomerList=new ArrayList<Customer>();
		 try {
			if(customerType.equals("All"))
		 {
				
			 allCustomerList=customerService.getAllCustomer();
			 //System.out.println("All");
		 }
		 else if(customerType.equals("Approved"))
		 {
			 allCustomerList=customerService.getCustomerByType(true);
			 //System.out.println("Approved");
			 
		 }
		 else if(customerType.equals("Non-Approved")){
			 allCustomerList=customerService.getCustomerByType(false);
			 //System.out.println("Non-Approved");
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
