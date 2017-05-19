<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/login.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="js/loginValidation.js"></script>
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
</head>
<body style="background-image: url(images/bank.jpg);background-repeat:no-repeat;background-size: cover;">
 <%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<c:if test="${sessionScope.customer!=null}">
 <c:redirect url="profile.jsp"/>
</c:if>

<a id="reg" href="register.jsp" class="btn btn-info" role="button">Register</a>
<a id="log" href="login.jsp" class="btn btn-info" role="button">Login</a>
<center><h1 style="color:white">My Bank</h1></center>
<div class="wrapper">
 
<center><h2 style="color:white"class="form-signin-heading">Please login</h2></center>  
    <form class="form-signin" name="loginForm" action="Login" method="post">       
       <div class="form-group">
      UserName :<input type="text" class="form-control" name="name" placeholder="UserName"/>
       </div>
      
       <div class="form-group">
      PassWord :<input type="password" class="form-control" name="pass" placeholder="Password"/>
       </div>  
       <div style="color: #FF0000;">${errorMessage}</div>  
          
      <label class="checkbox">
        <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> Remember me
      </label>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
    </form>
  </div>
</body>
</html>