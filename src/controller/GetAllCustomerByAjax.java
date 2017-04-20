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
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerType=request.getParameter("customerType");
		System.out.println(customerType+"   hii");
		HttpSession session=request.getSession(false);
		 allCustomerList=new ArrayList<Customer>();
		 try {
			if(customerType.equals("1"))
		 {
				
			 allCustomerList=customerService.getAllCustomer();
			 System.out.println("All");
		 }
		 else if(customerType.equals("2"))
		 {
			 allCustomerList=customerService.getCustomerByType(true);
			 System.out.println("Approved");
			 
		 }
		 else if(customerType.equals("3")){
			 allCustomerList=customerService.getCustomerByType(false);
			 System.out.println("Non-Approved");
		 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		 session.setAttribute("allCustomer", allCustomerList);
		 response.sendRedirect("admin.jsp");
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
