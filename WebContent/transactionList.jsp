<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <table class="table">
		<thead>
        <tr>
            <th>Ammount</th>
            <th>Credite_acc</th>
            <th>Debited_acc</th>
            <th>Transaction_id</th>
            <th>Date_time</th>
            
        </tr>
        </thead>
        <tbody>
       <c:forEach items="${transactionList}"  var="list">
           <tr>
           <td>${list.amount}</td>
           <td>${list.creditedAccount}</td>
           <td>${list.debitedAccount}</td>
           <td>${list.transactionId}</td>
           <td>${list.datetime}</td>
           
           </tr>
         </c:forEach>
         </tbody>
			</table>
</body>
</html>