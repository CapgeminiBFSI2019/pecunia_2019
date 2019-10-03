package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
          
        request.getRequestDispatcher("login.html").include(request, response);  
          
        Cookie ck=new Cookie("cookieName","");  
        ck.setMaxAge(0);  
        response.addCookie(ck);  
          
        out.println("<h4 class='text-success'>You have been successfully logged out. please login again to continue.</h4>");  
		
	
		
		
	}

}
