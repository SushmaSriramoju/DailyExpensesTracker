package com.det.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.det.dao.CustDaoImpl;
import com.det.entity.Cust;

@WebServlet("/currentmonthExpenses")
public class Currentmonthexpenses extends HttpServlet  
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
	
	res.setContentType("text/html");
	PrintWriter out =res.getWriter();
	String uid = (String) req.getServletContext().getAttribute("uid");
	
	try
	{
			CustDaoImpl CustDao =new CustDaoImpl();
			
			Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            List<Cust> list =CustDao.CurrentMonthexpenses(uid,year, month);
	
				if(list.size()!=0)
					{
						out.println("<head>");
						out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
						out.println("</head>");
						out.print("<table align='center' width='70%' border='1'>");
						out.print("<caption>Expenses Information</caption>");
						out.print("<tr><th>Email</th><th>Item</th><th>Date</th><th>Price</th><th>Description</th></tr>");
						 int totalSum = 0; 

							for (Cust cust : list) 
							{
								out.print("<tr>");
								out.print("<td>" + cust.getEmail() + "</td>");
								out.print("<td>" + cust.getItem() + "</td>");
								out.print("<td>" + cust.getDate() + "</td>");
								out.print("<td>" + cust.getPrice() + "</td>");
								out.print("<td>" + cust.getDescription() + "</td>");
								totalSum = cust.getTotal_Price();

								out.print("</tr>");
							}
							out.print("</table>");
							
							
							 out.print("<h3>Total Sum: " + totalSum + "</h3>");
							 out.print("<hr size='3' color='green'>");
							out.print("<h3><a href='ViewExpenses.html'>Back</a></h3>");


					}
				else
				{
					out.println("<head>");
	            	out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
	            	out.println("</head>");
					out.print("<h3 style='color:red'>  No record Found..");
					out.print("<h3><a href='ViewExpenses.html'>Back</a></h3>");
				}
				
		
	}
	catch (Exception e)
	{
	System.out.println(e);
    }
	}
}