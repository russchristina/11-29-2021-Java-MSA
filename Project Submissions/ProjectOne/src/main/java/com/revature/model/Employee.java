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
//@Table(name = "employee")
//public class Employee {
//
//	@Id
//	@Column(name = "emp_id")
//	@GeneratedValue(generator="emp_id_seq", strategy=GenerationType.AUTO)
//	@SequenceGenerator(allocationSize = 1, name = "emp_id_seq")
//	private int id;
//
//	@Column(name = "emp_name")
//	private String name;
//	@Column(name="emp_username")
//	private String username;
//	@Column(name = "emp_password")
//	private String password;
//	@Column(name = "emp_ismanager")
//	private boolean isManager;
//	public Employee() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public Employee(int id, String name, String username, String password, boolean isManager) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.username = username;
//		this.password = password;
//		this.isManager = isManager;
//	}
//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
//				+ ", isManager=" + isManager + "]";
//	}
//	@Override
//	public int hashCode() {
//		return Objects.hash(id, isManager, name, password, username);
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Employee other = (Employee) obj;
//		return id == other.id && isManager == other.isManager && Objects.equals(name, other.name)
//				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public boolean isManager() {
//		return isManager;
//	}
//	public void setManager(boolean isManager) {
//		this.isManager = isManager;
//	}
//
//	
//
//}
