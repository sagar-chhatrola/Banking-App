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

import pojo.Account;
import service.AccountService;
import service.AccountServiceImpl;

/**
 * Servlet implementation class GetAccountInfoList
 * This servlet is used for get all account info list of particular customer
 */
@WebServlet("/GetAccountInfoList")
public class GetAccountInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService=new AccountServiceImpl();
	ArrayList<Account> accountInfoList;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAccountInfoList() {
        super();
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param customerId this is int variable which used for store customer's id.
	 * @param accountInfoList this is ArrayList variable 
	 *         which is used to store account information of particular customer. 
 	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		
		HttpSession session=request.getSession();
		accountInfoList=new ArrayList<Account>();
		try {
			accountInfoList = accountService.getAccountInfo(customerId);
			
			session.setAttribute("accountInfoList", accountInfoList);
			response.sendRedirect("accountList.jsp");
		
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
