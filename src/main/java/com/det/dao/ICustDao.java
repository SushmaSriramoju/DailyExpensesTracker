package com.det.dao;

import java.sql.SQLException;
import java.util.List;

import com.det.entity.Cust;

public interface ICustDao {
	 public String addexpenses(Cust cust, String email) throws SQLException;
	 public List<Cust> ManageExpenses(String email) throws SQLException;
	 List<Cust> TodaysExpenses(String email) throws SQLException;
	 List<Cust> YesterdaysExpenses(String email) throws SQLException ;
	 List<Cust> Last7daysexpenses(String email) throws SQLException;
	 List<Cust> CurrentMonthexpenses(String email, int year, int month) throws SQLException ;
	 List<Cust> Totalyearexpenses(String email,int selectedYear) throws SQLException;
	 String UpdateRecord(Cust cust,Integer recordid,String email) throws SQLException;
	 Cust findRecordById(Integer Recordid,String email) throws SQLException;
	 String deleteRecord(Integer Recordid,String email) throws SQLException;
}
