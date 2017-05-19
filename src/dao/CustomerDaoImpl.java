package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pojo.Customer;
import utility.Database;

public class CustomerDaoImpl implements CustomerDao {
	private static final Logger _log=Logger.getLogger(CustomerDaoImpl.class.getName());
	String name = "";
	String email = "";
	String password = "";
	Connection con = null;
	Properties props = new Properties();
	
	/**
	 * This method is used for new customer registration
	 * 
	 */
	public int register(String userName, String pass, String email, java.sql.Date birthDate, String gender,
			Long mobileNumber) throws SQLException {
		con = Database.getInstance().getConnection();

		String insertCustomerDetailQuery = "insert into bank.customer (name,password,email,gender,mobileNumber,dateOfBirth,approve)values(?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(insertCustomerDetailQuery, Statement.RETURN_GENERATED_KEYS);

		st.setString(1, userName);
		st.setString(2, pass);
		st.setString(3, email);
		st.setString(4, gender);
		st.setLong(5, mobileNumber);
		st.setDate(6, birthDate);
		st.setBoolean(7, false);
		int status = st.executeUpdate();
		
		
		
		return status;
	}
	/**
	 * This method is used for Login into Bank Application.
	 * @param pass1, this is String variable,which is used to store password,which is entered by customer
	 * @param rs,this is ResultSet object
	 * @param pst,this is PreparedStatement object
	 */
	public int signIn(String name, String pass) throws SQLException {
		String getPasswordQuery = "select password from bank.customer where name=?and approve=1";
		con = Database.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(getPasswordQuery);
		pst.setString(1, name);
		ResultSet rs = pst.executeQuery();
		int status = 0;
		while (rs.next()) {
			String pass1 = rs.getString("password");
			if (pass1.equals(pass)) {
				status = 1;
			}
		}
		return status;
	}
	/**
	 * This method is used for get customer id of current login customer
	 * @param rs,this is ResultSet object
	 * @param pst,this is PreparedStatement object
	 */
	public int getCustomerId(String userName) throws Exception {

		String getCustomerIdQuery = "select customerId from bank.customer where name=?";
		con = Database.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(getCustomerIdQuery);
		pst.setString(1, userName);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			int customerId = rs.getInt("customerId");
			return customerId;
		}
		return 0;
	}
	/**
	 * This method is used for get customer name based on customer id
	 * @param rs,this is ResultSet object
	 * @param pst,this is PreparedStatement object
	 * @param userName,This is String variable,which is used for stored customer's name
	 */
	public String getUserName(int customerId) throws SQLException {

		String getCustomerNameQuery = "select name from bank.customer where customerId=?";
		con = Database.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(getCustomerNameQuery);
		pst.setInt(1, customerId);
		ResultSet rs = pst.executeQuery();
		String userName = null;
		while (rs.next()) {
			userName = rs.getString("name");
		}
		return userName;

	}
	/**
	 * This method is used for check the userName is exists or not.
	 * @param rs,this is ResultSet class object
	 * @param st,this is Statement class object
	 * @param userNameList,this is ArrayList<String> variable,
	 *        which is used to store all customers userName
	 */
	public int chechUserName(String userName) throws SQLException {
		con = Database.getInstance().getConnection();
		Statement st = con.createStatement();
		String getCustomersNameQuery = "select name from customer";
		ResultSet rs = st.executeQuery(getCustomersNameQuery);
		ArrayList<String> userNameList = new ArrayList<String>();
		while (rs.next()) {
			userNameList.add(rs.getString("name"));
		}
		if (userNameList.contains(userName)) {
			return 1;
		}
		return 0;

	}
	/**
	 * This method is used for get customer profile information
	 * @param rs,this is ResultSet class object
	 * @param pst,this is PreparedStatement class object
	 * @param Customer,this is Customer class variable,
	 *        which is used to set customer profile data.
	 */

	public Customer getUserProfile(int customerId) throws SQLException {
		con = Database.getInstance().getConnection();
		
		String getCustomerProfileQuery = "select name,email,gender,mobileNumber,dateOfBirth,password from bank.customer where customerId=?";
		PreparedStatement pst = con.prepareStatement(getCustomerProfileQuery);
		pst.setInt(1, customerId);
		ResultSet rs = pst.executeQuery();
		Customer customer = null;
		while (rs.next()) {
			customer = new Customer();
			customer.setName(rs.getString("name"));
			customer.setEmail(rs.getString("email"));
			customer.setGender(rs.getString("gender"));
			customer.setMobileNumber(rs.getLong("mobileNumber"));
			customer.setDate(rs.getDate("dateOfBirth"));
			customer.setPass(rs.getString("password"));

		}
		return customer;
	}

	/**
	 * This method is used for update customer profile information
	 * @param pst,this is PreparedStatement class object
	 * @param status,this is int variable,
	 *        which is used to store how many row affected by executedUpdated
	 */
	public int updateProfile(String userName, String email, String password, String gender, Long mobileNumber,
			Date birthDate, int customerId) throws SQLException {
		con = Database.getInstance().getConnection();
		String updateCustomerDetailQuery = "update bank.customer set name=?,password=?,email=?,gender=?,mobileNumber=?,dateOfbirth=? where customerId=?";

		PreparedStatement ps = con.prepareStatement(updateCustomerDetailQuery);
		ps.setString(1, userName);
		ps.setString(2, password);
		ps.setString(3, email);
		ps.setString(4, gender);
		ps.setLong(5, mobileNumber);
		ps.setDate(6, birthDate);
		ps.setInt(7, customerId);

		int status = ps.executeUpdate();
		return status;
	}
	/**
	 * This method is used for transfer money from one account to another account
	 * @param pst,this is PreparedStatement class object
	 * @param rs,this is ResultSet class object
	 * @param flag,this is int variable,
	 *        which is used to check entered ammount by user is greater than or equal to current balance
	 *        if flag==1 then transaction successful ,otherwise fail.
	 */
	public int transferAmmount(double ammount, int accountNumber, int transferAccountNumber, int id) throws SQLException {

		con = Database.getInstance().getConnection();
		PreparedStatement pst ;
		int flag = 0;
		if (ammount > 0) {

			String getAccountDetailQuery = "select * from bank.account where accountNumber=?";
			pst= con.prepareStatement(getAccountDetailQuery);
			pst.setInt(1, accountNumber);
			

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				if (rs.getInt(3) >= ammount) {
					int currentBalance = rs.getInt(3);
					
					

					String creditQuery = "update bank.account set balance=?-? where accountNumber=?";
					PreparedStatement pst1 = con.prepareStatement(creditQuery);
					pst1.setDouble(1, currentBalance);
					pst1.setDouble(2, ammount);
					pst1.setInt(3, accountNumber);
					pst1.executeUpdate();
				
					String getAmmountQuery = "select balance from bank.account where accountNumber=?";
					pst1=con.prepareStatement(getAmmountQuery);
					pst1.setInt(1, transferAccountNumber);
					ResultSet rs1 = pst1.executeQuery();
					double currentBalanceOfAnotherAccount = 0;
					while (rs1.next()) {
						currentBalanceOfAnotherAccount = rs1.getInt("balance");
					}
					String debitQuery = "update bank.account set balance=?+? where accountNumber=?";
				    PreparedStatement pst2=con.prepareStatement(debitQuery);
					pst2.setDouble(1,ammount);
					pst2.setDouble(2,currentBalanceOfAnotherAccount);
					pst2.setInt(3, transferAccountNumber);
					pst2.executeUpdate();
					flag = 1;
					
					String transactionHistoryQuery = "insert into bank.transcation (amount,creditedAccount,debitedAccount,datetime) values(?,?,?,?)";
			        pst1=con.prepareStatement(transactionHistoryQuery);
			        pst1.setDouble(1,ammount);
			        pst1.setInt(2,accountNumber);
			        pst1.setInt(3,transferAccountNumber);
			        pst1.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			                   
		            pst1.execute();// return 1;
				} else {
					flag = 0;
				}
			}
		}
		return flag;

	}

	/**
	 * This method is used for get All customers list
	 * @param st,this is  Statement class object
	 * @param rs,this is  ResultSet class object
	 * @param customerList,this is a ArrayList<Customer> variable,which is used to store customers information
	 * @param customer ,this is a Customer class object,which is used to set value of customers information
	 */

	@Override
	public ArrayList<Customer> getAllCustomer() throws SQLException {
		con = Database.getInstance().getConnection();
		Statement st = con.createStatement();
		String getCustomerDetailQuery = "select * from bank.customer";
		ResultSet rs = st.executeQuery(getCustomerDetailQuery);
		ArrayList<Customer> customerList=null;
		Customer customer=null;
		customerList = new ArrayList<Customer>();
		while (rs.next()) {
			customer = new Customer();
			customer.setId(rs.getInt("customerId"));
			customer.setName(rs.getString("name"));
			customer.setEmail(rs.getString("email"));
			customer.setGender(rs.getString("gender"));
			customer.setMobileNumber(rs.getLong("mobileNumber"));
			customer.setDate(rs.getDate("dateOfBirth"));
			customer.setApprove(rs.getBoolean("approve"));
			customerList.add(customer);
		}
		
		return customerList;
	}
	/**
	 * This method is used for get All customers list By their type
	 * @param pst,this is  Statement class object
	 * @param rs,this is  ResultSet class object
	 * @param customerList,this is a ArrayList<Customer> variable,which is used to store customers information
	 * @param customer ,this is a Customer class object,which is used to set value of customers information
	 */

	public ArrayList<Customer> getCustomerByType(boolean customerType) throws SQLException {
		con = Database.getInstance().getConnection();
		String getCustomerByTypeQuery = "select * from bank.customer where approve=? ";
		PreparedStatement pst = con.prepareStatement(getCustomerByTypeQuery);
		pst.setBoolean(1, customerType);
		ResultSet rs = pst.executeQuery();
		ArrayList<Customer> customerList=null;
		Customer customer=null;
		customerList = new ArrayList<Customer>();
		while (rs.next()) {
			customer = new Customer();
			customer.setId(rs.getInt("customerId"));
			customer.setName(rs.getString("name"));
			customer.setEmail(rs.getString("email"));
			customer.setGender(rs.getString("gender"));
			customer.setMobileNumber(rs.getLong("mobileNumber"));
			customer.setDate(rs.getDate("dateOfBirth"));
			customer.setApprove(rs.getBoolean("approve"));
			customerList.add(customer);
		}
		return customerList;
	}
	/**
	 * This method is used for customer approval or disapproval
	 * @param pst,this is preparedStatement object.
	 * @param rs,this is ResultSet object.
	 * @param props ,this is Property class object
	 * @param message,this is a Message class object,which is used in send message to receiver side
	 * @param name this is String variable, which is used for store customer' username.
	 * @param email this is String variable, which is used for store customer' email.
	 * @param password this is String variable, which is used for store customer' password.
 	 */
	@Override
	public void customerApprove(int customerId, boolean approve) throws SQLException {
		con = Database.getInstance().getConnection();
		PreparedStatement pst;
        if(approve==true)
        {
        	_log.info("inside customerApprove method");
        	_log.info(""+approve);
        	
		String customerApprovalQuery = "update bank.customer set approve=? where customerId=?";
		String getCustomerDetailQuery = "select email,name,password from bank.customer where customerId=?";
		 pst = con.prepareStatement(customerApprovalQuery);
		 pst.setBoolean(1, true);
			pst.setInt(2, customerId);
			pst.executeUpdate();
		PreparedStatement pst1 = con.prepareStatement(getCustomerDetailQuery);
		pst1.setInt(1, customerId);
		ResultSet rs = pst1.executeQuery();
		while (rs.next()) {
			name = rs.getString("name");
			password = rs.getString("password");
			email = rs.getString("email");
		}
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("sagar@aimdek.com", "sagar@12345");
				}
			});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("sagar@aimdek.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				message.setSubject("Account Approval");
				message.setText("Congratulation " + name
						+ "\nYour MyBank account is activated now.\n.You can now login with below credential.\n UserName:"
						+ name + "\nPassword:" + password);
				Transport.send(message);
				
				String insertAccountQuery = "insert into bank.account (customerId,balance,approve)values(?,?,?)";
				pst=con.prepareStatement(insertAccountQuery);
				pst.setInt(1, customerId);
				pst.setInt(2, 1000);
				pst.setBoolean(3, true);
				pst.executeUpdate();

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			
	   
        }
        else
        { 
        	String deleteCustomerQuery="delete from bank.customer where customerId=?";
        	
        	pst=con.prepareStatement(deleteCustomerQuery);
        	pst.setInt(1, customerId);
        	pst.execute();
        }
	}

}
