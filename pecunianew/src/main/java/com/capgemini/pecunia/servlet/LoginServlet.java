package com.capgemini.pecunia.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.service.LoginService;
import com.capgemini.pecunia.service.LoginServiceImpl;
import com.capgemini.pecunia.util.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside Login Servlet");
		
		HttpSession session = request.getSession(false);
		if (session == null) {
		  //TODO
		}

		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {  }
		Map<String,String> myMap = new HashMap<String, String>();
 
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		myMap = objectMapper.readValue(jb.toString(), HashMap.class);
	    String username = myMap.get("uname");
	    String password = myMap.get("pswd");
		System.out.println("Here : "+username+" "+password);
		Login loginObject = new Login();
		loginObject.setUsername(username);
		loginObject.setPassword(password);
	    
		
		
		LoginService loginService = new LoginServiceImpl();
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Access-Control-Allow-Headers" ,"Content-Type, Authorization, Content-Length, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		boolean result=false;
		PrintWriter out =response.getWriter();
	
		try {
			
			result = loginService.validateEmail(loginObject);
			String name=request.getParameter("uname");
			System.out.println("name - " + name);
			if(result)
			{
				//HttpSession session=request.getSession();  
		        session.setAttribute("username",username);
				((ObjectNode) dataResponse).put("success", result);
				((ObjectNode) dataResponse).put("message",Constants.LOGIN_SUCCESSFUL);
			}else {
				throw new PecuniaException(ErrorConstants.LOGIN_ERROR);
			}
			
		} catch (PecuniaException | LoginException e) {
			((ObjectNode) dataResponse).put("success", result);
			((ObjectNode) dataResponse).put("message", e.getMessage());

		}
		finally {
			out.print(dataResponse);
		}
			
	}

//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		resp.setContentType("application/json");
//		resp.addHeader("Access-Control-Allow-Origin", "*");
//		resp.getWriter().write("{}");
//	}

}
