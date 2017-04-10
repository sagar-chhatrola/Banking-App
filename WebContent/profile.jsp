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
<link rel="stylesheet" href="css/updateForm.css">
<title>Insert title here</title>
</head>
<body>
  <%HttpSession loginSession=request.getSession(false);
    Customer loginUser=(Customer)loginSession.getAttribute("loginUser");
  %>
   <jsp:include page="profile.html" /> 
   <h2>Profile</h2>
   <table id="updateForm">
   <form class="form-signin" name="updateForm" action="updateProfile" method="post">   
   <tr>    
       <div class="form-group">
      <td>UserName :</td><td><input type="text"  name="name" value="<%=loginUser.getName() %>"/></td>
       </div>
    </tr>  
    <tr>
       <div class="form-group">
      <td>PassWord :</td><td><input type="password" name="pass" value="<%=loginUser.getPass()%>"/></td>
       </div> 
    </tr>   
    
    <tr>
        <div class="form-group">
      <td>Email :</td><td><input type="email" name="email" value="<%=loginUser.getEmail()%>"/></td>
       </div> 
    </tr> 
    <tr>
        <div class="form-group">
      <td>Gender :</td><td><input type="text" name="gender" value="<%=loginUser.getGender()%>"/></td>
       </div> 
    </tr> 
    
    <tr>
        <div class="form-group">
      <td>BirthDate :</td><td><input type="date" name="birthDate" value="<%=loginUser.getDate()%>"/></td>
       </div> 
    </tr> 
    <tr>
        <div class="form-group">
      <td>Mobile :</td><td><input type="text" name="mobileNumber" value="<%=loginUser.getMobileNumber()%>"/></td>
       </div> 
    </tr> 
         
   
      </table>
       <button class="btn btn-primary" type="submit">Update</button>  
     </form>
   
</body>
</html>