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

import service.AccountService;
import service.AccountServiceImpl;

/**
 * Servlet implementation class getAccountNumberList
 * This servlet is used to get account number of particular customer 
 */
@WebServlet("/getAccountNumberList")
public class GetAccountNumberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService=new AccountServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAccountNumberList() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param customerId this is int variable  which is used to store customer's id.
	 * @param accountNumberList this is ArrayList variable 
	 *         which is used to store account number of particular customer
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		int customerId=(int) session.getAttribute("customerId");
		List<Integer> accountNumberList=new ArrayList<Integer>();
		try {
			
			accountNumberList=accountService.getAccountNumber(customerId);
			session.setAttribute("accountNumberList", accountNumberList);
			response.sendRedirect("accountNumber.jsp");
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
