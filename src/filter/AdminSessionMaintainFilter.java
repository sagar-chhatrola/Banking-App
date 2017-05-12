package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminSessionMaintainFilter
 */
@WebFilter(urlPatterns={"/admin.jsp","/accountAdminpage.jsp"}

		  )
public class AdminSessionMaintainFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminSessionMaintainFilter() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		 HttpServletRequest httpRequest=(HttpServletRequest)request;
         HttpServletResponse httpResponse=(HttpServletResponse)response;
         HttpSession session=httpRequest.getSession(false);
         System.out.println(session);
         String adminName=(String) session.getAttribute("adminName");
         
         if(adminName==null)
         {
       	  
       	 httpResponse.sendRedirect("login.jsp");
         }
         else{
		chain.doFilter(request, response);
         }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
