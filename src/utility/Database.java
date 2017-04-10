package utility;

import java.sql.Connection;
import java.sql.DriverManager;



public class Database {
	private static Database dbInstance;
    private static Connection con ;
	 
	 
	 private Database(){
		 
	 }
	 
	 public static Database getInstance(){
		 if(dbInstance==null){
			 return new Database();
		 }
		 return dbInstance;
	 }
	 
	public  Connection getConnection() {
		if(con==null){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bank?verifyServerCertificate=false&useSSL=true",
					"root", "root");
		    }
			catch (Exception e) {
			System.out.println(e);
		   }
		}
		
		return con;
		
	}
}
