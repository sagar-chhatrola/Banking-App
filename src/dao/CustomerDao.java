package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



import ManageDB.ManageConnection;
import hello.Pair;
import pojo.Transaction;

public class CustomerDao {
	 // private static int cust_id;
  
	  /**
	   * <>
	   * @param name 
	   * @param pass
	   * @return
	   * @throws SQLException
	   */
	  public static int register(String name,String  pass) throws SQLException{
		  Connection con=ManageConnection.getConnection();
		
		  String query="insert into bank.cust_details (name,password)values(?,?)";
		  PreparedStatement st=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		  st.setString(1,name);
		  st.setString(2,pass);
		  int status=st.executeUpdate();
		  int cust_id=0;
		  if(status==1){
		  ResultSet rs=st.getGeneratedKeys();
		 
		  while(rs.next()){
		 cust_id=rs.getInt(1);
		  }
		 String str="insert into bank.account (cust_id,balance)values(?,?)";
		  st=con.prepareStatement(str);
		  st.setInt(1,cust_id);
		  st.setInt(2,1000);
		  st.executeUpdate();
		 
		  }
		  return status;
	  }
	  
	  public static int signIn(String name,String pass) throws SQLException{
		  String query="select password from bank.cust_details where name='"+name+"'";
		  Connection con=ManageConnection.getConnection();
		  Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery(query);
		  int status=0;
		  while(rs.next())
		  {
			  String pass1=rs.getString("password");
			  if(pass1.equals(pass))
			  {
			  status=1;
			  }
		  }
		  return status;
	  }
	
		 public static ArrayList<pojo.Transaction> listOfTransaction(int acc_no) throws SQLException{
			  ArrayList<pojo.Transaction> al=new ArrayList<pojo.Transaction>();
			  Connection con=ManageConnection.getConnection();
			  Statement st=con.createStatement();
			  
				String query="select * from bank.transcation where credited_acc in('"+acc_no+"')";	
								Transaction t;
				ResultSet rs=st.executeQuery(query);
				while(rs.next())
				{
	               t=new Transaction();
					t.setT_id(rs.getInt("t_id"));
					t.setAmount(rs.getInt("amount"));
					t.setCredited_acc(rs.getInt("credited_acc"));
					t.setDebited_acc(rs.getInt("debited_acc"));
	                t.setDatetime(rs.getString("datetime"));
					
					//StringBuffer str=new StringBuffer(rs.getString("amount"));  
					//str.append("             ").append(rs.getInt("debited_acc")).append("                       ").append(rs.getInt("credited_acc")).append("                         ").append(rs.getInt("t_id")).append("                     ").append(rs.getString("datetime"));
				    //al.add(str); 
					al.add(t);
				}
				String query1="select * from bank.transcation where debited_acc in('"+acc_no+"')";	
				
				ResultSet rs1=st.executeQuery(query1);
				while(rs1.next())
				{
				    t=new Transaction();
					t.setT_id(rs1.getInt("t_id"));
					t.setAmount(rs1.getInt("amount"));
					t.setCredited_acc(rs1.getInt("credited_acc"));
					t.setDebited_acc(rs1.getInt("debited_acc"));
	                t.setDatetime(rs1.getString("datetime"));
					
					//StringBuffer str=new StringBuffer(rs1.getString("amount"));
					//str.append("             ").append(rs1.getInt("debited_acc")).append("                       ").append(rs1.getInt("credited_acc")).append("                         ").append(rs1.getInt("t_id")).append("                      ").append(rs1.getString("datetime"));
				    //al.add(str);
	                   al.add(t);				
				}
				
			 
				return al;
		 }
		 
	 
	 public static int createMultiple(String name,String pass,int id) throws SQLException{
		  Connection con=ManageConnection.getConnection();
			
		  String query="insert into bank.account (cust_id,balance)values(?,?)";
		  PreparedStatement st=con.prepareStatement(query);
		   st.setInt(1,id);
		   st.setInt(2,1000);
		  int status=st.executeUpdate();
		 
		
		  return status;
	 }
	 
	
	public static int getCustomerId(String name, String pass) throws Exception {
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
	public static String getUserName(int id) throws SQLException {
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
	public static ArrayList<Pair> getAccountInfo(int id) throws SQLException{
		
		ArrayList<Pair> li=new ArrayList<Pair>();
		Connection con=ManageConnection.getConnection();
		  Statement st=con.createStatement();
		  String query="select * from bank.account where cust_id="+id+"";
		 // System.out.println(id);
		 
		  ResultSet rs=st.executeQuery(query);
		  int acc,bala;
		  while(rs.next()){
			 // System.out.println(id);
			  
			 acc=rs.getInt("acc_no");
			 bala=rs.getInt("balance");
			// System.out.println(acc);
			// System.out.println(bala);
			 Pair p=new Pair();
			 p.acc_no=acc;
			 p.balance=bala;
			 li.add(p);
			 System.out.println(p.acc_no);
			 System.out.println(p.balance);
			
			 }
		  return li;
	}
	
	public static int checkAccount(int acc_no, int id) throws SQLException {
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
	public static int chechUserName(String uname) throws SQLException{
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
}
