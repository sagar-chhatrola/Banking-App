package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Account;
import utility.Database;

public class AccountDaoImpl implements AccountDao {
    Connection con=null;
    
	public ArrayList<Account> getAccountInfo(int customerId) throws SQLException {

		ArrayList<Account> accountList = new ArrayList<Account>();
		con = Database.getInstance().getConnection();
		String query = "select * from bank.account where cust_id=?";
		
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, customerId);

		ResultSet rs = pst.executeQuery();
		int accountNo, balance;
		boolean approve;
		while (rs.next()) {

			accountNo = rs.getInt("acc_no");
			balance = rs.getInt("balance");
            approve=rs.getBoolean("approve");
			Account account = new Account();
			account.accountNumber = accountNo;
			account.balance = balance;
			account.approve=approve;
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
	
	public ArrayList<Integer> getAccountNumberDropDown(int accountNumber) throws SQLException
	{
		con = Database.getInstance().getConnection();
		String query = "select acc_no from  bank.account where acc_no not in(?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, accountNumber);
	    ResultSet rs=pst.executeQuery();
	    ArrayList<Integer> allAccountNumberList = new ArrayList<Integer>();
	    while(rs.next()){
	    	allAccountNumberList.add(rs.getInt("acc_no"));
	    }
	    return allAccountNumberList;
	}
	
}
