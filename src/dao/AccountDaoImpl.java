package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pojo.Account;
import utility.Database;

public class AccountDaoImpl implements AccountDao {
	Connection con = null;
	//private static final Logger _log = Logger.getLogger(AccountDaoImpl.class.getName(), null);
	String name = "";
	String email = "";
	String password = "";
	Properties props = new Properties();
	/**
	 * This method is used for getting account information.
	 * @param accountList this is ArrayList<Account> variable ,
	 *         which is used for store all account information 
	 * @param pst,this is preparedStatement object.
	 * @param rs,this is ResultSet object.
	 * @param accountNumber,this is int variable,which is used for stored accountNumber
	 * @param balance,this is double variable,which is used for store ammount.
	 * @param approve ,this is boolean variable,
	 *        which is used for store account status e.g. Approve or Non-Approve.
	 * @param account,this is Account class variable,
	 *        which is used for store account information into ArrayList<Account>
	 */
	public ArrayList<Account> getAccountInfo(int customerId) throws SQLException {

		ArrayList<Account> accountList = new ArrayList<Account>();
		con = Database.getInstance().getConnection();
		String accountInfoquery = "select * from bank.account where customerId=? and approve=?";

		PreparedStatement pst = con.prepareStatement(accountInfoquery);
		pst.setInt(1, customerId);
        pst.setBoolean(2, true);
		ResultSet rs = pst.executeQuery();
		int accountNumber;
		double balance;
		boolean approve;
		while (rs.next()) {

			accountNumber = rs.getInt("accountNumber");
			balance = rs.getDouble("balance");
			approve = rs.getBoolean("approve");
			Account account = new Account();
			account.accountNumber = accountNumber;
			account.balance = balance;
			account.approve = approve;
			accountList.add(account);

		}
		return accountList;
	}
	/**
	 * This method is used for get all account number of particular customer
	 * @param ps,this is preparedStatement object.
	 * @param rs,this is ResultSet object.
	 * @param accaccountNumberList,this is ArrayList<Integer> variable,which is used for store account numbers
 	 */
	public ArrayList<Integer> getAllAccountNumber(int customerId) throws SQLException {
		con = Database.getInstance().getConnection();
		String allAccountNumberQuery = "select accountNumber from bank.account where customerId not in(?) and approve=?";

		PreparedStatement ps = con.prepareStatement(allAccountNumberQuery);
		ps.setInt(1, customerId);
		ps.setBoolean(2, true);
		ResultSet rs = ps.executeQuery();
		List<Integer> accountNumberList = new ArrayList<Integer>();
		while (rs.next()) {
			accountNumberList.add(rs.getInt("accountNumber"));
		}
		return (ArrayList<Integer>) accountNumberList;
	}
	/**
	 * @param ps,this is preparedStatement object.
	 * @param rs,this is ResultSet object.
	 * @param accaccountNumberList,this is ArrayList<Integer> variable,which is used for store account numbers
 	 */
	
	public ArrayList<Integer> getAccountNumber(int customerId) throws SQLException {
		con = Database.getInstance().getConnection();
		String accountNumberQuery = "select accountNumber from bank.account where customerId=? and approve=?";

		PreparedStatement ps = con.prepareStatement(accountNumberQuery);
		ps.setInt(1, customerId);
		ps.setBoolean(2, true);
		ResultSet rs = ps.executeQuery();
		List<Integer> accountNumberList = new ArrayList<Integer>();
		while (rs.next()) {
			accountNumberList.add(rs.getInt("accountNumber"));
		}
		return (ArrayList<Integer>) accountNumberList;
	}
	/**This method is used for create another account of same customer
	 * @param ps,this is preparedStatement object.
	 * @param status ,this is int variable,which is used for stored how many row is updated
 	 */
	public int createMultiple(String name, String pass, int customerId) throws SQLException {
		con = Database.getInstance().getConnection();
        String customerDetailsQuery="select name,password from bank.customer where customerId=?";
        PreparedStatement pst = con.prepareStatement(customerDetailsQuery);
        pst.setInt(1, customerId);
        int status=0;
        ResultSet rs=pst.executeQuery();
        String userName = null,password = null;
        while(rs.next())
        {
        	userName=rs.getString("name");
        	password=rs.getString("password");
        }
        
        if(name.equals(userName) && pass.equals(password)){
		String query1 = "insert into bank.account (customerId,balance,approve)values(?,?,?)";
		PreparedStatement st = con.prepareStatement(query1);
		st.setInt(1, customerId);
		st.setInt(2, 1000);
		st.setBoolean(3, false);
		status = st.executeUpdate();
        }
        
		return status;
	}
    
