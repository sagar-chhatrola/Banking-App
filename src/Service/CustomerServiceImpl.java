package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ManageDB.ManageConnection;

public class CustomerServiceImpl implements CustomerService {
	


	



	public int Transfer_ammount(Integer ammount, Integer acc_no, Integer account_transfer, int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection con=ManageConnection.getConnection();
		  Statement st=con.createStatement();
		  int n=0;
		  CustomerServiceImpl cs =new CustomerServiceImpl();
		int n1=cs.accountValidation(id, acc_no);
		int n2=cs.accountValidation1(id, account_transfer);
		if(n1!=0 && n2!=0)
		{
			
		
		  String str="select * from bank.account where acc_no="+acc_no+"";
		 
		  System.out.println(str);
		  
		ResultSet rs=st.executeQuery(str);
		
		
		
		//System.out.println(balance2);
		
		  while(rs.next())
		  {
			  if(rs.getInt(3)>=ammount && ammount!=null)
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
				  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				 // String list="Transfer "+ammount+" Rs from Account No "+acc_no+" to "+account_transfer+""; 
				 String history_query="insert into bank.transcation (amount,credited_acc,debited_acc,datetime) values('"+ammount+"','"+account_transfer+"','"+acc_no+"','"+timeStamp+"')";
				  
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
	
	public  int accountValidation(int id,int acc_no) throws SQLException{
		String query="select acc_no from bank.account where cust_id='"+id+"'";
		Connection con=ManageConnection.getConnection();
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
	public  int accountValidation1(int id,int acc_no_transfer) throws SQLException{
		String query="select acc_no from bank.account";
		Connection con=ManageConnection.getConnection();
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
	
	
}
