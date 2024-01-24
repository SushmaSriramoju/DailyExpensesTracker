package com.det.entity;

public class Cust {

	String Item;
	String Date;
	String Description;
	Integer Price,Total_Price;
	String email;
	Integer Recordid;

	public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}

	public Integer getPrice() {
		return Price;
	}
	public void setPrice(Integer price) {
		Price = price;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getTotal_Price() {
		return Total_Price;
	}
	public void setTotal_Price(Integer total_Price) {
		Total_Price = total_Price;
	}
	public Integer getRecordid() {
		return Recordid;
	}
	public void setRecordid(Integer recordid) {
		Recordid = recordid;
	}
	
}
