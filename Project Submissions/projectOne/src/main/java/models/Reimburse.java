package models;

import java.sql.Date;

public class Reimburse {

	private int id;
	private String employee;
	private int amount;
	private Date date;
	private boolean approval;
	private String approvedBy;
	
	public Reimburse() {
		super();
	}

	public Reimburse(int id, String employee, int amount, Date date, boolean approval, String approvedBy) {
		super();
		this.id = id;
		this.employee = employee;
		this.amount = amount;
		this.date = date;
		this.approval = approval;
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

	public int getAmount() {
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

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + (approval ? 1231 : 1237);
		result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimburse other = (Reimburse) obj;
		if (amount != other.amount)
			return false;
		if (approval != other.approval)
			return false;
		if (approvedBy == null) {
			if (other.approvedBy != null)
				return false;
		} else if (!approvedBy.equals(other.approvedBy))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimburse [id=" + id + ", employee=" + employee + ", amount=" + amount + ", date=" + date
				+ ", approval=" + approval + ", approvedBy=" + approvedBy + "]";
	}
	
	
}
