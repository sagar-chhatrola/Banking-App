package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.CustomerServiceImpl;
import dao.CustomerDao;
import pojo.Customer;
import pojo.Transaction;

/**
 * Servlet implementation class ListTransaction
 */
//@WebServlet("/ListTransaction")
public class ListTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession(false);
		
		PrintWriter pw=response.getWriter();
		ArrayList<Transaction> al=new ArrayList<Transaction>();
		int acc_no=Integer.parseInt(request.getParameter("acc_no"));
		
         if(session!=null)
         {
		try {
			Customer c=(Customer) session.getAttribute("c");
			int id=c.getId();
			System.out.println(id);
			int n=0;
			
			n=CustomerDao.checkAccount(acc_no,id);
			System.out.println(n);
			pw.println("<br/>");
			if(n==0)
			{
				
				request.getRequestDispatcher("profile.jsp").include(request, response);
				pw.println("<br/>this is not your account number.<br/>");
			}
			else{
				al=CustomerDao.listOfTransaction(acc_no);
				if(al.isEmpty()){
					request.getRequestDispatcher("profile.jsp").include(request, response);
				}else{
					request.getRequestDispatcher("profile.jsp").include(request, response);
					pw.println("<br/>");
				for(Transaction list:al)
				{
					pw.println(""+list+"");
				}
				
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
      
         else{
        	 pw.print("Please Login first");
        	 request.getRequestDispatcher("login.html").include(request, response);
         }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
