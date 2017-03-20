<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	
	background-image: url("images/bank1.jpg");
    background-repeat:no-repeat;
        background-size: cover;
           opacity: 1;
           
     width: 100%;
      height: 100%;
}a{
float:right;
margin-right: 15px;
}

.wrapper {	
	margin-top: 80px;
  margin-bottom: 80px;
}

.form-signin {
  max-width: 380px;
  padding: 15px 35px 45px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid rgba(0,0,0,0.1);  

  .form-signin-heading,
	.checkbox {
	  margin-bottom: 30px;
	}

	.checkbox {
	  font-weight: normal;
	}

	.form-control {
	  position: relative;
	  font-size: 16px;
	  height: auto;
	  padding: 10px;
		@include box-sizing(border-box);

		&:focus {
		  z-index: 2;
		}
	}

	input[type="text"] {
	  margin-bottom: -1px;
	  border-bottom-left-radius: 0;
	  border-bottom-right-radius: 0;
	}

	input[type="password"] {
	  margin-bottom: 20px;
	  border-top-left-radius: 0;
	  border-top-right-radius: 0;
	}
}

</style>
</head>
<body>
<a id="reg" href="register.jsp" class="btn btn-info" role="button">Register</a>
<a id="log" href="login.jsp" class="btn btn-info" role="button">Login</a>
<div class="wrapper">
 
<center><h2 style="color:white"class="form-signin-heading">Please login</h2></center>  
    <form class="form-signin" action="Login">       
       <div class="form-group">
      UserName :<input type="text" class="form-control" name="name" placeholder="UserName" required="" autofocus="" />
       </div>
      
       <div class="form-group">
      PassWord :<input type="password" class="form-control" name="pass" placeholder="Password" required=""/>
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