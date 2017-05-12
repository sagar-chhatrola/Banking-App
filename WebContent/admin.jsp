<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="js/accountShow.js"></script>
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
      <li style="float:right" ><a href="LogOut.jsp" >LogOut</a></li>
    </ul>
  </div>
</nav>
  

<center><h1>Customer List</h1></center>
<div>
Choose Type: <select name="customerType" id="customerType">
                 <option value="All" ${param.customerType eq "1"?'selected':''}>All</option>
                  <option value="Approved" ${param.customerType eq "2"?'selected':''}>Approved</option>
                  <option value="Non-Approved" ${param.customerType eq "3"?'selected':''}>Non-Approved</option>
               </select>
</div>

 <div id="customerList">
 <table class="table">
		<thead>
        <tr>
            <th>Customer Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Mobile</th>
            <th>BirthDate</th>
            <th>Status</th>
            <th>Show Details</th>
            
            
        </tr>
        </thead>
        <tbody>
       <c:forEach items="${allCustomer}"  var="list">
           <tr>
           <td>${list.id}</td>
           <td>${list.name}</td>
           <td>${list.email}</td>
           <td>${list.gender}</td>
           <td>${list.mobileNumber}</td>
           <td>${list.date}</td>
          <td>
           <c:choose>
             <c:when test="${list.approve==false}">
              <a class="btn btn-primary" onclick="javascript:onApprove(this)" data-id="${list.id}" data-approve="true"> Approve</a>
              <a class="btn btn-primary" onclick="javascript:onApprove(this)" data-id="${list.id}" data-approve="false">  DisApprove</a>
             </c:when>
             <c:otherwise>
             Approved
             </c:otherwise>
           </c:choose>
           </td>
        <td>
        <a class="btn btn-info" onclick="javascript:onClick(this)" data-toggle="collapse"  data-value="${list.id}" href="#${list.id}">
        Show Accounts</a>
       </td>
          </tr>
          <tr id="${list.id}"> </tr>
			</c:forEach>
			</table>
 
</div>
 
			 
		<script type="text/javascript">
            $('#customerType').change (
            function() {
            	
                $.ajax({
                    type: "GET",
                    url: "GetAllCustomerByAjax",
                    data: {customerType: document.getElementById("customerType").value},
                    success: function(data){               
            	        $("#customerList").html(data);
            	    }
                });
            }
        );     
        function onApprove(item) {
        	        
                	var id = item.getAttribute("data-id");
                	var approve=item.getAttribute("data-approve");
                    $.get("CustomerApprove", {
                    	customerId:id,
                    	approve:approve,
                    	customerType:document.getElementById("customerType").value
                   }, function(responseText) {
                       $("#customerList").html(responseText);
                    });
                };
                
                function onClick(item) {
                	var value = item.getAttribute("data-value");
                    $.get("GetAccountInfoList", {
                    	customerId:value
                   }, function(responseText) {
                       $("#"+value).html(responseText);
                    });
                };
        </script>
        
         
			

</body>
</html>