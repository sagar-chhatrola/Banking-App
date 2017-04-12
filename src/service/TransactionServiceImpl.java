package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.TransactionDao;
import dao.TransactionDaoImpl;
import pojo.Transaction;

public class TransactionServiceImpl implements TransactionService{
	
   
    TransactionDao transactionDao=new TransactionDaoImpl();
    
	@Override
	public ArrayList<Transaction> listOfTransaction(int accountNumber) throws SQLException {
		
		return transactionDao.listOfTransaction(accountNumber);
	}

}
