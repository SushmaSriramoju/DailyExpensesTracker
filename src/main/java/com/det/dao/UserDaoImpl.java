package com.det.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.det.entity.Cust;
import com.det.entity.User;

public class UserDaoImpl implements IUserDao
{
	private Connection connection;
	private String sql;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDaoImpl() throws ClassNotFoundException, SQLException 
	{ 
		connection = MyConnection.getConnection();
	}
	public String validateLoginDetails (User user) throws SQLException 
		{
		sql = "select * from detlogin where uid=? and password=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, user.getUid());
			pst.setString(2, user.getPassword());
			rs = pst.executeQuery();

		if (rs.next())
			{
			
				return "Dashboard.html";
				
			} 
		else 
			{
				return "index.html";
			}
		}
	
	
	public String registerUser(User user) throws SQLException 
	{
	    sql = "insert into detlogin values (?, ?, ?)"; 
	    pst = connection.prepareStatement(sql);
	    pst.setString(1, user.getUname());
	    pst.setString(2, user.getUid());
	    pst.setString(3, user.getPassword());
	    
	    pst.executeUpdate();
	    
	    
	    
	    return "index.html";
	}
	
	public String UpdateUserName(User user, String uid) throws SQLException
	{
		sql= "update detlogin set uname=? where uid=?";
		pst = connection.prepareStatement(sql);
		pst.setString(1, user.getUname());
	    pst.setString(2, uid);
	    pst.executeUpdate();
		return "Userprofile.jsp";
	}
}


