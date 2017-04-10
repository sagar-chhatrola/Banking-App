package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Account;

public interface AccountDao {
	public  ArrayList<Account> getAccountInfo(int customerId) throws SQLException ;
	public  ArrayList<Integer> getAllAccountNumber(int customerId) throws SQLException;
	public  ArrayList<Integer> getAccountNumber(int customerId) throws SQLException;
	public int createMultiple(String name, String pass, int id) throws SQLException;
}
