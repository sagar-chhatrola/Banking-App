package service;

import java.sql.SQLException;

public interface AdminService {
	public int isAdmin(String name) throws SQLException;
	 public int adminLogin(String name,String password) throws SQLException;
}
