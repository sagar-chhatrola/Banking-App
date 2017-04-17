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
import service.TransactionService;
import service.TransactionServiceImpl;

/**
 * Servlet implementation class getTransactionHistoryByAjax
 */
@WebServlet("/getTransactionHistoryByAjax")
public class GetTransactionHistoryByAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TransactionService transactionService=new TransactionServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTransactionHistoryByAjax() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int accountNumber=Integer.parseInt(request.getParameter("accountNumber"));
		System.out.println("accountNumber::"+ accountNumber);
		HttpSession session=request.getSession(false);
		System.out.println("hiii ");
           List<Transaction> transactionList;
           try {
        	   
        	   transactionList=new ArrayList<Transaction>();
			transactionList=transactionService.listOfTransaction(accountNumber);
	
			 session.setAttribute("transactionList", transactionList);
			 response.sendRedirect("transactionList.jsp");
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
