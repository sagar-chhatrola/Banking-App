package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
	 // private static int cust_id;
    public static Connection getConnection(){  
	        Connection con=null;  
	        try{  
	            Class.forName("com.mysql.jdbc.Driver");  
	            con=DriverManager.getConnection("jdbc:mysql://localhost/bank?verifyServerCertificate=false&useSSL=true","root","root");  
	        }catch(Exception e){System.out.println(e);}  
	        return con;  
	    }
	  
	  public static int register(String name,String  pass) throws SQLException{
		  Connection con=getConnection();
		
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
		  Connection con=getConnection();
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
	  
	  public static int Transfer_ammount(Integer ammount,Integer acc_no,Integer account_transfer,int id) throws SQLException{ 
		  
		
		  Connection con=getConnection();
		  Statement st=con.createStatement();
		  int n=0;
		  
		int n1=accountValidation(id, acc_no);
		int n2=accountValidation1(id, account_transfer);
		if(n1!=0 && n2!=0)
		{
			
		
		  String str="select * from bank.account where acc_no="+acc_no+"";
		 
		  System.out.println(str);
		  
		ResultSet rs=st.executeQuery(str);
		
		
		
		//System.out.println(balance2);
		
		  while(rs.next())
		  {
			  if(rs.getInt(3)>ammount && ammount!=null)
			  {
				  Integer balance1=rs.getInt(3);
				  System.out.println(balance1);
				 // System.out.println(rs.getInt("balance"));
				  Statement st1=con.createStatement();
				 
				  String credit="update bank.account set balance='"+balance1+"'-'"+ammount+"'where acc_no='"+acc_no+"'";
				  st1.executeUpdate(credit);
				  System.out.println(credit);
				  String str1="select balance from bank.account where acc_no="+account_transfer+"";
				  ResultSet rs1=st1.executeQuery(str1);
				  int balance2=0;
				 while(rs1.next()){
					  balance2=rs1.getInt("balance");
				 }
				String debit="update bank.account set balance='"+balance2+"'+'"+ammount+"'where acc_no='"+account_transfer+"'";
				  st1.executeUpdate(debit);
				  n=1;
				 // String list="Transfer "+ammount+" Rs from Account No "+acc_no+" to "+account_transfer+""; 
				 String history_query="insert into bank.transcation (amount,credited_acc,debited_acc) values('"+ammount+"','"+account_transfer+"','"+acc_no+"')";
				  
				 st1.execute(history_query);//return 1;
			  }
			  else
			  {
				  n=0;
			  }
		  }
		}
		  return n;
	  }
	  public static int checkAccount(int acc_no,int id) throws SQLException{
		  Connection con=getConnection();
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
	 
	 public static ArrayList<String> listOfTransaction(int acc_no) throws SQLException{
		  ArrayList<String> al=new ArrayList<String>();
		  Connection con=getConnection();
		  Statement st=con.createStatement();
		  
			String query="select * from bank.transcation where credited_acc in('"+acc_no+"')";	
			String query1="select * from bank.transcation where debited_acc in('"+acc_no+"')";	
			
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				String str="Transfer "+rs.getInt("amount")+" Rs from Account"+rs.getInt("debited_acc")+" To Account "+rs.getInt("credited_acc")+"";
			    al.add(str+"<br/>");
			}
			ResultSet rs1=st.executeQuery(query1);
			while(rs1.next())
			{
				String str="Transfer "+rs1.getInt("amount")+" Rs from Account"+rs1.getInt("debited_acc")+" To Account "+rs1.getInt("credited_acc")+"";
			    al.add(str+"<br/>");
			}
			
		 
			return al;
	 }
	 
	 public static int getCustomerId(String name,String pass) throws Exception{
		 String query="select cust_id from bank.cust_details where name='"+name+"' and password='"+pass+"'";
		 Connection con=getConnection();
		  Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery(query);
		  if(rs.next())
		  {
			  int id=rs.getInt("cust_id");
			  return id;
		  }
		 return 0;
	 }
	 public static int createMultiple(String name,String pass,int id) throws SQLException{
		  Connection con=getConnection();
			
		  String query="insert into bank.account (cust_id,balance)values(?,?)";
		  PreparedStatement st=con.prepareStatement(query);
		   st.setInt(1,id);
		   st.setInt(2,1000);
		  int status=st.executeUpdate();
		 
		
		  return status;
	 }
	 
	public static int accountValidation(int id,int acc_no) throws SQLException{
		String query="select acc_no from bank.account where cust_id='"+id+"'";
		Connection con=getConnection();
		  Statement st=con.createStatement();
		  ResultSet rs2=st.executeQuery(query);
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
	public static int accountValidation1(int id,int acc_no_transfer) throws SQLException{
		String query="select acc_no from bank.account";
		Connection con=getConnection();
		  Statement st=con.createStatement();
		  ResultSet rs2=st.executeQuery(query);
		  List<Integer> li=new ArrayList<Integer>();
		  
		 int n=0;
		  while(rs2.next()){
			  li.add(rs2.getInt("acc_no"));
		      
		  }
		  if(li.contains(acc_no_transfer)){
			  n=1;
		  }
		  return n;
	}
	
	public static ArrayList<Pair> getAccountInfo(int id) throws SQLException{
		
		ArrayList<Pair> li=new ArrayList<Pair>();
		Connection con=getConnection();
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
	
	
	 public static int chechUserName(String uname) throws SQLException{
		 Connection con=getConnection();
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
