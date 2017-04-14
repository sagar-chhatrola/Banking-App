package service;

import java.sql.SQLException;

import dao.AdminDao;
import dao.AdminDaoImpl;

public class AdminServiceImpl implements AdminService {
    AdminDao adminDao=new AdminDaoImpl();
	@Override
	public int isAdmin(String name) throws SQLException {
	
		return adminDao.isAdmin(name);
	}

	@Override
	public int adminLogin(String name, String password) throws SQLException {
		
		return adminDao.adminLogin(name,password);
	}

}
