<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	background: #eee !important;	
}

.wrapper {	
	margin-top: 80px;
  margin-bottom: 80px;
}

 

  .form-signin-heading,
	.checkbox {
	  margin-bottom: 30px;
	}
</style>
</head>
<body>
<%@ page import="hello.CustomerDao" %>
<%@ page import="hello.Customer" %>
<%@ page import="hello.Pair" %>

  <%@ page import="java.util.*" %>
  <%@ page import="javax.servlet.http.HttpSession" %>
   <%@ page import="javax.servlet.http.HttpSession" %>
    <%@ page import="javax.servlet.http.HttpServletRequest" %>
<jsp:include page="profile.html" />  
<%! String name,pass,str; 
int status,id;
ArrayList<Integer> acc=null;ArrayList<Integer> balance=null;%> 
 
  <%
  HttpSession s=request.getSession(false);
  if(s!=null){
   name=request.getParameter("name");
  pass=request.getParameter("pass");
   //String query="select password from bank.cust_details where name="+name+"";
       
		//pw.print("hiii");
		Customer c1=(Customer)session.getAttribute("c");
		int id=c1.getId();
		//request.getRequestDispatcher("profile.jsp").include(request, response);
		ArrayList<Pair> li=CustomerDao.getAccountInfo(id);
		System.out.println(li.size());
		acc=new ArrayList<Integer>();
		balance=new ArrayList<Integer>();
		for(int i=0;i<li.size();i++)
		{System.out.println("hii");
		  System.out.println("hii");
			acc.add(li.get(i).acc_no);
			balance.add(li.get(i).balance);
			
		}
		
  }
  else{
	  request.setAttribute("errorMessage", "Please Login First.");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	  
  }
	
  
	
%>  
<div class="wrapper">
            <div style="color: Green;">${errorMessage}</div>  
      <h2 class="form-signin-heading">Account Details</h2>
     
      <br/>
      <pre>Account Number   Balance</pre>
     
      <pre><%
        for(int i=0;i<acc.size();i++){
    	  out.print(acc.get(i)+"                 ");
    	  out.print(balance.get(i));
    	  out.println();
    	  out.println();
    	  
    	 }
      
      %>    
      
      <br/> </pre>
     <br/>
  
   
  </div>
</body>
</html>