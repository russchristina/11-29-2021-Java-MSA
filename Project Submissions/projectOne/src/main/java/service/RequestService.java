package service;

import java.util.Collections;
import java.util.List;

import models.Request;
import repo.RequestDAO;
import repo.RequestImpl;

public class RequestService {
	
	private RequestDAO requestImpl;
	
	public RequestService() {
		this.requestImpl = new RequestImpl();
	}
	
	public List<Request> findAllOrderedByEmp(){
		
		List<Request> retrievedRequests = requestImpl.viewAll();
		
		Collections.sort(retrievedRequests,
				(i1, i2) -> i1.getEmployee().compareTo(i2.getEmployee()));
	
		return retrievedRequests;
	}

	public List<Request> viewAll() {
		return this.requestImpl.viewAll();
	}

	public void submitNew(Request request) {
		this.requestImpl.submitNew(request);
	}

	public Request findById(int id) {
		return this.requestImpl.findById(id);
	}
	
	public List<Request> findByStatus(String status) {
		return this.requestImpl.findByStatus(status);
	}

	public List<Request> viewAllPending(String status) {
		return this.requestImpl.viewAllPending(status);
	}

	public List<Request> viewAllComplete(String status) {
		return this.requestImpl.viewAllComplete(status);
	}
	
	

	
}
