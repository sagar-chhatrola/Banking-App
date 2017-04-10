package utility;

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
 * Servlet implementation class UpdateUserName
 */

public class UpdateUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserName() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session.getAttribute("customer")!=null)
		{
		Customer customer=(Customer) session.getAttribute("customer");
		System.out.println(request.getParameter("userName"));
		int id=customer.getId();
			try {
				int status=CustomerDao.updateUserName(request.getParameter("userName"), id);
				System.out.println(status);
				response.sendRedirect("profile.jsp");;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				
		}
		else
		{
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
