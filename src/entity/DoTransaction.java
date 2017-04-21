package entity;

import java.io.IOException;
import java.util.logging.Logger;

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
	private static final Logger _log=Logger.getLogger(DoTransaction.class.getName());
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
		
		
		HttpSession session = request.getSession(false);
		if (session.getAttribute("customer") != null) {
			
			if (request.getParameter("anotherAccountNumber") != "" && request.getParameter("accountNumber") != ""
					&& request.getParameter("ammount") != "" && Validate.checkNumber((request.getParameter("accountNumber")))
					&& Validate.checkNumber((request.getParameter("ammount")))
					&& Validate.checkNumber((request.getParameter("anotherAccountNumber")))) {
				int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
				int ammount = Integer.parseInt(request.getParameter("ammount"));
				int accountNumberTransfer = Integer.parseInt(request.getParameter("anotherAccountNumber"));

				try {
					Customer customer = (Customer) session.getAttribute("customer");
					int id = customer.getId();
					
					int status = cs.transferAmmount(ammount, accountNumber, accountNumberTransfer, id);
					if (status > 0) {
						session.setAttribute("successTransfer", "Successfully Transfered!!");
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
				_log.info("in else statement of dotransaction");
				request.setAttribute("validation", "Please Enter valid details !!");
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
