package service;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.AccountDao;
import dao.AccountDaoImpl;
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

	
}
