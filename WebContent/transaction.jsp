<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/transaction.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-image: url(images/bank.jpg);background-repeat:no-repeat;background-size: cover;">
<jsp:include page="profile.html" /> 
<div class="wrapper">
<div class="container">
 <center> <h2>Enter Details</h2></center>
  
  <form class="form-signin" action="DoTransaction" method="post">
    <div class="form-group">
      <label for="usr">Your Account Number:</label>
       <select name="accountNumber" id="accountNumber">
                 <c:forEach items="${accountNumberList}"  var="list">
                    <option value="${list}" >
                       ${list}
                    </option>
                 </c:forEach>
       </select>
    </div>
    <div class="form-group">
      <label for="usr">Account number which number to Transfer:</label>
       <select name="anotherAccountNumber" id="anotherAccountNumber">
                 <c:forEach items="${allAccountNumberList}"  var="list">
                    <option value="${list}" >
                       ${list}
                    </option>
                 </c:forEach>
       </select>
    </div>
    <div class="form-group">
      <label for="pwd">Enter Ammount:</label>
      <input type="text" class="form-control" id="usr" name="ammount">
      <br/>
      <div style="color: #FF0000;">${errorMessage}</div>  
        <button class="btn btn-lg btn-primary" type="submit">Transfer</button>
    </div>
  </form>
    </div>
  </div>

</body>
</html>