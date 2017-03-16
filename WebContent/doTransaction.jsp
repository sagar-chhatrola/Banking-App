<%@page import="hello.Validate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	background: #eee !important;	
}

.wrapper {	
	margin-top: 80px;
  margin-bottom: 80px;
}

 

  .form-signin-heading,
	.checkbox {
	  margin-bottom: 30px;
	}
</style>
</head>
<body>
<%@ page import="hello.CustomerDao" %>
<%@ page import="hello.Customer" %>
<%@ page import="hello.Pair" %>

  <%@ page import="java.util.*" %>
  <%@ page import="javax.servlet.http.HttpSession" %>
   <%@ page import="javax.servlet.http.HttpSession" %>
    <%@ page import="javax.servlet.http.HttpServletRequest" %>
<jsp:include page="profile.html" /> 
<%//PrintWriter pw=response.getWriter();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    // pw.print(request.getParameter("acc_no"));
	    //pw.print(request.getParameter("Money"));
	    // pw.print(request.getParameter("acc_no_transfer"));
	     HttpSession s =request.getSession(false);
	     if(s!=null)
	     {//request.getParameter("acc_no_transfer")!="";
	    	//Validate.checkNumber((request.getParameter("acc_no")));
	   if(request.getParameter("acc_no_transfer")!="" && request.getParameter("acc_no")!="" && request.getParameter("Money")!="" && Validate.checkNumber((request.getParameter("acc_no"))) && Validate.checkNumber((request.getParameter("Money"))) && Validate.checkNumber((request.getParameter("acc_no_transfer"))))
	   {
		Integer acc_no=Integer.parseInt(request.getParameter("acc_no"));
		Integer ammount=Integer.parseInt(request.getParameter("Money"));
		Integer acc_no_transfer=Integer.parseInt(request.getParameter("acc_no_transfer"));
           
		try {
			Customer c=(Customer) session.getAttribute("c");
			int id=c.getId();
			
    	  
    	  
    	   int n= CustomerDao.Transfer_ammount(ammount,acc_no,acc_no_transfer,id);
    	   if(n>0)
    	   {
    		  //out.println("Successfully transfer<br/><br/>");
    		   request.setAttribute("errorMessage", "Successfully Transfer!!");
				request.getRequestDispatcher("profile.jsp").forward(request, response);
    		   //request.getRequestDispatcher("profile.html").include(request, response);
    	   }
    	   else
    	   {
    		   //out.println("Enter Valiad Input,Incoorect account or not sufficent ammount!!");
    		   //request.getRequestDispatcher("transaction.html").include(request, response);
    		   request.setAttribute("errorMessage", "Enter Valiad Input,Incoorect account or not sufficent ammount!!");
				request.getRequestDispatcher("transaction.jsp").forward(request, response);
    	   }
		
	     }
	   
        catch (Exception e) {
		// TODO Auto-generated catch block
		   e.printStackTrace();
		   
	     }  	
	 }
	   else
	   {
		   request.setAttribute("errorMessage", "Please Enter valid details !!");
			request.getRequestDispatcher("transaction.jsp").forward(request, response);
	   }
	     }  else{
	    	 out.println("please login first");
	    	 request.getRequestDispatcher("login.html").include(request, response);
	     }%> 
</body>
</html>