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
 * This servlet is used for transfer ammount from one account to another
 */

public class DoTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerServiceImpl customerService = new CustomerServiceImpl();
	private static final Logger _log=Logger.getLogger(DoTransaction.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoTransaction() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param fromAccountNumber this is int variable,
	 *        which is used for store account number that is transfer money from which account.
	 * @param toAccountNumber this is int variable,
	 *        which is used for store account number that is transfer money to which account.
	 * @param ammount this is double variable,
	 *        which is used for how many ruppes transfer from one account to another account
	 * @param id this is int variable,which is used for store customer's id.
	 * @param customer this is Customer class object,
	 *        which is used for get current login customer's id.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(false);
		if (session.getAttribute("customer") != null) {
			
			
				int fromAccountNumber = Integer.parseInt(request.getParameter("accountNumber"));
				double ammount = Double.parseDouble(request.getParameter("ammount"));
				int toAccountNumber = Integer.parseInt(request.getParameter("anotherAccountNumber"));

				try {
					Customer customer = (Customer) session.getAttribute("customer");
					int id = customer.getId();
					int status = customerService.transferAmmount(ammount, fromAccountNumber, toAccountNumber,id);
					if (status > 0) {
						session.setAttribute("successTransfer", "Successfully Transfered!!");
						response.sendRedirect("GetAccountInfo");
					} else {
						request.setAttribute("errmsg",
								"Enter Valid ammount");
						request.getRequestDispatcher("transaction.jsp").forward(request, response);
					}

				}

				catch (Exception e) {
					e.printStackTrace();

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
