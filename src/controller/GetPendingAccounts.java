package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.AccountService;
import service.AccountServiceImpl;

/**
 * Servlet implementation class GetPendingAccounts
 */
@WebServlet("/GetPendingAccounts")
public class GetPendingAccounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       AccountService accountService=new AccountServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPendingAccounts() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		try {
			session.setAttribute("pendingAccountList",accountService.getPendingAccounts());
			response.sendRedirect("accountAdminpage.jsp");
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
