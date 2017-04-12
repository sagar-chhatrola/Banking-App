<%@page import="pojo.Account"%>
<%@page import="service.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
   
<div class="container">
           
       <div class="row"> 
       <div class="col-md-4">
      <h2 style="">Account Details</h2>
       </div>
        
      <div class="col-md-4 col-md-offset-4">
      
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
    
    
        <c:forEach items="${accountInfoList}" var="accountInstance">
        <tr>
            <td>${accountInstance.accountNumber}</td>
            <td>${accountInstance.balance}</td>
          <td><a href="GetTransactionHistory?accountNumber=${accountInstance.accountNumber}" class="btn btn-md btn-primary">Show Transaction</a></td>
            
        </tr>
        </c:forEach>
			
			</table>
      
        </div>
      </div>
      
      
      <br/> 
   <div style="color: Green; font-size:25px">${successTransfer}</div> 
   
   
 
</body>
</html>