	/**
	 * This method is used for get all account number except one,
	 *   which is selected for transfer money from that account
	 * @param pst,this is preparedStatement object.
	 * @param rs,this is ResultSet object.
	 * @param allAccountNumberList,this is ArrayList<Integer> variable,which is used for store all account numbers of bank
 	 */
	public ArrayList<Integer> getAccountNumberDropDown(int accountNumber) throws SQLException {
		con = Database.getInstance().getConnection();
		String getAccountNumberQuery = "select accountNumber from  bank.account where accountNumber not in(?)";
		PreparedStatement pst = con.prepareStatement(getAccountNumberQuery);
		pst.setInt(1, accountNumber);
		ResultSet rs = pst.executeQuery();
		ArrayList<Integer> allAccountNumberList = new ArrayList<Integer>();
		while (rs.next()) {
			allAccountNumberList.add(rs.getInt("accountNumber"));
		}
		return allAccountNumberList;
	}
	/**
	 * This method is used for get pending accounts,which need to approval or disapproval
	 * @param pst,this is preparedStatement object.
	 * @param rs,this is ResultSet object.
	 * @param pendingAccountsList,this is ArrayList<Account> variable,
	 *        which is used for store all pending accounts which need to approve or disapprove
 	 */
	@Override
	public ArrayList<Account> getPendingAccounts() throws SQLException {
		con = Database.getInstance().getConnection();
		String getPendingAccountsQuery = "select * from  bank.account where approve=?";
		PreparedStatement pst = con.prepareStatement(getPendingAccountsQuery);
		pst.setBoolean(1, false);
		ResultSet rs = pst.executeQuery();
		ArrayList<Account> pendingAccountsList = new ArrayList<Account>();
		Account account = null;

		while (rs.next()) {
			account = new Account();
			account.setaccountNumber(rs.getInt("accountNumber"));
			account.setApprove(rs.getBoolean("approve"));
			account.setCustomerId(rs.getInt("customerId"));
			pendingAccountsList.add(account);
		}
		return pendingAccountsList;
	}
	/**
	 * This method is used for account approval or disapproval
	 * @param pst,this is preparedStatement object.
	 * @param rs,this is ResultSet object.
	 * @param props ,this is Property class object
	 * @param message,this is a Message class object,which is used in send message to receiver side
	 * @param name this is String variable, which is used for store customer' username.
	 * @param email this is String variable, which is used for store customer' email.
	 * @param password this is String variable, which is used for store customer' password.
 	 */
	@Override
	public void accountApprove(int accountNumber, boolean approve) throws SQLException {
		con = Database.getInstance().getConnection();
		PreparedStatement pst;
		if (approve == true) {
			/*_log.info("inside accountApprove method");
			_log.info("" + approve);
*/
			String accountApprovalQuery = "update bank.account set approve=? where accountNumber=?";
			pst = con.prepareStatement(accountApprovalQuery);
			pst.setBoolean(1, true);
			pst.setInt(2, accountNumber);
			pst.executeUpdate();
			
			String getCustomerIdQuery = "select customerId from bank.account where accountNumber=?";
			PreparedStatement pst2 = con.prepareStatement(getCustomerIdQuery);
			pst2.setInt(1, accountNumber);
			ResultSet rs1 = pst2.executeQuery();
			int customerId=0;
			while (rs1.next()) {
				customerId = rs1.getInt("customerId");
			}
			
			String getCustomerDetailsQuery = "select email,name,password from bank.customer where customerId=?";
			PreparedStatement pst1 = con.prepareStatement(getCustomerDetailsQuery);
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
				message.setText("Congratulation " + name + "\nYour MyBank account(accountNumber:" + accountNumber
						+ ") is activated now.\n.You can now login with below credential.\n UserName:" + name
						+ "\nPassword:" + password);
				Transport.send(message);
				

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} else {
			String deleteAccountQuery = "delete from bank.account where accountNumber=?";
            pst = con.prepareStatement(deleteAccountQuery);
			pst.setInt(1, accountNumber);
			pst.execute();
		}
	}

}
