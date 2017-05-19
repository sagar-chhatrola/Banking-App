<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Admin Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand">Bank Admin</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="GetAllCustomer">Home</a></li>
      <li><a href="GetPendingAccounts">Account</a></li>
      <li style="float:right" ><a href="LogOut" >LogOut</a></li>
    </ul>
  </div>
</nav>
 <center><h1>Pending Account List</h1></center>
  <div id="accountList">
 <table class="table">
		<thead>
        <tr>
            <th>Customer Id</th>
            <th>Account Number</th>
            <th>Approve/Decline</th>
            
        </tr>
        </thead>
        <tbody>
       <c:forEach items="${sessionScope.pendingAccountList}"  var="list">
           <tr>
           <td>${list.customerId}</td>
           <td>${list.accountNumber}</td>
          <td>
           <c:choose>
             <c:when test="${list.approve==false}">
              <a class="btn btn-primary" href="AccountApprove?approve=true&accountNumber=${list.accountNumber}"> Approve</a>
              <a class="btn btn-primary"  href="AccountApprove?approve=false&accountNumber=${list.accountNumber}">  DisApprove</a>
             </c:when>
             <c:otherwise>
             
             </c:otherwise>
           </c:choose>
           </td>
           </tr>
			</c:forEach>
			</table>
 
</div>
  

</body>
</html>