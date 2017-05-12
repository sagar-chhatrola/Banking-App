package service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import dao.AccountDao;
import dao.AccountDaoImpl;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import pojo.Account;

public class AccountServiceImpl implements AccountService{
    
	AccountDao accountDao=new AccountDaoImpl();
	
	
	@Override
	public ArrayList<Account> getAccountInfo(int customerId) throws SQLException {

		return accountDao.getAccountInfo(customerId);
	}

	@Override
	public ArrayList<Integer> getAllAccountNumber(int customerId) throws SQLException {
		return  accountDao.getAllAccountNumber(customerId);
	}

	@Override
	public ArrayList<Integer> getAccountNumber(int customerId) throws SQLException {
		
		return accountDao.getAccountNumber(customerId);
	}

	@Override
	public int createMultiple(String name, String pass, int id) throws SQLException {
		
		return accountDao.createMultiple(name, pass, id);
	}

	@Override
	public ArrayList<Integer> getAccountNumberDropDown(int accountNumber) throws SQLException {
		
		return accountDao.getAccountNumberDropDown(accountNumber);
	}

	@Override
	public ArrayList<Account> getPendingAccounts() throws SQLException {
		
		return accountDao.getPendingAccounts();
	}

	@Override
	public void accountApprove(int accountNumber,boolean approve) throws SQLException {
		accountDao.accountApprove(accountNumber,approve);
		
		
	}

	
}
