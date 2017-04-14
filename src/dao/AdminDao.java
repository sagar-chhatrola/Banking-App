package dao;

import java.sql.SQLException;

public interface AdminDao {
  public int isAdmin(String name)throws SQLException ;
  public int adminLogin(String name,String password)throws SQLException;
}
