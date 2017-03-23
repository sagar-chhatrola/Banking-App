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
 body  {
    background-image: url("images/bank.jpg");
    background-repeat:no-repeat;
        background-size: cover;
         background-image: transparent;
     width: 100%;
      height: 100%;
   
}

.form-signin {
  max-width: 380px;
  padding:  30px 40px 20px;
 
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid rgba(0,0,0,0.1);  

  .form-signin-heading,
	.checkbox {
	  margin-bottom: 30px;
	}
 }
  .form-signin-heading,
	.checkbox {
	  margin-bottom: 30px;
	}
</style>
</head>
<body>
<jsp:include page="profile.html" />  

<div class="wrapper">
<div class="container">
 <center> <h2>Enter Details</h2></center>
  
  <form class="form-signin" action="transactionList.jsp" method="post">
    <div class="form-group">
      <label for="usr">Enter Your Account Number to see history:</label>
      <input type="text" class="form-control" id="usr" name="acc_no">
      <br/>
      <div style="color: #FF0000;">${errorMessage}</div>   
        
      <button class="btn btn-lg btn-primary" type="submit">Submit</button>
    </div>
  
  </form>
    </div>
  </div>
</body>
</html>