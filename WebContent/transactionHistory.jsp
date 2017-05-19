<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="profile.html" /> 
<%@ page import="dao.*" %>
<%@ page import="pojo.*" %>
<%@ page import="service.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/transactionList.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

   <div> 
    <h2>Account History</h2>
    <br/>
        
 Account Number: <select name="accountNumber" id="accountNumber">
                 <c:forEach items="${accountNumberList}"  var="list">
                    <option value="${list}" ${param.accountNumber eq '${accountNumber}' ? 'selected': ''} >
                       ${list}
                    </option>
                 </c:forEach>
               </select>
      </div>
<div class="wrapper" id="history">

       <table class="table">
		<thead>
        <tr>
            <th>Amount</th>
            <th>Transaction_id</th>
            <th>Date_time</th>
            <th>Summary</th>
            
        </tr>
        </thead>
        <tbody>
       <c:forEach items="${transactionList}"  var="list">
           <tr>
           <td>${list.amount}</td>
           <td>${list.transactionId}</td>
           <td>${list.datetime}</td>
           <td>Transfer amount from Account(${list.debitedAccount}) to Account(${list.creditedAccount})</td>
           </tr>
         </c:forEach>
         </tbody>
			</table>
			<script type="text/javascript">
        
            $('#accountNumber').change (
            function() {
                $.ajax({
                    type: "GET",
                    url: "getTransactionHistoryByAjax",
                    data: {accountNumber: document.getElementById("accountNumber").value},
                    success: function(data){               
            	        $("#history").html(data);
            	    }
                });
            }
        );
</script>
	
			
  </div>
  
</body>
</html>