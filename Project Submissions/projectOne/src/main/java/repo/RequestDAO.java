package repo;

import java.util.List;
import models.Request;

public interface RequestDAO {
	
	public List<Request> viewAll();
	
	public Request findById(int Id);
	
	public List<Request> findByStatus(String status);
	
	public void submitNew(Request request);

	public List<Request> viewAllPending(String status);

	public List<Request> viewAllComplete(String status);

	
	
}
