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
       <c:forEach items="${sessionScope.allCustomer}"  var="list">
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
        
         </tbody>
			</table>
			<script type="text/javascript">
			 
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