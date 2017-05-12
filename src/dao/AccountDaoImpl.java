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

	public ArrayList<Account> getAccountInfo(int customerId) throws SQLException {

		ArrayList<Account> accountList = new ArrayList<Account>();
		con = Database.getInstance().getConnection();
		String query = "select * from bank.account where cust_id=?";

		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, customerId);

		ResultSet rs = pst.executeQuery();
		int accountNo;
		double balance;
		boolean approve;
		while (rs.next()) {

			accountNo = rs.getInt("acc_no");
			balance = rs.getDouble("balance");
			approve = rs.getBoolean("approve");
			Account account = new Account();
			account.accountNumber = accountNo;
			account.balance = balance;
			account.approve = approve;
			accountList.add(account);

		}
		return accountList;
	}

	public ArrayList<Integer> getAllAccountNumber(int customerId) throws SQLException {
		con = Database.getInstance().getConnection();
		String query = "select acc_no from bank.account where cust_id not in(?)";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, customerId);
		ResultSet rs = ps.executeQuery();
		List<Integer> accountNumberList = new ArrayList<Integer>();
		while (rs.next()) {
			accountNumberList.add(rs.getInt("acc_no"));
		}
		return (ArrayList<Integer>) accountNumberList;
	}

	public ArrayList<Integer> getAccountNumber(int customerId) throws SQLException {
		con = Database.getInstance().getConnection();
		String query = "select acc_no from bank.account where cust_id=?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, customerId);
		ResultSet rs = ps.executeQuery();
		List<Integer> accountNumberList = new ArrayList<Integer>();
		while (rs.next()) {
			accountNumberList.add(rs.getInt("acc_no"));
		}
		return (ArrayList<Integer>) accountNumberList;
	}

	public int createMultiple(String name, String pass, int id) throws SQLException {
		con = Database.getInstance().getConnection();

		String query = "insert into bank.account (cust_id,balance,approve)values(?,?,?)";
		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1, id);
		st.setInt(2, 1000);
		st.setBoolean(3, false);
		int status = st.executeUpdate();

		return status;
	}

	public ArrayList<Integer> getAccountNumberDropDown(int accountNumber) throws SQLException {
		con = Database.getInstance().getConnection();
		String query = "select acc_no from  bank.account where acc_no not in(?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, accountNumber);
		ResultSet rs = pst.executeQuery();
		ArrayList<Integer> allAccountNumberList = new ArrayList<Integer>();
		while (rs.next()) {
			allAccountNumberList.add(rs.getInt("acc_no"));
		}
		return allAccountNumberList;
	}

	@Override
	public ArrayList<Account> getPendingAccounts() throws SQLException {
		con = Database.getInstance().getConnection();
		String query = "select * from  bank.account where approve=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setBoolean(1, false);
		ResultSet rs = pst.executeQuery();
		ArrayList<Account> pendingAccountsList = new ArrayList<Account>();
		Account account = null;

		while (rs.next()) {
			account = new Account();
			account.setaccountNumber(rs.getInt("acc_no"));
			account.setApprove(rs.getBoolean("approve"));
			account.setCustomerId(rs.getInt("cust_id"));
			pendingAccountsList.add(account);
		}
		return pendingAccountsList;
	}

	@Override
	public void accountApprove(int accountNumber, boolean approve) throws SQLException {
		con = Database.getInstance().getConnection();
		PreparedStatement pst;
		if (approve == true) {
			/*_log.info("inside accountApprove method");
			_log.info("" + approve);
*/
			String query = "update bank.account set approve=? where acc_no=?";
			pst = con.prepareStatement(query);
			pst.setBoolean(1, true);
			pst.setInt(2, accountNumber);
			pst.executeUpdate();
			
			String getCustomerIdQuery = "select cust_id from bank.account where acc_no=?";
			PreparedStatement pst2 = con.prepareStatement(getCustomerIdQuery);
			pst2.setInt(1, accountNumber);
			ResultSet rs1 = pst2.executeQuery();
			int customerId=0;
			while (rs1.next()) {
				customerId = rs1.getInt("cust_id");
			}
			
			String query1 = "select email,name,password from bank.cust_details where cust_id=?";
			PreparedStatement pst1 = con.prepareStatement(query1);
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
				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} else {
			String query = "delete from bank.account where acc_no=?";

			pst = con.prepareStatement(query);
			pst.setInt(1, accountNumber);
			pst.execute();
		}
	}

}
