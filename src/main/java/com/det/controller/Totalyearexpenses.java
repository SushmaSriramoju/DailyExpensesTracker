package com.det.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.det.dao.CustDaoImpl;
import com.det.entity.Cust;

@WebServlet("/totalExpenses")
public class Totalyearexpenses extends HttpServlet  
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    res.setContentType("text/html");
	    PrintWriter out = res.getWriter();
	    String uid = (String) req.getServletContext().getAttribute("uid");
	    String selectedYearStr = req.getParameter("selectedYear");
	    out.println("<head>");
		out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
		out.println("</head>");
		out.println("<body>");
	    out.print("<div class=\"form-container\">");
	    out.print("<form action='totalExpenses' method='get'>");
	    out.print("Select Year: ");
	    out.print("<select name='selectedYear'>");
	    out.print("<option value='2023'>2023</option>");
	    out.print("<option value='2022'>2022</option>");
	    out.print("<option value='2021'>2021</option>");
	    // Add more options as needed
	    out.print("</select>");
	    out.print("<input type='submit' value='Show Expenses'>");
	    out.print("</form>");
	    out.print("</body>");
	    
	    try {
	        if (selectedYearStr != null && !selectedYearStr.isEmpty())
	        {
	            int selectedYear = Integer.parseInt(selectedYearStr);
	            CustDaoImpl CustDao = new CustDaoImpl();
	            List<Cust> list = CustDao.Totalyearexpenses(uid, selectedYear);

	            if (list.size() != 0) 
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
	                out.print("<h3 style='color:red'>No record Found..</h3>");
	                out.print("<h3><a href='ViewExpenses.html'>Back</a></h3>");
	            }
	        } 
	        
	        else 
	        {
	        	out.print("<h3 style='color:green'>Please select a year.</h3>");
	        }
	    } 
	    catch (Exception e) 
	    {
	        System.out.println(e);
	    }
	}
}