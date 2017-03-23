package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.CustomerServiceImpl;
import dao.CustomerDao;
import pojo.Customer;

/**
 * Servlet implementation class DoTransaction
 */
//@WebServlet("/DoTransaction")
public class DoTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	     PrintWriter pw=response.getWriter();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    // pw.print(request.getParameter("acc_no"));
	    //pw.print(request.getParameter("Money"));
	    // pw.print(request.getParameter("acc_no_transfer"));
	     HttpSession session =request.getSession(false);
	     if(session!=null)
	     {
		Integer acc_no=Integer.parseInt(request.getParameter("acc_no"));
		Integer ammount=Integer.parseInt(request.getParameter("Money"));
		Integer acc_no_transfer=Integer.parseInt(request.getParameter("acc_no_transfer"));
           
		try {
			Customer c=(Customer) session.getAttribute("c");
			int id=c.getId();
			
    	  
    	  CustomerServiceImpl cs=new CustomerServiceImpl();
    	   int n=cs.Transfer_ammount(ammount,acc_no,acc_no_transfer,id);
    	   if(n>0)
    	   {
    		   pw.println("Successfully transfer<br/><br/>");
    		   request.getRequestDispatcher("profile.html").include(request, response);
    	   }
    	   else
    	   {
    		   pw.println("Enter Valiad Input,Incoorect account or not sufficent ammount!!");
    		   request.getRequestDispatcher("transaction.html").include(request, response);
    	   }
	    
       } catch (SQLException e) {
		// TODO Auto-generated catch block
		   e.printStackTrace();
		   
	  }  	
	 }
	     else{
	    	 pw.println("please login first");
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
