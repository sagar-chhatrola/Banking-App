<%@page import="pojo.Account"%>
<%@page import="Service.CustomerServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="css/profile.css">
 <link rel="stylesheet" href="css/account.css">
 
</head>
<body>
<%@ page import="dao.*" %>
<%@ page import="pojo.Customer" %>


  <%@ page import="java.util.*" %>
  <%@ page import="javax.servlet.http.HttpSession" %>
   <%@ page import="javax.servlet.http.HttpSession" %>
    <%@ page import="javax.servlet.http.HttpServletRequest"%>
   <jsp:include page="profile.html" /> 
    
<%! String name,pass,str; 
int status,id;
ArrayList<Integer> account=null;ArrayList<Long> balance=null;
{%> 
 
  <%
  

  HttpSession s=request.getSession(false);
   if(s.getAttribute("customer")!=null){
   
		Customer c1=(Customer)s.getAttribute("customer");
		 id=c1.getId();
		
		ArrayList<Account> li=CustomerDao.getAccountInfo(id);
		System.out.println(li.size());
		account=new ArrayList<Integer>();
		balance=new ArrayList<Long>();
		for(int i=0;i<li.size();i++)
		{
			account.add(li.get(i).accountNumber);
			balance.add(li.get(i).balance);
			
		}
		
  }
  else{
	  request.setAttribute("errorMessage", "Please Login First.");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	  
  }
  
  
	
%> 
 
<div class="container">
           
       <div class="row"> 
       <div class="col-md-4">
      <h2 style="">Account Details</h2>
       </div>
        
      <div class="col-md-4 col-md-offset-4">
      <h2 style="font-size: 14;color:navy;"><%="Welcome "+CustomerDao.getUserName(id)+"!!"%></h2>
      </div>
     
      </div>
      <br/>
      
   
    <div class="table-reponsive">
      <table class="table table-striped">
		
        <tr>
            <th>Account Number</th>
            <th>Balance</th>
            <th>Show Transaction</th>
        
             </tr>
    
        <% 
        for(int i=0;i<account.size();i++)
		{ 
			%>
    
        <tr>
            <td><%= account.get(i) %></td>
            <td><%= balance.get(i) %></td>
          <td><a href="http://localhost:8081/OnlineBanking/getTransactionHistory?accountNumber=<%= account.get(i) %>" class="btn btn-md btn-primary">Show Transaction</a></td>
            
        </tr>
        <% }%>
			
			</table>
      
      </div>
      </div>
      <%!}%>
      
      <br/> </pre>
      </
     <br/>
   <div style="color: Green; font-size:25px">${errorMessage}</div> 
   
   
 
</body>
</html>