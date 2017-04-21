package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		System.out.println(customerId+"  "+approve);
		try {
			customerService.customerApprove(customerId, approve);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		_log.info(request.getParameter("customerType"));
		request.setAttribute("customerType", request.getParameter("customerType"));
		request.getRequestDispatcher("GetAllCustomerByAjax").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
