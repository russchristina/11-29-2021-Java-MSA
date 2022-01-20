package models;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity

@Table
public class Request {
	
	@Id
	@Column
	@GeneratedValue(generator = "requests_request_id_seq", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(allocationSize = 1, name = "requests_request_id_seq", sequenceName = "requests_request_id_seq")
	private int id;
	@Column
	private String employee;
	@Column
	private int amount;
	@Column
	private String reason;
	@Column
	private Date date;
	@Column
	private String status;
	@Column
	private String approvedBy;
	
	public Request() {
		super();
	}

	public Request(int id, String employee, int amount, String notes, Date date, String status, String approvedBy) {
		super();
		this.id = id;
		this.employee = employee;
		this.amount = amount;
		this.reason = notes;
		this.date = date;
		this.status = status;
		this.approvedBy = approvedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, approvedBy, date, employee, id, reason, status);
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
		return amount == other.amount && Objects.equals(approvedBy, other.approvedBy)
				&& Objects.equals(date, other.date) && Objects.equals(employee, other.employee) && id == other.id
				&& Objects.equals(reason, other.reason) && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", employee=" + employee + ", amount=" + amount + ", reason=" + reason + ", date="
				+ date + ", status=" + status + ", approvedBy=" + approvedBy + "]";
	}

		

	
		
}
