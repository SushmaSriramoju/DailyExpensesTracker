package com.det.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.det.entity.Cust;

public class CustDaoImpl implements ICustDao{
	private Connection connection;
	private String sql,sql1;
	private PreparedStatement pst,pst1;
	private ResultSet rs,rs1;
	
	public CustDaoImpl() throws ClassNotFoundException, SQLException
	{ 
		connection = MyConnection.getConnection();
	}
	
	public String addexpenses(Cust cust, String email) throws SQLException
	{
		    
		   
		        sql = "insert into expensesDetails (item, date, price, description, email) values(?,?,?,?,?)";
		        pst = connection.prepareStatement (sql);
		        
		        pst.setString(1, cust.getItem());
		        pst.setString(2, cust.getDate());
		        pst.setInt(3,cust.getPrice());
		        pst.setString(4,cust.getDescription());
		        pst.setString(5,email);
		        
		        pst.executeUpdate();
		        return "Dashboard.html";
	}

	public List<Cust> ManageExpenses(String email) throws SQLException
	{
	    List<Cust> list = new ArrayList<Cust>();
	    sql = "SELECT * FROM expensesDetails WHERE email = ?";
	    
	    pst = connection.prepareStatement(sql);
	        pst.setString(1, email);
	        ResultSet rs = pst.executeQuery();
	        
	        while (rs.next()) 
	        {
	            Cust cust = new Cust();
	            cust.setEmail(rs.getString("email"));
	            cust.setItem(rs.getString("item"));
	            cust.setDate(rs.getString("date"));
	            cust.setPrice(rs.getInt("price"));
	            cust.setDescription(rs.getString("description"));
	            cust.setRecordid(rs.getInt("recordid"));
	            list.add(cust);
	        }
	        return list;
	}

	public List<Cust> TodaysExpenses(String email) throws SQLException 
	{
	    List<Cust> list = new ArrayList<Cust>();
	    LocalDate currentDate = LocalDate.now(); 

	 
	   sql = "SELECT item, date, price, description, email FROM expensesDetails WHERE email = ? AND date = ?";
	    
	   sql1 = "SELECT SUM(price) AS total_price FROM expensesDetails WHERE email = ? AND date = ?";

	    pst = connection.prepareStatement(sql);
	    pst1= connection.prepareStatement(sql1); 

	        pst.setString(1, email);
	        pst.setString(2, currentDate.toString());
	        ResultSet rs = pst.executeQuery();

	        pst1.setString(1, email);
	        pst1.setString(2, currentDate.toString());
	        ResultSet rs1 = pst1.executeQuery();

	        int totalSum = 0;

	        if (rs1.next()) 
	        {
	            totalSum = rs1.getInt("total_price");
	        }

	        while (rs.next()) {
	            Cust cust = new Cust();
	            cust.setEmail(rs.getString("email"));
	            cust.setItem(rs.getString("item"));
	            cust.setDate(rs.getString("date"));
	            cust.setPrice(rs.getInt("price"));
	            cust.setDescription(rs.getString("description"));
	            cust.setTotal_Price(totalSum); // Set the total price for each record
	            
	            list.add(cust);
	        }
	    

	    return list;
	}
	
	

	public List<Cust> YesterdaysExpenses(String email) throws SQLException 
	{
	    List<Cust> list = new ArrayList<Cust>();
	    
	    LocalDate currentDate = LocalDate.now();
	    LocalDate yesterdayDate = currentDate.minusDays(1); 

	 
	   sql = "SELECT item, date, price, description, email FROM expensesDetails WHERE email = ? AND date = ?";
	    
	   sql1 = "SELECT SUM(price) AS total_price FROM expensesDetails WHERE email = ? AND date = ?";

	    pst = connection.prepareStatement(sql);
	         pst1= connection.prepareStatement(sql1); 

	        pst.setString(1, email);
	        pst.setString(2, yesterdayDate.toString());
	        ResultSet rs = pst.executeQuery();

	        pst1.setString(1, email);
	        pst1.setString(2, yesterdayDate.toString());
	        ResultSet rs1 = pst1.executeQuery();

	        int totalSum = 0;

	        if (rs1.next()) 
	        {
	            totalSum = rs1.getInt("total_price");
	        }

	        while (rs.next()) {
	            Cust cust = new Cust();
	            cust.setEmail(rs.getString("email"));
	            cust.setItem(rs.getString("item"));
	            cust.setDate(rs.getString("date"));
	            cust.setPrice(rs.getInt("price"));
	            cust.setDescription(rs.getString("description"));
	            cust.setTotal_Price(totalSum); // Set the total price for each record
	            list.add(cust);
	        }
	    

	    return list;
	}
	
	
	
