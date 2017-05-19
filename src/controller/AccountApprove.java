package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AccountService;
import service.AccountServiceImpl;

/**
 * Servlet implementation class AccountApprove
 * This servlet is used for  account approval or disapproval
 * @author sagar
 * 
 */
@WebServlet("/AccountApprove")
public class AccountApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       AccountService accountService=new AccountServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountApprove() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param approve this is boolean variable which used for status of approval
	 * @param accountNumber this is int variable which is used for get accountNumber
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean approve=Boolean.parseBoolean(request.getParameter("approve"));
		int accountNumber=Integer.parseInt(request.getParameter("accountNumber"));
		try {
			accountService.accountApprove(accountNumber,approve);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		request.getRequestDispatcher("/GetPendingAccounts").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
