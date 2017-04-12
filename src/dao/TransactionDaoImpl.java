package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pojo.Transaction;
import utility.Database;

public class TransactionDaoImpl implements TransactionDao{
    Connection con=null;
	public  ArrayList<pojo.Transaction> listOfTransaction(int accountNumber) throws SQLException {
		ArrayList<pojo.Transaction> al = new ArrayList<pojo.Transaction>();
		 con = Database.getInstance().getConnection();
		Statement st = con.createStatement();

		String query = "select * from bank.transcation where credited_acc in('" + accountNumber + "')";
		Transaction t;
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			t = new Transaction();
			t.setTransactionId(rs.getInt("t_id"));
			t.setAmount(rs.getInt("amount"));
			t.setCreditedAccount(rs.getInt("credited_acc"));
			t.setDebitedAccount(rs.getInt("debited_acc"));
			t.setDatetime(rs.getString("datetime"));
			al.add(t);
		}
		String query1 = "select * from bank.transcation where debited_acc in('" + accountNumber + "')";

		ResultSet rs1 = st.executeQuery(query1);
		while (rs1.next()) {
			t = new Transaction();
			t.setTransactionId(rs1.getInt("t_id"));
			t.setAmount(rs1.getInt("amount"));
			t.setCreditedAccount(rs1.getInt("credited_acc"));
			t.setDebitedAccount(rs1.getInt("debited_acc"));
			t.setDatetime(rs1.getString("datetime"));
			al.add(t);
		}

		return al;
	}
	
	

}