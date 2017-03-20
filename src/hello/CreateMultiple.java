package hello;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import pojo.Customer;

/**
 * Servlet implementation class CreateMultiple
 */
//@WebServlet("/CreateMultiple")
public class CreateMultiple extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMultiple() {
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
		if(session.getAttribute("c")!=null)
		{
		//PrintWriter pw=response.getWriter();
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		Customer c=(Customer) session.getAttribute("c");
		int id=c.getId();
		System.out.println(id);
		try {
			int status=CustomerDao.createMultiple(name, pass,id);
			if(status==1)
			{
				//pw.println("Successfully Another Account Created.<br/>");
				request.setAttribute("errorMessage", "Successfully Another Account Created.");
				request.getRequestDispatcher("profile.jsp").include(request, response);
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
