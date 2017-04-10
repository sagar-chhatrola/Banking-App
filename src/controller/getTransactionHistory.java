package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import pojo.Transaction;

/**
 * Servlet implementation class getTransactionHistory
 */
//@WebServlet("/getTransactionHistory")
public class getTransactionHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getTransactionHistory() {
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
		try {
			HttpSession session = request.getSession(false);
			transactionList = new ArrayList<Transaction>();
			accountNumberList = new ArrayList<Integer>();
			int customerId = (int) session.getAttribute("customerId");

			accountNumberList = CustomerDao.getAccountNumber(customerId);
			if(request.getParameter("accountNumber")!=null)
	        {
	       	 
	       	 accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
	        }else{
	        	 accountNumber=accountNumberList.get(0);
	        }
			
			transactionList = CustomerDao.listOfTransaction(accountNumber);
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
