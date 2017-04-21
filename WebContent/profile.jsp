<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="pojo.Customer" %>


  <%@ page import="java.util.*" %>
  <%@ page import="javax.servlet.http.HttpSession" %>
  <%@ page import="javax.servlet.http.HttpSession" %>
  <%@ page import="javax.servlet.http.HttpServletRequest" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/updateForm.css">
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="js/profileValidation.js"></script>
  
  

</head>
<body>
  
   <jsp:include page="profile.html" /> 
   <h2>Profile</h2>
   
   
   <form class="form-signin" id="updateForm2" name="updateForm2" action="updateProfile" method="post">   
      
       <div class="form-group">
     UserName :<input type="text"  name="name" id="name" value="${sessionScope.loginUser.name}"/>
       </div>
   
       <div class="form-group">
      PassWord :<input type="password" name="pass" id="pass" value="${sessionScope.loginUser.pass}">
       </div> 
   
        <div class="form-group">
     Email :<input type="email" name="email" value="${sessionScope.loginUser.email}">
       </div> 
    
        <div class="form-group">
      Gender :${sessionScope.loginUser.gender}
      <div>
    Male  <input type="radio" name="gender" value="Male">
    Female <input type="radio" name="gender" value="Female" >
      </div>
       </div> 
    
        <div class="form-group">
     BirthDate :<input type="date" name="birthDate" value="${sessionScope.loginUser.date}">
       </div> 
   
        <div class="form-group">
     Mobile :<input type="text" id="mobileNumber" name="mobileNumber" value="${sessionScope.loginUser.mobileNumber}">
       </div> 
  
       <button class="btn btn-primary" type="submit" >Update</button>  
     </form>
     <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
  </body>
</html>