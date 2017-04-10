package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransactionDao {
	
	public ArrayList<pojo.Transaction> listOfTransaction(int accountNumber) throws SQLException;
}
