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
         <h3>AccountList</h3>
		<thead>
        <tr>
            <th>Account Id</th>
            <th>Balance</th>
            <th>Show Transaction</th>     
        </tr>
        </thead>
        <tbody>
       
        
       <c:forEach items="${accountInfoList}"  var="list">
           <tr>
           <td>${list.accountNumber}</td>
           <td>${list.balance}</td>
           <td> <a name="account" data-toggle="collapse" class="btn btn-info" data-target="#${list.accountNumber}" data-value="${list.accountNumber}"> Show Transaction</a>
        <div id="${list.accountNumber}" class="collapse transactionList">
        
        </div>
        </td>
           </tr>
            </c:forEach>
         
    
         </tbody>
        </table>
        <script type="text/javascript">
        
        $(function() {
    		
            $('a[name="account"]').click(function() {
            	var value = $(this).data("value");
                $.get("getTransactionHistoryByAjax", {
                	accountNumber:value
               }, function(responseText) {
                   $(".transactionList").html(responseText);
                });
            });
       });
</script>
        
        
       
</body>
</html>