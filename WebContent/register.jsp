<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/register.css">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="js/registerValidation.js"></script>
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>

</head>

<body style="background-image: url(images/bank1.jpg);background-repeat:no-repeat;background-size: cover;">
<a id="reg" href="register.jsp" class="btn btn-info" role="button">Register</a>
<a id="log" href="login.jsp" class="btn btn-info" role="button">Login</a>

<center><h1 style="color:white">My Bank</h1></center>

<div class="wrapper">
  <div class="container">

      <center> <h2 style="color:white">Please register</h2></center>
  
              <form class="form-signin" name="registerForm" action="Register" method="post">
                <div class="form-group">
                 <label for="usr">UserName:</label>
                 <input type="text" class="form-control" id="usr" name="name">
                   <div style="color: #FF0000;">${userName}</div>
                </div>
                <div class="form-group">
                 <label for="usr">Email:</label>
                 <input type="email" class="form-control" id="email" name="email">
                </div>
                <div class="form-group">
                 <label for="usr">BirthDate:</label>
                 <input type="date" class="form-control" id="birthDate" name="birthDate">
                </div>
                 <div class="form-group">
                 <label for="usr">Mobile:</label>
                 <input type="text" class="form-control" id="mobileNumber" name="mobileNumber">
                </div>
                 <div class="form-group">
                 <label for="usr">Gender:</label>
                  <br/>
                 <input type="radio"  id="gender" name="gender" value="Male">Male
                 <input type="radio"  id="gender" name="gender" value="Female">Female
                 
                </div>
               <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pass" name="pass">
                <br/>
                         
                        <label for="pwd">Confirm Password:</label>
                 <input type="password" name="confirmPassword" class="form-control" id="confirmPassword">
                 <br/>  
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
               </div>
 
             </form>
    </div>
  </div>
</body>
</html>