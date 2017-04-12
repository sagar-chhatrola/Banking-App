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

import pojo.Customer;
import utility.Database;

public class CustomerDaoImpl implements CustomerDao {
	Connection con =null;
	public  int register(String name, String pass,String email,java.sql.Date birthDate,String gender,Long mobileNumber) throws SQLException {
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

	public  int signIn(String name, String pass) throws SQLException {
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

	public  int getCustomerId(String userName) throws Exception {
		
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

	public  String getUserName(int customerId) throws SQLException {
		
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

	

	

	public  int chechUserName(String userName) throws SQLException {
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
	
	public  Customer getUserProfile(int customerId) throws SQLException{
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
	
	public  int updateProfile(String userName,String email,String password,String gender,Long mobileNumber,Date birthDate,int customerId) throws SQLException{
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
	
public int transferAmmount(Integer ammount, Integer acc_no, Integer account_transfer, int id) throws SQLException {
		
		Connection con = Database.getInstance().getConnection();
		Statement st = con.createStatement();
		int n = 0;
		if (ammount>0) {

			String str = "select * from bank.account where acc_no=" + acc_no + "";

			System.out.println(str);

			ResultSet rs = st.executeQuery(str);

			

			while (rs.next()) {
				if (rs.getInt(3) >= ammount && ammount != null) {
					Integer balance1 = rs.getInt(3);
					System.out.println(balance1);
					Statement st1 = con.createStatement();

					String credit = "update bank.account set balance='" + balance1 + "'-'" + ammount + "'where acc_no='"
							+ acc_no + "'";
					st1.executeUpdate(credit);
					System.out.println(credit);
					String str1 = "select balance from bank.account where acc_no=" + account_transfer + "";
					ResultSet rs1 = st1.executeQuery(str1);
					int balance2 = 0;
					while (rs1.next()) {
						balance2 = rs1.getInt("balance");
					}
					String debit = "update bank.account set balance='" + balance2 + "'+'" + ammount + "'where acc_no='"
							+ account_transfer + "'";
					st1.executeUpdate(debit);
					n = 1;
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
					
					String history_query = "insert into bank.transcation (amount,credited_acc,debited_acc,datetime) values('"
							+ ammount + "','" + account_transfer + "','" + acc_no + "','" + timeStamp + "')";

					st1.execute(history_query);// return 1;
				} else {
					n = 0;
				}
			}
		}
		return n;

	}
}
