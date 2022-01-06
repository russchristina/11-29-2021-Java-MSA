package dao;

import java.sql.Date;

import models.Request;

public interface RequestDAO {

	public Request findByEmp(String username);
	
	public Request findByDate(Date date);
	
	public Request findById(int Id);
	
	public Request findByAmount(int Amount);
	
	public Request findByStatus(String status);
	
	public void submitNew(int id, String employee, int amount, String notes, Date date, String status, String approvedBy);
	
	public void update(Request reimburse);
	
	public void delete(Request reimburse);
}
