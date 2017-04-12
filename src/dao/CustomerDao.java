package dao;

import java.sql.Date;
import java.sql.SQLException;

import pojo.Customer;

public interface CustomerDao {
	public  int register(String name, String pass,String email,java.sql.Date birthDate,String gender,Long mobileNumber) throws SQLException;
	public  int signIn(String name, String pass) throws SQLException;
	public  int getCustomerId(String userName) throws Exception ;
	public  String getUserName(int customerId) throws SQLException ;
	public  int chechUserName(String userName) throws SQLException;
	public  Customer getUserProfile(int customerId) throws SQLException;
	public  int updateProfile(String userName,String email,String password,String gender,Long mobileNumber,Date birthDate,int customerId) throws SQLException;
	public int transferAmmount(Integer ammount, Integer acc_no, Integer account_transfer, int id) throws SQLException;
}
