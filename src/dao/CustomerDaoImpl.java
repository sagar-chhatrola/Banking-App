package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

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
	String name = "";
	String email = "";
	String password = "";
	Connection con = null;
	Properties props = new Properties();
	

	public int register(String name, String pass, String email, java.sql.Date birthDate, String gender,
			Long mobileNumber) throws SQLException {
		con = Database.getInstance().getConnection();

		String query = "insert into bank.cust_details (name,password,email,gender,mobileNumber,dateOfBirth,approve)values(?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		st.setString(1, name);
		st.setString(2, pass);
		st.setString(3, email);
		st.setString(4, gender);
		st.setLong(5, mobileNumber);
		st.setDate(6, birthDate);
		st.setBoolean(7, false);
		int status = st.executeUpdate();
		System.out.println(status);
		int customerId = 0;
		if (status > 0) {
			ResultSet rs = st.getGeneratedKeys();
			while (rs.next()) {
				customerId = rs.getInt(1);
			}
			String str = "insert into bank.account (cust_id,balance)values(?,?)";
			st = con.prepareStatement(str);
			st.setInt(1, customerId);
			st.setInt(2, 1000);
			st.executeUpdate();
		}
		return status;
	}

	public int signIn(String name, String pass) throws SQLException {
		String query = "select password from bank.cust_details where name=?and approve=1";
		con = Database.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
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

	public int getCustomerId(String userName) throws Exception {

		String query = "select cust_id from bank.cust_details where name=?";
		con = Database.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, userName);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			int id = rs.getInt("cust_id");
			return id;
		}
		return 0;
	}

	public String getUserName(int customerId) throws SQLException {

		String query = "select name from bank.cust_details where cust_id=?";
		con = Database.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, customerId);
		ResultSet rs = pst.executeQuery();
		String name = null;
		while (rs.next()) {
			name = rs.getString("name");
		}
		return name;

	}

	public int chechUserName(String userName) throws SQLException {
		con = Database.getInstance().getConnection();
		Statement st = con.createStatement();
		String query = "select name from cust_details";
		ResultSet rs = st.executeQuery(query);
		ArrayList<String> al = new ArrayList<String>();
		while (rs.next()) {
			al.add(rs.getString("name"));
		}
		if (al.contains(userName)) {
			return 1;
		}
		return 0;

	}

	public Customer getUserProfile(int customerId) throws SQLException {
		con = Database.getInstance().getConnection();
		
		String query = "select name,email,gender,mobileNumber,dateOfBirth,password from bank.cust_details where cust_id=?";
		PreparedStatement pst = con.prepareStatement(query);
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

	public int updateProfile(String userName, String email, String password, String gender, Long mobileNumber,
			Date birthDate, int customerId) throws SQLException {
		con = Database.getInstance().getConnection();
		String query = "update bank.cust_details set name=?,password=?,email=?,gender=?,mobileNumber=?,dateOfbirth=? where cust_id=?";

		PreparedStatement ps = con.prepareStatement(query);
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

	public int transferAmmount(Integer ammount, Integer acc_no, Integer account_transfer, int id) throws SQLException {

		con = Database.getInstance().getConnection();
		PreparedStatement pst ;
		int n = 0;
		if (ammount > 0) {

			String str = "select * from bank.account where acc_no=?";
			pst= con.prepareStatement(str);
			pst.setInt(1, acc_no);
			System.out.println(str);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				if (rs.getInt(3) >= ammount && ammount != null) {
					Integer balance1 = rs.getInt(3);
					System.out.println(balance1);
					

					String credit = "update bank.account set balance=?-? where acc_no=?";
					PreparedStatement pst1 = con.prepareStatement(credit);
					pst1.setInt(1, balance1);
					pst1.setInt(2, ammount);
					pst1.setInt(3, acc_no);
					pst1.executeUpdate();
					System.out.println(credit);
					String str1 = "select balance from bank.account where acc_no=?";
					pst1=con.prepareStatement(str1);
					pst1.setInt(1, account_transfer);
					ResultSet rs1 = pst1.executeQuery();
					int balance2 = 0;
					while (rs1.next()) {
						balance2 = rs1.getInt("balance");
					}
					Statement st1=con.createStatement();
					String debit = "update bank.account set balance='" + balance2 + "'+'" + ammount + "'where acc_no='"
							+ account_transfer + "'";
					st1.executeUpdate(debit);
					n = 1;
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

					 
					   String history_query = "insert into bank.transcation (amount,credited_acc,debited_acc,datetime) values(?,?,?,?)";
			                    pst1=con.prepareStatement(history_query);
			                   pst1.setInt(1,ammount);
			                   pst1.setInt(2,acc_no);
			                   pst1.setInt(3,account_transfer);
			                   pst1.setString(4, timeStamp);
			                   
								pst1.execute();// return 1;
				} else {
					n = 0;
				}
			}
		}
		return n;

	}

	@Override
	public String checkUser(String name, String password) throws SQLException {
		con = Database.getInstance().getConnection();
		Statement st = con.createStatement();

		return null;
	}

	@Override
	public ArrayList<Customer> getAllCustomer() throws SQLException {
		con = Database.getInstance().getConnection();
		Statement st = con.createStatement();
		String query = "select * from bank.cust_details";
		ResultSet rs = st.executeQuery(query);
		ArrayList<Customer> customerList=null;
		Customer customer=null;
		customerList = new ArrayList<Customer>();
		while (rs.next()) {
			customer = new Customer();
			customer.setId(rs.getInt("cust_id"));
			customer.setName(rs.getString("name"));
			customer.setEmail(rs.getString("email"));
			customer.setGender(rs.getString("gender"));
			customer.setMobileNumber(rs.getLong("mobileNumber"));
			customer.setDate(rs.getDate("dateOfBirth"));
			customer.setApprove(rs.getBoolean("approve"));
			customerList.add(customer);
		}
		System.out.println("all customer"+customerList.size());
		return customerList;
	}

	public ArrayList<Customer> getCustomerByType(boolean customerType) throws SQLException {
		con = Database.getInstance().getConnection();
		String query = "select * from bank.cust_details where approve=? ";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setBoolean(1, customerType);
		ResultSet rs = pst.executeQuery();
		ArrayList<Customer> customerList=null;
		Customer customer=null;
		customerList = new ArrayList<Customer>();
		while (rs.next()) {
			customer = new Customer();
			customer.setId(rs.getInt("cust_id"));
			customer.setName(rs.getString("name"));
			customer.setEmail(rs.getString("email"));
			customer.setGender(rs.getString("gender"));
			customer.setMobileNumber(rs.getLong("mobileNumber"));
			customer.setDate(rs.getDate("dateOfBirth"));
			customer.setApprove(rs.getBoolean("approve"));
			customerList.add(customer);
		}
		System.out.println("condition customer"+customerList.size());
		return customerList;
	}

	@Override
	public void customerApprove(int customerId, boolean approve) throws SQLException {
		con = Database.getInstance().getConnection();

		String query = "update bank.cust_details set approve=? where cust_id=?";
		String query1 = "select email,name,password from bank.cust_details where cust_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		PreparedStatement pst1 = con.prepareStatement(query1);
		pst1.setInt(1, customerId);
		ResultSet rs = pst1.executeQuery();
		while (rs.next()) {
			name = rs.getString("name");
			password = rs.getString("password");
			email = rs.getString("email");
		}
		if (approve == true) {
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
				message.setText("Hello " + name + "\nYour MyBank account is Deactivated now for security reasons.");
				Transport.send(message);
				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			pst.setBoolean(1, false);
			

		} else {
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
				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			pst.setBoolean(1, true);
		}
		pst.setInt(2, customerId);
		pst.executeUpdate();

	}

}
