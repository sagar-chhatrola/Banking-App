 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/index.css">
 
</head>
<body style="background-image: url(images/bank.jpg);background-repeat:no-repeat;background-size: cover;">

<c:if test="${sessionScope.customer!=null}">
 <c:redirect url="profile.jsp"/>
</c:if>
<a id="reg" href="register.jsp" class="btn btn-info" role="button">Register</a>
<a id="log" href="login.jsp" class="btn btn-info" role="button">Login</a>
<center><h1 style="color:White">My Bank</h1></center>

<div id="errorMessage">${registerMessage}</div> 


<br/>
</body>
</html>