package com.myangtzeu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if(account != null && password != null){
			System.out.println(account);
			System.out.println(password);
			session.setAttribute("account", account);
	        session.setAttribute("password", password);
			request.getSession().setAttribute("account", account);
			Cookie cookie = new Cookie("cookie",account+":"+password);  
	        cookie.setPath(request.getContextPath());  
	        cookie.setMaxAge(3600*24*256*4);  
	        response.addCookie(cookie);
	        session.setAttribute("account", account);
	        session.setAttribute("password", password);
//	        request.getRequestDispatcher("WeiXinServlet").forward(request, response);
		}
	}
}
