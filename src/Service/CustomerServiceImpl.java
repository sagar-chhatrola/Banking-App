package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ManageDB.ManageConnection;

public class CustomerServiceImpl implements CustomerService {
	public int chechUserName(String uname) throws SQLException{
		 Connection con=ManageConnection.getConnection();
		  Statement st=con.createStatement();
		  String query="select name from cust_details";
		  ResultSet rs=st.executeQuery(query);
		  ArrayList<String> al=new ArrayList<String>();
		  while(rs.next())
		  {
			  al.add(rs.getString("name"));
		  }
		  if(al.contains(uname))
		  {
			  return 1;
		  }
		  return 0;
		
	 }

	
	public String getUserName(int id) throws SQLException {
		// TODO Auto-generated method stub
		 String query="select name from bank.cust_details where cust_id="+id+"";
		 Connection con=ManageConnection.getConnection();
		  Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery(query);
		  String name=null;
		    while(rs.next()){
		    	name=rs.getString("name");
		    }
		    
		    System.out.println(name);
		    System.out.println(id);
			  return name;
	
	}


	
	public int getCustomerId(String name, String pass) throws Exception {
		// TODO Auto-generated method stub
		 String query="select cust_id from bank.cust_details where name='"+name+"' and password='"+pass+"'";
		 Connection con=ManageConnection.getConnection();
		  Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery(query);
		  if(rs.next())
		  {
			  int id=rs.getInt("cust_id");
			  return id;
		  }
		 return 0;
	}


	
	public int checkAccount(int acc_no, int id) throws SQLException {
		// TODO Auto-generated method stub
		 Connection con=ManageConnection.getConnection();
		  Statement st=con.createStatement();
		  String query2="select acc_no from bank.account where cust_id='"+id+"'";
			//Connection con=getConnection();
			  //Statement st=con.createStatement();
			  ResultSet rs2=st.executeQuery(query2);
			  List<Integer> li=new ArrayList<Integer>();
			 int n=0;
			  while(rs2.next()){
				  li.add(rs2.getInt("acc_no"));
			      
			  }
			  if(li.contains(acc_no)){
				  n=1;
			  }
		  return n;
		
	}
	
	
}
