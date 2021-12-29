package Dao;

import com.revature.model.Employee;

public interface EmployeeDao {
	public int createEmployee(Employee employee);
	public int updateEmployee(Employee employee);
	public int deleteEmployeeById(int id);
	public void deposit(int EmployeeId, double amount);
	public void withdraw(int EmployeeId, double amount);
}
