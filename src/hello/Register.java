package hello;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Register
 */
//@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    PrintWriter pw=response.getWriter();
	    response.setContentType("text/html");
	    String name=request.getParameter("name");
	    String pass=request.getParameter("pass");
	    int m=0;
	    try {
			m=CustomerDao.chechUserName(name);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}if(m==1)
		{
			request.setAttribute("errorMessage", "username is already exist");
			request.getRequestDispatcher("register.jsp").include(request, response);
		}
		else if(name==null || pass==null){
			request.setAttribute("errorMessage", "enter valid username and password");
			request.getRequestDispatcher("register.jsp").include(request, response);
		}
		else
	    {
	    	
	    	
	    	 try {
	    		 
				int status=CustomerDao.register(name,pass);
				if(status>0)
				{
					pw.print("You are successfully Registerd.");
					request.getRequestDispatcher("index.html").include(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
