package service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransactionService {
	public ArrayList<pojo.Transaction> listOfTransaction(int accountNumber) throws SQLException;
}
