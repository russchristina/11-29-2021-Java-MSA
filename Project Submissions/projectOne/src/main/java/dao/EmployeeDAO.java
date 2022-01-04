package dao;

public interface EmployeeDAO {

	public void viewPending();
	
	public void viewComplete();
	
	public void viewApproved();
	
	public void viewDenied();
	
	public void viewAll();
	
	public void submitNew();
	
	public void update();
	
	public void delete();
	
	
}
