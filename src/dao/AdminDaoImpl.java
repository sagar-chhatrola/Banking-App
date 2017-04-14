package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.Database;

public class AdminDaoImpl implements AdminDao {
	Connection con =null;
	@Override
	public int isAdmin(String name) throws SQLException {
		 con = Database.getInstance().getConnection();
		 Statement st=con.createStatement();
		 String query="select userName from bank.admin";
		 ResultSet rs=st.executeQuery(query);
		 int adminStatus = 0;
		 while(rs.next())
		 {
			 if(name.equals(rs.getString("userName"))){
				 adminStatus=1;
			 }
		 }
		 System.out.println(adminStatus);
		return adminStatus;
	}
	@Override
	public int adminLogin(String name, String password) throws SQLException {
		con = Database.getInstance().getConnection();
		
		String query="select password from bank.admin where userName=?";
		
		 PreparedStatement pst=con.prepareStatement(query);
		 pst.setString(1,name); 
		System.out.println("1");
		 ResultSet rs=pst.executeQuery();
		 System.out.println("2");	  
		 int status = 0;
			while (rs.next()) {
				String pass = rs.getString("password");
				if (pass.equals(password)) {
					status = 1;
				}
			}
			return status;
		
	}

	
}
