package utilities.repository;

import java.util.List;

import models.Employee;
import models.Request;

public interface EmployeeRequestRepository {

	void createRequest(Request r);
	
	Employee findByEmployeeName(String name);
	
	List<Request> findRequestsByEmployeeName(String name);
	
	List<Request> findAllRequests();
	
	List<Request> findPendingRequests();
	
	public void updateRequest(Request r);
	
	Request highestSpender();
	
	int numberOfRequests();
	
	double averageCost();
} // End interface
