package dao;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import models.Request;

public interface RequestDAO {
	
	public List<Request> viewAll();

	public List<Request> findByEmp(String username);
	
	public List<Request> findByDate(Date date);
	
	public Request findById(int Id);
	
	public Request findByAmount(int Amount);
	
	public List<Request> findByStatus(String status);
	
	public void submitNew(Request request);
	
	public void update(Request request);
	
	public void delete(Request request);



	
}
