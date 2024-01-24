package com.det.dao;

import java.sql.SQLException;
import java.util.List;

import com.det.entity.Cust;
import com.det.entity.User;

public interface IUserDao 
{
	 String validateLoginDetails(User user) throws SQLException;
	 String registerUser(User user) throws SQLException;
	 String UpdateUserName(User user, String uid) throws SQLException;
}
