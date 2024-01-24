package com.det.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.det.dao.UserDaoImpl;
import com.det.entity.User;


@WebServlet("/Signup")
public class SignupController extends HttpServlet
{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{
res.setContentType("text/html");
PrintWriter out= res.getWriter();
String Name = req.getParameter("uname");
String userId= req.getParameter("uid");
String password= req.getParameter("password");
String cpassword = req.getParameter("cpassword");

String namePattern = "^[A-Za-z\\s]+$";
String emailPattern = "^[a-zA-Z].*";


if(Name.matches(namePattern))
{
	if(userId.matches(emailPattern))
	{
		if (password.equals(cpassword) && password.length() >= 8 )
		{
			try
			{
					User user =new User();
					UserDaoImpl userDao= new UserDaoImpl();
					user.setUname(Name);
					user.setUid(userId);
					user.setPassword(password);
					req.getRequestDispatcher (userDao.registerUser(user)). forward (req, res);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else
		{
		out.println("<html><body>");
	    out.println("<script>");
	    out.println("console.log('Please check your username.');");
	    out.println("alert('Both passwords does'nt match.');");
	    out.println("window.location.href = 'index.html';"); // Redirect to the login page
	    out.println("</script>");
	    out.println("</body></html>");
		}
	}
	else
	{
		out.println("<html><body>");
	    out.println("<script>");
	    out.println("alert('Please check your userid.');");
	    out.println("window.history.back();"); // Redirect to the login page
	    out.println("</script>");
	    out.println("</body></html>");
	}
}
	
else
{
	out.println("<html><body>");
    out.println("<script>");
    out.println("alert('Please check your username.');");
    out.println("window.history.back();"); // Redirect to the login page
    out.println("</script>");
    out.println("</body></html>");
}	
}
}