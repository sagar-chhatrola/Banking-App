package ManageDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ManageConnection {
	  public static Connection getConnection(){  
	        Connection con=null;  
	        try{  
	            Class.forName("com.mysql.jdbc.Driver");  
	            con=DriverManager.getConnection("jdbc:mysql://localhost/bank?verifyServerCertificate=false&useSSL=true","root","root");  
	        }catch(Exception e){System.out.println(e);}  
	        return con;  
	    }
}
