package dao;

import java.sql.Date;
import java.util.List;

import models.Employee;
import models.Request;

public interface ManagerDAO {
	
	public Request viewAllPending(String status);
	
	public Request viewAllComplete(String status);
	
	public Request viewAllApproved(String status);
	
	public Request viewAllDenied(String status);
	
	public List<Request> viewAll();
	
	public void submitNew();
	
	public void update(Request reimburse);
	
	public void approve(Request reimburse);
	
	public List<Employee> findAllEmp();

	public Request findByEmp(String username);

	public Request findByDate(Date date);

	public Request findById(int id);

	public Request findByAmount(int amount);
	
	
	
	
}
