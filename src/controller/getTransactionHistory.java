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

import pojo.Transaction;
import service.AccountService;
import service.AccountServiceImpl;
import service.TransactionService;
import service.TransactionServiceImpl;

/**
 * Servlet implementation class getTransactionHistory
 */
@WebServlet("/GetTransactionHistory")
public class GetTransactionHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TransactionService transactionService=new TransactionServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTransactionHistory() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int accountNumber;
		List<Transaction> transactionList = null;
		List<Integer> accountNumberList = null;
		AccountService accountService=null;
		
		try {
			HttpSession session = request.getSession(false);
			transactionList = new ArrayList<Transaction>();
			accountNumberList = new ArrayList<Integer>();
			int customerId = (int) session.getAttribute("customerId");
			new AccountServiceImpl();
			accountService=new AccountServiceImpl();
			accountNumberList = accountService.getAccountNumber(customerId);
			if(request.getParameter("accountNumber")!=null)
	        {
	       	 
	       	 accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
	        }else{
	        	 accountNumber=accountNumberList.get(0);
	        }
			
			transactionList = transactionService.listOfTransaction(accountNumber);
			session.setAttribute("accountNumberList", accountNumberList);
			request.setAttribute("accountNumber", accountNumber);
			session.setAttribute("transactionList", transactionList);
			//request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
			response.sendRedirect("transactionHistory.jsp");
		} catch (SQLException e) {

			e.printStackTrace();
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
