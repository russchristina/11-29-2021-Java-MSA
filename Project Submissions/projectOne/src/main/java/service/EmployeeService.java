package service;

import java.sql.Date;
import java.util.List;

import models.Request;
import repo.EmployeeDAO;
import repo.EmployeeImpl;

public class EmployeeService {
	
	private EmployeeDAO employeeImpl;
	
	public EmployeeService() {
		this.employeeImpl = new EmployeeImpl();
	}

	public List<Request> viewAll() {
		return this.employeeImpl.viewAll();
	}

	public void submitNew(Request Request) {
		this.employeeImpl.submitNew(Request);
			}

	public List<Request> viewAllPending(String status) {
		return this.employeeImpl.viewAllPending(status) ;
	}

	public List<Request> viewAllComplete(String status) {
		return this.employeeImpl.viewAllComplete(status);
	}

	public void update(int id) {
		this.employeeImpl.update(id);
		
	}

	public void delete(int id) {
		this.employeeImpl.delete(id);
		
	}


}
