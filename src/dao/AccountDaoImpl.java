package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Account;
import utility.Database;

public class AccountDaoImpl implements AccountDao {
    Connection con=null;
    
	public ArrayList<Account> getAccountInfo(int customerId) throws SQLException {

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
	
	
	public  ArrayList<Integer> getAllAccountNumber(int customerId) throws SQLException{
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
	
	public  ArrayList<Integer> getAccountNumber(int customerId) throws SQLException{
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
	
	public  int createMultiple(String name, String pass, int id) throws SQLException {
		 con = Database.getInstance().getConnection();

		String query = "insert into bank.account (cust_id,balance)values(?,?)";
		PreparedStatement st = con.prepareStatement(query);
		st.setInt(1, id);
		st.setInt(2, 1000);
		int status = st.executeUpdate();

		return status;
	}
	
}
