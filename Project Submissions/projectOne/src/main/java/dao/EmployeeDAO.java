package dao;

public interface EmployeeDAO {

	public void viewPending(); //to see what invoices are pending
	
	public void viewComplete(); // to see all non-pending invoices
	
	public void viewApproved(); // to see approved invoices
	
	public void viewDenied(); // to see which have been denied
	
	public void viewAll(); //to see all pending and complete invoices
	
	public void submitNew(); // to add new invoice
	
	public void update(); // to update pending invoice
	
	public void delete(); //to cancel pending invoice
	
	
}
