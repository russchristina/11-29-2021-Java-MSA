package dao;

import java.util.List;

import models.Employee;
import models.Request;

public interface EmployeeDAO {

	public List<Request> viewAllPending(String status);
	
	public List<Request> viewAllComplete(String status);
	
	public List<Request> viewAll();
	
	public void submitNew(Request request);
	
	public void update(Request reimburse);
	
	public void delete(Request request);
	
	

	
	
	
}
