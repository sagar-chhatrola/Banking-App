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
import pojo.Customer;
import service.AccountService;
import service.AccountServiceImpl;

/**
 * Servlet implementation class GetAccountInfo
 */
@WebServlet("/GetAccountInfo")
public class GetAccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountService accountService=new AccountServiceImpl();
	ArrayList<Account> accountInfoList=new ArrayList<Account>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAccountInfo() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession(false);
		int id;
		   if(s.getAttribute("customer")!=null){
		   
				Customer c1=(Customer)s.getAttribute("customer");
				 id=c1.getId(); 
				
				try {
					accountInfoList = accountService.getAccountInfo(id);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				request.setAttribute("successTransfer", "Successfully Transfered");
				request.setAttribute("accountInfoList", accountInfoList);
				request.getRequestDispatcher("account.jsp").forward(request, response);
				
				
		  }
		  else{
			  request.setAttribute("errorMessage", "Please Login First.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			  
		  }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
