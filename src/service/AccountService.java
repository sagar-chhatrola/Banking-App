package service;

import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Account;

public interface AccountService {
	public  ArrayList<Account> getAccountInfo(int customerId) throws SQLException ;
	public  ArrayList<Integer> getAllAccountNumber(int customerId) throws SQLException;
	public  ArrayList<Integer> getAccountNumber(int customerId) throws SQLException;
	public int createMultiple(String name, String pass, int id) throws SQLException;
	public ArrayList<Integer> getAccountNumberDropDown(int accountNumber)throws SQLException;
}
