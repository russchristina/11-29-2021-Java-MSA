package dao;

public interface managerDAO {
	
	public void viewAllPending();
	
	public void viewAllComplete();
	
	public void viewAllApproved();
	
	public void viewAllDenied();
	
	public void viewAll();
	
	public void submitNew();
	
	public void update();
	
	public void approve();
	
	public void findByEmp();
	
	public void findByDate();
	
	public void findById();
	
	public void findByAmount();
	
	
}
