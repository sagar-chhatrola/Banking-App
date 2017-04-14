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
<a href="LogOut.jsp" class="btn btn-primary" styele="float:right" >LogOut</a>
<center><h1>Customer List</h1></center>
<div>
Choose Type: <select name="customerType" id="customerType">
                 <option value="1">All</option>
                  <option value="2">Approved</option>
                  <option value="3">Non-Approved</option>
               </select>
</div>
 <div id="customerList">
 
 <data>
 <table class="table">
		<thead>
        <tr>
            <th>Customer Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Mobile</th>
            <th>BirthDate</th>
            <th>Approve</th>
            <th>Show</th>
            
            
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
          <td><a href="CustomerApprove?customerId=${list.id}&approve=${list.approve}&customerType=1" class="btn btn-primary">${list.approve}</a></td>
          
          </tr>
         </c:forEach>
         
    
         </tbody>
			</table>
			
		<script type="text/javascript">
            $('#customerType').change (
            function() {
            	
                $.ajax({
                    type: "GET",
                    url: "GetAllCustomerByAjax",
                    data: {customerType: document.getElementById("customerType").value},
                    success: function(data){               
            	        $("#customerList").html($(data).find("data").html());
            	    }
                });
            }
        );
        </script>
			
</data>
</div>
</body>
</html>