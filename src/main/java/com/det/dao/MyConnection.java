package com.det.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection 
{
private static Connection connection;
	
	private MyConnection() {}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if(connection==null)
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/det","root","Sushma@27");
		}
		return connection; 
	}
}
