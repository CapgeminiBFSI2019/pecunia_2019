package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.LoginService;
import com.capgemini.pecunia.service.LoginServiceImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("uname");
		String password = request.getParameter("pswd");
		Login loginObject = new Login();
		loginObject.setUsername(username);
		loginObject.setPassword(password);
		LoginService loginService = new LoginServiceImpl();
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		try {
			boolean result = loginService.validateEmail(loginObject);
			String name=request.getParameter("uname");
			if(result)
			{
				HttpSession session=request.getSession();  
		        session.setAttribute("username",username); 
				request.getRequestDispatcher("MainPage.html").forward(request, response); 
			}else {
				throw new PecuniaException("");
			}
			
		} catch (PecuniaException | LoginException e) {
			request.getRequestDispatcher("login.html").include(request, response);
			out.println("<script>$('#loan-request-toast').toast('show');</script>");
		}
		
		out.close();
	}

}