	public List<Cust> Last7daysexpenses(String email) throws SQLException 
	{
	    List<Cust> list = new ArrayList<Cust>();
	    
	    LocalDate endDate = LocalDate.now();
	    LocalDate startDate = endDate.minusDays(7);

	 
	   sql = "SELECT item, date, price, description, email FROM expensesDetails WHERE email = ? AND date >= ? AND date <= ?";
	    
	   sql1= "SELECT SUM(price) AS total_price FROM expensesDetails WHERE email = ? AND date >= ? AND date <= ?";

	    pst = connection.prepareStatement(sql);
	         pst1= connection.prepareStatement(sql1); 

	        pst.setString(1, email);
	        pst.setString(2, startDate.toString());
	        pst.setString(3, endDate.toString());
	        ResultSet rs = pst.executeQuery();

	        pst1.setString(1, email);
	        pst1.setString(2, startDate.toString());
	        pst1.setString(3, endDate.toString());
	        ResultSet rs1 = pst1.executeQuery();

	        int totalSum = 0;

	        if (rs1.next()) 
	        {
	            totalSum = rs1.getInt("total_price");
	        }

	        while (rs.next()) {
	            Cust cust = new Cust();
	            cust.setEmail(rs.getString("email"));
	            cust.setItem(rs.getString("item"));
	            cust.setDate(rs.getString("date"));
	            cust.setPrice(rs.getInt("price"));
	            cust.setDescription(rs.getString("description"));
	            cust.setTotal_Price(totalSum); // Set the total price for each record
	            list.add(cust);
	        }
	    

	    return list;
	}


	public List<Cust> CurrentMonthexpenses(String email, int year, int month) throws SQLException 
	{
	    List<Cust> list = new ArrayList<Cust>();
	    
	 
	   sql = "SELECT item, date, price, description,  email FROM expensesDetails WHERE email = ? AND  YEAR(date) = ? AND MONTH(date) = ?";
	    
	   sql1= "SELECT SUM(price) AS total_price FROM expensesDetails WHERE email = ? AND  YEAR(date) = ? AND MONTH(date) = ?";

	    pst = connection.prepareStatement(sql);
	         pst1= connection.prepareStatement(sql1); 

	        pst.setString(1, email);
	        pst.setInt(2, year);
	        pst.setInt(3, month);
	        ResultSet rs = pst.executeQuery();

	        pst1.setString(1, email);
	        pst1.setInt(2, year);
	        pst1.setInt(3,month );
	        ResultSet rs1 = pst1.executeQuery();

	        int totalSum = 0;

	        if (rs1.next()) 
	        {
	            totalSum = rs1.getInt("total_price");
	        }

	        while (rs.next()) {
	            Cust cust = new Cust();
	            cust.setEmail(rs.getString("email"));
	            cust.setItem(rs.getString("item"));
	            cust.setDate(rs.getString("date"));
	            cust.setPrice(rs.getInt("price"));
	            cust.setDescription(rs.getString("description"));
	            cust.setTotal_Price(totalSum); // Set the total price for each record
	            list.add(cust);
	        }
	    

	    return list;
	}

	public List<Cust> Totalyearexpenses(String email,int selectedYear) throws SQLException 
	{
	    List<Cust> list = new ArrayList<Cust>();
	    
	 
	   sql = "SELECT item, date, price, description, email FROM expensesDetails WHERE email = ? AND  YEAR(date) = ?" ;
	    
	    sql1= "SELECT SUM(price) AS total_price FROM expensesDetails WHERE email = ? AND  YEAR(date) = ? ";

	    pst = connection.prepareStatement(sql);
	    pst1= connection.prepareStatement(sql1); 
	    
	    pst.setString(1, email);
	    pst.setInt(2, selectedYear);
	    ResultSet rs = pst.executeQuery();
	     
	     pst1.setString(1, email);
		 pst1.setInt(2, selectedYear);
	     ResultSet rs1 = pst1.executeQuery();

	       
	        int totalSum = 0;

	        if (rs1.next()) 
	        {
	            totalSum = rs1.getInt("total_price");
	        }

	        while (rs.next()) {
	            Cust cust = new Cust();
	            cust.setEmail(rs.getString("email"));
	            cust.setItem(rs.getString("item"));
	            cust.setDate(rs.getString("date"));
	            cust.setPrice(rs.getInt("price"));
	            cust.setDescription(rs.getString("description"));
	            cust.setTotal_Price(totalSum); 
	            list.add(cust);
	        }
	    

	    return list;
	}
	

	public String UpdateRecord(Cust cust,Integer recordid, String email) throws SQLException
	{
		System.out.println("Cust = " + cust);
		sql = "UPDATE expensesDetails SET item = ?, date = ?, price = ?, description = ? WHERE recordid = ? and email = ?";
		pst = connection.prepareStatement(sql);
		
		 pst.setString(1, cust.getItem());
	     pst.setString(2, cust.getDate());
	     pst.setInt(3,cust.getPrice());
	     pst.setString(4,cust.getDescription());
	     pst.setInt(5,recordid);
	     pst.setString(6, email);
		pst.executeUpdate();
			
		return "Dashboard.html";
		}
	
	
	public Cust findRecordById(Integer Recordid, String email) throws SQLException
	{
		sql = "select * from expensesDetails where recordid = ? and email = ? ";
		pst = connection.prepareStatement(sql);
		pst.setInt(1, Recordid);
		pst.setString(2, email);
		rs = pst.executeQuery();
		
		if(rs.next())
		{
			Cust cust = new Cust();
			
			cust.setRecordid(rs.getInt("recordid"));
			cust.setItem(rs.getString("item"));
			cust.setDate(rs.getString("date"));
			cust.setPrice(rs.getInt("price"));
			cust.setDescription(rs.getString("description"));
			return cust;
		}
		else 
		{
			return null;
		}
	}

	public String deleteRecord(Integer Recordid,String email) throws SQLException
	{
		sql = "delete from expensesDetails where recordid = ? and email = ?";
		pst = connection.prepareStatement(sql);
		pst.setInt(1, Recordid);
		pst.setString(2, email);
		pst.executeUpdate();
		
		return"/Manageexpenses";
	}
	 
}