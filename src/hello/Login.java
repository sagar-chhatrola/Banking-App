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

/**
 * Servlet implementation class Login
 */
//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter pw=response.getWriter();
		    response.setContentType("text/html");
		   String name=request.getParameter("name");
		   String pass=request.getParameter("pass");
		    //String query="select password from bank.cust_details where name="+name+"";
		   try {
			int status= CustomerDao.signIn(name,pass);
			if(status==1)
			{
				Customer c=new Customer();
				c.setName(name);
				c.setPass(pass);
				int id=CustomerDao.getCustomerId(name, pass);
				c.setId(id);
				HttpSession session=request.getSession();
				session.setAttribute("c", c);
				//pw.print("hiii");
				
				//request.getRequestDispatcher("profile.jsp").include(request, response);
				
				response.sendRedirect("profile.jsp");
			}
			else{
			
				 request.setAttribute("errorMessage", "invalid username or password");
					request.getRequestDispatcher("login.jsp").include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
