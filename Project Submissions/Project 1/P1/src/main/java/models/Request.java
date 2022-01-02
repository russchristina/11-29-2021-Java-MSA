package models;

import java.util.Objects;

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
	public Request(int requestId, String employeeName, double amount, String reason) {
		super();
		this.requestId = requestId;
		this.employeeName = employeeName;
		this.amount = amount;
		this.reason = reason;
		this.status = 1;
	} // End constructor

	public Request(String employeeName, double amount) {
		super();
		this.employeeName = employeeName;
		this.amount = amount;
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

	@Override
	public int hashCode() {
		return Objects.hash(amount, employeeName, note, reason, requestId, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(employeeName, other.employeeName) && Objects.equals(note, other.note)
				&& Objects.equals(reason, other.reason) && requestId == other.requestId && status == other.status;
	}

	@Override
	public String toString() {
		return "Request id: " + requestId + ", employee name: " + employeeName + ", amount: " + amount + ", reason: "
				+ reason + ", status: " + status + ", note: " + note;
	}
} // End class
