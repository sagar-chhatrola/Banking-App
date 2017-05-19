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

import service.AccountService;
import service.AccountServiceImpl;

/**
 * Servlet implementation class GetAccountNumberDropDown
 * This servlet is used for display accountNumber on trsansfer page based on selected account number
 */
@WebServlet("/GetAccountNumberDropDown")
public class GetAccountNumberDropDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 AccountService accountService=new AccountServiceImpl();
  public static final Logger _log=Logger.getLogger(GetAccountNumberDropDown.class.getName(), null);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAccountNumberDropDown() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param accountNumber this is int variable which is used to store account number of bank
	 * @param allAccountNumberList this is ArrayList variable which is used to store account Number
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accountNumber=Integer.parseInt(request.getParameter("accountNumber"));
		HttpSession session=request.getSession(false);
		_log.info("inside dropdown"+accountNumber);
		try {
			ArrayList<Integer> allAccountNumberList=accountService.getAccountNumberDropDown(accountNumber);
			session.setAttribute("allAccountNumberList", allAccountNumberList);
		System.out.println(allAccountNumberList);
			response.sendRedirect("accountNumberDropDownList.jsp");
			
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
