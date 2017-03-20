package Service;

import java.sql.SQLException;

public interface CustomerService {

int chechUserName(String name) throws Exception;
public String getUserName(int id) throws SQLException;
public int getCustomerId(String name,String pass) throws Exception;
public  int checkAccount(int acc_no,int id) throws SQLException;
}
