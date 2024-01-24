package com.det.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.det.dao.UserDaoImpl;
import com.det.entity.User;

@WebServlet("/UserProfileServlet")

public class UserProfileController extends HttpServlet 
{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        // Fetch the user's email from the session (assuming it's stored there after login)
        
    	String userId = (String) req.getServletContext().getAttribute("uid");
        // Forward the request to the JSP page with the user's email as an attribute
       req.setAttribute("email", userId);
        
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
    	res.setContentType("text/html");
    	PrintWriter out= res.getWriter();
    	String Name = req.getParameter("uname");
    	String uid = (String) req.getServletContext().getAttribute("uid");
    	
    	//System.out.println(req.getParameter("uname"));

    	try {
    		User user = new User();
			UserDaoImpl userDao = new UserDaoImpl();
			
			user.setUname(Name);
			user.setUid(uid);
			req.getRequestDispatcher (userDao.UpdateUserName(user,uid)).forward (req, res);			
		} 
    	catch (Exception e) {
			
    		System.out.println(e);
		}
    	
    }
}