package models;

public class Request {

	// Fields
	private int requestId;
	private String employeeName;
	private double amount;
	private String reason;
	private int status;
	private String note;
	
	// Constructor
	public Request(int requestId, String employeeName, double amount, String reason, int status, String note) {
		super();
		this.requestId = requestId;
		this.employeeName = employeeName;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
		this.note = note;
	} // End constructor

	// Constructor
		public Request(int requestId, String employeeName, double amount, String reason, int status) {
			super();
			this.requestId = requestId;
			this.employeeName = employeeName;
			this.amount = amount;
			this.reason = reason;
			this.status = status;
		} // End constructor
	
	// Constructor
	public Request(int requestId, String employeeName, double amount, String reason) {
		super();
		this.requestId = requestId;
		this.employeeName = employeeName;
		this.amount = amount;
		this.reason = reason;
		this.status = 1;
	} // End constructor

	// Getters/setters
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	
} // End class
