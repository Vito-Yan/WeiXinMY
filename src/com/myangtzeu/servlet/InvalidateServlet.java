package com.myangtzeu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InvalidateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          if(request.getSession(false)!=null){  
                request.getSession().invalidate();  
      
                //É¾³ý×Ô¶¯µÇÂ¼ cookie  
                Cookie cookie = new Cookie("cookie", "");  
                cookie.setPath(request.getContextPath());  
                cookie.setMaxAge(0);  
                response.addCookie(cookie);  
            }  
            response.sendRedirect("WeiXinServlet");  
    }
}