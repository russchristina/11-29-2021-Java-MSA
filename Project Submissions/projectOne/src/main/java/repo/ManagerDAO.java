package repo;

import java.sql.Date;
import java.util.List;

import models.Employee;
import models.Request;

public interface ManagerDAO {
	
	public List<Request> viewAllPending(String status);
	
	public List<Request> viewAllComplete(String status);
	
	public List<Request> viewAll();
	
	public void update(int id);
	
	public Request findById(int id);

	public List<Employee> findAllEmp();

	void submitNew(Request request);

	List<Request> viewAllApproved(String status);

	List<Request> viewAllDenied(String status);
	
	public void addEmployee(Employee employee);

	public void approve(int id);


	
	
	
	
}
