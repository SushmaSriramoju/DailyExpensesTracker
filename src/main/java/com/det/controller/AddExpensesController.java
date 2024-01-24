package com.det.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.det.dao.CustDaoImpl;
import com.det.entity.Cust;
import com.det.entity.User;

@WebServlet("/Addexpenses")
public class AddExpensesController  extends HttpServlet
{	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
	res.setContentType("text/html"); 
	PrintWriter out = res.getWriter();
	
	String uid = (String) req.getServletContext().getAttribute("uid");
	String Item = req.getParameter("item"); 
	String Date = req.getParameter("date"); 
	Integer Price=Integer.parseInt(req.getParameter("price"));
	String Description = req.getParameter("description");

	try
	{
	Cust cust = new Cust();
	CustDaoImpl CustDao = new CustDaoImpl();
	
	cust.setItem(Item);
	cust.setDate(Date);
	cust.setPrice(Price); 
	cust.setDescription(Description);
	cust.setEmail(uid);
	
	req.getRequestDispatcher (CustDao.addexpenses(cust, uid)).forward(req, res);

	}
	catch (Exception e)
	{
	System.out.println(e);
	}
	}
	}
