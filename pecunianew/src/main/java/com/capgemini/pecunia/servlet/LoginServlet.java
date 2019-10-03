package com.capgemini.pecunia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.LoginService;
import com.capgemini.pecunia.service.LoginServiceImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("uname");
		String password = request.getParameter("pswd");
		Login loginObject = new Login();
		loginObject.setUsername(username);
		loginObject.setPassword(password);
		LoginService loginService = new LoginServiceImpl();
		try {
			boolean result = loginService.validateEmail(loginObject);
			if(result)
			{
				PrintWriter out =response.getWriter();
				out.println("<h1>Success</h1>");
			}
		} catch (PecuniaException | LoginException e) {
			PrintWriter out =response.getWriter();
			out.println("<h1>Failure</h1><br>"+e.getMessage());
//			System.out.println(e.getMessage());
		}
	}

}
