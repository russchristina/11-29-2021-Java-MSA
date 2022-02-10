package com.revature.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity

@Table(name = "reimbursements")

public class Reports {
	@Id
	@Column
	@GeneratedValue(generator = "reimbursements_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "reimbursements_id", sequenceName = "reimbursements_id")
	private int id;
	@Column
	private int amount;
	@Column
	private String reason;
	@ManyToOne
	private Employee employee;

	public Reports(int id, int amount, String reason, Employee employee) {
		super();
		this.id = id;
		this.amount = amount;
		this.reason = reason;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Reports [id=" + id + ", amount=" + amount + ", reason=" + reason + ", employee=" + employee + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, employee, id, reason);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reports other = (Reports) obj;
		return amount == other.amount && Objects.equals(employee, other.employee) && id == other.id
				&& Objects.equals(reason, other.reason);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Reports() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
