package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Account;
import pojo.Customer;
import pojo.Transaction;
import utility.Database;

public class CustomerDao {
	static Connection con =null;

	/**
	 * <>
	 * 
	 * @param name
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	public static int register(String name, String pass,String email,java.sql.Date birthDate,String gender,Long mobileNumber) throws SQLException {
		 con = Database.getInstance().getConnection();

		String query = "insert into bank.cust_details (name,password,email,gender,mobileNumber,dateOfBirth)values(?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	
		st.setString(1, name);
		st.setString(2, pass);
		st.setString(3, email);
		st.setString(4, gender);
		st.setLong(5,mobileNumber);
		st.setDate(6,birthDate);
		int status = st.executeUpdate();
		int customerId = 0;
		if (status == 1) {
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

	public static int signIn(String name, String pass) throws SQLException {
		String query = "select password from bank.cust_details where name='" + name + "'";
		 con = Database.getInstance().getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		int status = 0;
		while (rs.next()) {
			String pass1 = rs.getString("password");
			if (pass1.equals(pass)) {
				status = 1;
			}
		}
		return status;
	}

	public static ArrayList<pojo.Transaction> listOfTransaction(int accountNumber) throws SQLException {
		ArrayList<pojo.Transaction> al = new ArrayList<pojo.Transaction>();
		 con = Database.getInstance().getConnection();
		Statement st = con.createStatement();

		String query = "select * from bank.transcation where credited_acc in('" + accountNumber + "')";
		Transaction t;
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			t = new Transaction();
			t.setT_id(rs.getInt("t_id"));
			t.setAmount(rs.getInt("amount"));
			t.setCredited_acc(rs.getInt("credited_acc"));
			t.setDebited_acc(rs.getInt("debited_acc"));
			t.setDatetime(rs.getString("datetime"));
			al.add(t);
		}
		String query1 = "select * from bank.transcation where debited_acc in('" + accountNumber + "')";

		ResultSet rs1 = st.executeQuery(query1);
		while (rs1.next()) {
			t = new Transaction();
			t.setT_id(rs1.getInt("t_id"));
			t.setAmount(rs1.getInt("amount"));
			t.setCredited_acc(rs1.getInt("credited_acc"));
			t.setDebited_acc(rs1.getInt("debited_acc"));
			t.setDatetime(rs1.getString("datetime"));
			al.add(t);
		}

		return al;
	}

	public static int createMultiple(String name, String pass, int id) throws SQLException {
		 con = Database.getInstance().getConnection();

		String query = "insert into bank.account (cust_id,balance)values(?,?)";
		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1, id);
		st.setInt(2, 1000);
		int status = st.executeUpdate();

		return status;
	}

	public static int getCustomerId(String userName) throws Exception {
		
		String query = "select cust_id from bank.cust_details where name='" + userName + "'";
		 con = Database.getInstance().getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			int id = rs.getInt("cust_id");
			return id;
		}
		return 0;
	}

	public static String getUserName(int customerId) throws SQLException {
		
		String query = "select name from bank.cust_details where cust_id=" + customerId + "";
		 con = Database.getInstance().getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		String name = null;
		while (rs.next()) {
			name = rs.getString("name");
		}
		return name;

	}

	public static ArrayList<Account> getAccountInfo(int customerId) throws SQLException {

		ArrayList<Account> accountList = new ArrayList<Account>();
		 con = Database.getInstance().getConnection();
		Statement st = con.createStatement();
		String query = "select * from bank.account where cust_id=" + customerId + "";
	

		ResultSet rs = st.executeQuery(query);
		int accountNo, balance;
		while (rs.next()) {
			

			accountNo = rs.getInt("acc_no");
			balance = rs.getInt("balance");
			
			Account account = new Account();
			account.accountNumber = accountNo;
			account.balance = balance;
			accountList.add(account);
			
		}
		return accountList;
	}

	

	public static int chechUserName(String userName) throws SQLException {
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
	
	public static int updateUserName(String userName,int customerId) throws SQLException{
		 con = Database.getInstance().getConnection();
		Statement st=con.createStatement();
		String query="update bank.cust_details set name='"+userName+"' where cust_id='"+customerId+"'";
	    int status=st.executeUpdate(query);
	   
	    return status;
	}
	
	public static int updateEmail(String email,int customerId) throws SQLException{
		 con = Database.getInstance().getConnection();
		Statement st=con.createStatement();
		String query="update bank.cust_details set email='"+email+"' where cust_id='"+customerId+"'";
		int status=st.executeUpdate(query);
		
		return status;
	}
	
	public static Customer getUserProfile(int customerId) throws SQLException{
		 con = Database.getInstance().getConnection();
		Statement st=con.createStatement();
		String query="select name,email,gender,mobileNumber,dateOfBirth,password from bank.cust_details where cust_id='"+customerId+"'";
		ResultSet rs = st.executeQuery(query);
		Customer customer = null;
		while(rs.next()){
			customer=new Customer();
			customer.setName(rs.getString("name"));
			customer.setEmail(rs.getString("email"));
			customer.setGender(rs.getString("gender"));
			customer.setMobileNumber(rs.getLong("mobileNumber"));
			customer.setDate(rs.getDate("dateOfBirth"));
			customer.setPass(rs.getString("password"));
			
		}
		return customer;
	}
	
	public static int updateProfile(String userName,String email,String password,String gender,Long mobileNumber,Date birthDate,int customerId) throws SQLException{
		 con = Database.getInstance().getConnection();
		String query="update bank.cust_details set name=?,password=?,email=?,gender=?,mobileNumber=?,dateOfbirth=? where cust_id=?";
				
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, userName);
		ps.setString(2, password);
		ps.setString(3, email);
		ps.setString(4, gender);
		ps.setLong(5, mobileNumber);
		ps.setDate(6, birthDate);
        ps.setInt(7, customerId);


		int status=ps.executeUpdate();
		return status;
	}
	
	public static ArrayList<Integer> getAccountNumber(int customerId) throws SQLException{
		 con = Database.getInstance().getConnection();
		String query="select acc_no from bank.account where cust_id=?";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1,customerId);
		ResultSet rs=ps.executeQuery();
		List<Integer> accountNumberList=new ArrayList<Integer>();
		while(rs.next())
		{
			accountNumberList.add(rs.getInt("acc_no"));
		}
		return (ArrayList<Integer>) accountNumberList;
	}
	public static ArrayList<Integer> getAllAccountNumber(int customerId) throws SQLException{
		 con = Database.getInstance().getConnection();
		String query="select acc_no from bank.account where cust_id not in(?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1,customerId);
		ResultSet rs=ps.executeQuery();
		List<Integer> accountNumberList=new ArrayList<Integer>();
		while(rs.next())
		{
			accountNumberList.add(rs.getInt("acc_no"));
		}
		return (ArrayList<Integer>) accountNumberList;
	}
}
