<%@page import="Service.CustomerServiceImpl"%>
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
<jsp:include page="profile.html" /> 
<%@ page import="dao.*" %>
<%@ page import="pojo.Customer" %>
<%@ page import="hello.Pair" %>

<%@ page import="Service.*" %>
  <%@ page import="java.util.*" %>
  <%@ page import="javax.servlet.http.HttpSession" %>
   <%@ page import="javax.servlet.http.HttpSession" %>
    <%@ page import="javax.servlet.http.HttpServletRequest" %>
    
    <%!ArrayList<StringBuffer> al=null; int acc_no,n=0;{%>
<%
HttpSession s=request.getSession(false);
   if(s.getAttribute("c")!=null)
 {
//PrintWriter pw=response.getWriter();
String acc=request.getParameter("acc_no");
 //acc_no=Integer.parseInt(request.getParameter("acc_no"));
 boolean flag=true;
 for(int i=0;i<acc.length();i++)
 {
	 if(Character.isLetter(acc.charAt(i)))
	 {
		 flag=false;
		 break;
	 }
 }
if(flag==true && request.getParameter("acc_no")!="")
{
	acc_no=Integer.parseInt(request.getParameter("acc_no"));
 
try {
	Customer c=(Customer) session.getAttribute("c");
	int id=c.getId();
	System.out.println(id);
CustomerServiceImpl cs=new CustomerServiceImpl();
	n=cs.checkAccount(acc_no,id);
	System.out.println(n);
	out.println("<br/>");
	if(n==0)
	{
		//response.sendRedirect("account_number.jsp");
		//request.getRequestDispatcher("account_number.jsp").include(request,response);
		//out.println("<br/>this is not your account number.<br/>");
		al=null;
		al=new ArrayList<StringBuffer>();
		request.setAttribute("errorMessage", "please enter your account number");
		request.getRequestDispatcher("account_number.jsp").forward(request,response);
	}
	else{
		al=CustomerDao.listOfTransaction(acc_no);
		
	 }
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    }
  }
   else{
	   request.setAttribute("errorMessage", "please enter valid account number");
		request.getRequestDispatcher("account_number.jsp").forward(request,response);
  }
    }
else{
	request.setAttribute("errorMessage", "please login first");
	request.getRequestDispatcher("login.jsp").forward(request,response);
}
 %>
<div class="wrapper">
          
        <h2>Account History</h2>
        <br/>
        <pre><h4> Ammount      Credited_Acc     Debited_Acc      Transaction_id      Date_Time </h4></pre>
        <pre><%if(n!=0) 
        for(StringBuffer list:al)
		{
			out.println("->  "+list+"");
			
			
			}%>
			</pre>
			<%!al=null;al=new ArrayList<StringBuffer>(); }%>
  </div>
</body>
</html>