package models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "requests")
public class Request {

	// Fields
	@Id
	@Column(name = "request_id")
	@GeneratedValue(generator = "request_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "request_id_seq")
	private int requestId;
	@Column(name = "employee_name")
	private String employeeName;
	@Column(name = "amount", columnDefinition = "NUMERIC")
	private double amount;
	@Column(name = "reason")
	private String reason;
	@Column(name = "status")
	private String status;
	@Column(name = "note")
	private String note;
	
	// Constructor
	public Request(int requestId, String employeeName, double amount, String reason, String status, String note) {
		super();
		this.requestId = requestId;
		this.employeeName = employeeName;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
		this.note = note;
	} // End constructor
	
	// Constructor
	public Request(String employeeName, double amount, String reason) {
		super();
		this.employeeName = employeeName;
		this.amount = amount;
		this.reason = reason;
		this.status = "Pending";
	} // End constructor

	// Constructor
	public Request(String employeeName, double amount) {
		super();
		this.employeeName = employeeName;
		this.amount = amount;
	} // End constructor
	
	// Constructor
	public Request() {}
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
		return "Request [requestId=" + requestId + ", employeeName=" + employeeName + ", amount=" + amount + ", reason="
				+ reason + ", status=" + status + ", note=" + note + "]";
	}
} // End class
