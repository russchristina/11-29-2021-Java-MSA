package service;

import java.util.List;

import models.Employee;
import repo.ManagerDAO;
import repo.ManagerImpl;

public class ManagerService {

	private ManagerDAO managerImpl;
	
	public ManagerService() {
		this.managerImpl = new ManagerImpl();
	}
	
	public void save(Employee employee) {
		this.managerImpl.addEmployee(employee);
	}

	public List<Employee> findAllEmp() {
		return this.managerImpl.findAllEmp();
		
	}
	
	public void approve(int id) {
		this.managerImpl.approve(id);
	}
	
	
}
