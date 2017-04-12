package entity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Customer;
import service.CustomerServiceImpl;

/**
 * Servlet implementation class DoTransaction
 */

public class DoTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerServiceImpl cs = new CustomerServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoTransaction() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession s = request.getSession(false);
		if (s.getAttribute("customer") != null) {
			
			if (request.getParameter("anotherAccountNumber") != "" && request.getParameter("accountNumber") != ""
					&& request.getParameter("ammount") != "" && Validate.checkNumber((request.getParameter("accountNumber")))
					&& Validate.checkNumber((request.getParameter("ammount")))
					&& Validate.checkNumber((request.getParameter("anotherAccountNumber")))) {
				int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
				int ammount = Integer.parseInt(request.getParameter("ammount"));
				int accountNumberTransfer = Integer.parseInt(request.getParameter("anotherAccountNumber"));

				try {
					Customer customer = (Customer) s.getAttribute("customer");
					int id = customer.getId();
					
					int status = cs.transferAmmount(ammount, accountNumber, accountNumberTransfer, id);
					if (status > 0) {
						request.setAttribute("errorMessage", "Successfully Transfered!!");
						response.sendRedirect("GetAccountInfo");
					} else {
						request.setAttribute("errorMassage",
								"Enter Valad ammount,or not sufficent ammount!!");
						request.getRequestDispatcher("transaction.jsp").forward(request, response);
					}

				}

				catch (Exception e) {
					e.printStackTrace();

				}
			} else {
				request.setAttribute("errorMessage", "Please Enter valid details !!");
				request.getRequestDispatcher("transaction.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage", "Please login first !!");

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
