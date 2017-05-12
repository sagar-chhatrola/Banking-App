package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import pojo.Customer;

public class CustomerServiceImpl implements CustomerService {
    
	CustomerDao customerDao=new CustomerDaoImpl();

	@Override
	public int register(String name, String pass, String email, Date birthDate, String gender, Long mobileNumber)
			throws SQLException {
		
		return customerDao.register(name, pass, email, birthDate, gender, mobileNumber);
	}

	@Override
	public int signIn(String name, String pass) throws SQLException {
	
		return 	customerDao.signIn(name, pass);
	}

	@Override
	public int getCustomerId(String userName) throws Exception {
		
		return customerDao.getCustomerId(userName);
	}

	@Override
	public String getUserName(int customerId) throws SQLException {
		
		return customerDao.getUserName(customerId);
	}

	@Override
	public int chechUserName(String userName) throws SQLException {
		
		return customerDao.chechUserName(userName);
	}

	@Override
	public Customer getUserProfile(int customerId) throws SQLException {
		
		return customerDao.getUserProfile(customerId);
	}

	@Override
	public int updateProfile(String userName, String email, String password, String gender, Long mobileNumber,
			Date birthDate, int customerId) throws SQLException {
		
		return customerDao.updateProfile(userName, email, password, gender, mobileNumber, birthDate, customerId);
	}

	@Override
	public int transferAmmount(double ammount, int acc_no, int account_transfer, int id) throws SQLException {
	
		return customerDao.transferAmmount(ammount, acc_no, account_transfer, id);
	}

	@Override
	public String checkUser(String userName, String password) {
		
		return null;
	}

	@Override
	public ArrayList<Customer> getAllCustomer() throws SQLException {
		return customerDao.getAllCustomer();
	}

	@Override
	public ArrayList<Customer> getCustomerByType(boolean customerType) throws SQLException {
		
		return customerDao.getCustomerByType(customerType);
	}

	@Override
	public void customerApprove(int customerId, boolean approve) throws SQLException {
		 customerDao.customerApprove(customerId, approve);
		
	}
}
