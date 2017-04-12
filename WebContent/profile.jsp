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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/updateForm.css">
<title>Insert title here</title>
</head>
<body>
 <script src="js/profileValidation.js"></script>
   <jsp:include page="profile.html" /> 
   <h2>Profile</h2>
   <table id="updateForm">
   <form class="form-signin" name="updateForm1" action="updateProfile" method="post">   
   <tr>    
       <div class="form-group">
      <td>UserName :</td><td><input type="text"  name="name" id="name" value="${sessionScope.loginUser.name}"/></td>
       </div>
    </tr>  
    <tr>
       <div class="form-group">
      <td>PassWord :</td><td><input type="password" name="pass" id="pass" value="${sessionScope.loginUser.pass}"></td>
       </div> 
    </tr>   
    
    <tr>
        <div class="form-group">
      <td>Email :</td><td><input type="email" name="email" value="${sessionScope.loginUser.email}"></td>
       </div> 
    </tr> 
    <tr>
        <div class="form-group">
      <td>Gender :${sessionScope.loginUser.gender}</td>
      </tr>
      <tr>
      <td><input type="radio" name="gender" value="Male"> Male</td>
      <td>
      <input type="radio" name="gender" value="Female" > Female</td>
       </div> 
    </tr> 
    
    <tr>
        <div class="form-group">
      <td>BirthDate :</td><td><input type="date" name="birthDate" value="${sessionScope.loginUser.date}"></td>
       </div> 
    </tr> 
    <tr>
        <div class="form-group">
      <td>Mobile :</td><td><input type="text" name="mobileNumber" value="${sessionScope.loginUser.mobileNumber}"></td>
       </div> 
    </tr> 
         
   
      </table>
       <button class="btn btn-primary" type="submit" >Update</button>  
     </form>
   
</body>
</html>