//package com.revature.model;
//
//import java.util.Objects;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//@Entity
//@Table(name= "reimb")
//public class Reimb {
//	@Id
//	@Column(name="reimb_id")
//	@GeneratedValue(generator="reimb_id_seq", strategy= GenerationType.AUTO)
//	@SequenceGenerator(allocationSize=1, name = "reimb_id_seq")
//	private int id;
//	@Column(name="emp_name")
//	private String employee;
//	@Column(name="reimb_amt", columnDefinition="numeric")
//	private float amount;
//	@Column(name="reimb_desc")
//	private String description;
//	@Column(name="reimb_status")
//	private String status;
//	@Column(name="reimb_note")
//	private String note;
//	public Reimb() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public Reimb(int id, String employee, float amount, String description, String status, String note) {
//		super();
//		this.id = id;
//		this.employee = employee;
//		this.amount = amount;
//		this.description = description;
//		this.status = status;
//		this.note = note;
//	}
//	@Override
//	public String toString() {
//		return "Reimb [id=" + id + ", employee=" + employee + ", amount=" + amount + ", description=" + description
//				+ ", status=" + status + ", note=" + note + "]";
//	}
//	@Override
//	public int hashCode() {
//		return Objects.hash(amount, description, employee, id, note, status);
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Reimb other = (Reimb) obj;
//		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount)
//				&& Objects.equals(description, other.description) && Objects.equals(employee, other.employee)
//				&& id == other.id && Objects.equals(note, other.note) && Objects.equals(status, other.status);
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getEmployee() {
//		return employee;
//	}
//	public void setEmployee(String employee) {
//		this.employee = employee;
//	}
//	public float getAmount() {
//		return amount;
//	}
//	public void setAmount(float amount) {
//		this.amount = amount;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
//	public String getNote() {
//		return note;
//	}
//	public void setNote(String note) {
//		this.note = note;
//	}
//
//}
