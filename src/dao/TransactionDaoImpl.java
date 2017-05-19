package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Transaction;
import utility.Database;

public class TransactionDaoImpl implements TransactionDao{
    Connection con=null;
    /**
	 * This method is used for get transaction history of particular account
	 * @param pst,this is  Statement class object
	 * @param rs,this is  ResultSet class object
	 * @param transactionList,this is a ArrayList<Transaction> variable,which is used to store transaction information
	 * @param transaction ,this is a Transaction class object,which is used to set value of transaction information
	 */
	public  ArrayList<pojo.Transaction> listOfTransaction(int accountNumber) throws SQLException {
		ArrayList<pojo.Transaction> transactionList = new ArrayList<pojo.Transaction>();
		 con = Database.getInstance().getConnection();
		 String getTransactionListQuery = "select * from bank.transcation where creditedAccount in(?)";
		 
		PreparedStatement pst = con.prepareStatement(getTransactionListQuery);
        pst.setInt(1, accountNumber);
		
		Transaction transaction;
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			transaction = new Transaction();
			transaction.setTransactionId(rs.getInt("transactionId"));
			transaction.setAmount(rs.getDouble("amount"));
			transaction.setCreditedAccount(rs.getInt("creditedAccount"));
			transaction.setDebitedAccount(rs.getInt("debitedAccount"));
			transaction.setDatetime(rs.getString("datetime"));
			transactionList.add(transaction);
		}
		String getTransactionQuery = "select * from bank.transcation where debitedAccount in(?)";
        pst=con.prepareStatement(getTransactionQuery);
        pst.setInt(1, accountNumber);
		ResultSet rs1 = pst.executeQuery();
		while (rs1.next()) {
			transaction = new Transaction();
			transaction.setTransactionId(rs1.getInt("transactionId"));
			transaction.setAmount(rs1.getDouble("amount"));
			transaction.setCreditedAccount(rs1.getInt("creditedAccount"));
			transaction.setDebitedAccount(rs1.getInt("debitedAccount"));
			transaction.setDatetime(rs1.getString("datetime"));
			transactionList.add(transaction);
		}

		return transactionList;
	}
	
	

}
