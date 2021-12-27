package my.app;

import java.util.Objects;

public class Account {
	int user_id;
	String username;
	String password;
	String first_name;
	String last_name;
	String address;
	String state;
	int zipCode;
	int linked;
	float balance;
	boolean admin = false;
	boolean employee = false;
	
	public Account() {
		super();
	}
	
	public Account(int user_id, String username, String password, String first_name, String last_name,
			String address, String state, int zipCode, int linked, float balance, boolean admin, boolean employee) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.state = state;
		this.zipCode = zipCode;
		this.linked = linked;
		this.balance = balance;
		this.admin = admin;
		this.employee = employee;
	}
	protected int getUser_id() {
		return user_id;
	}
	protected void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	protected String getUsername() {
		return username;
	}
	protected void setUsername(String username) {
		this.username = username;
	}
	protected String getPassword() {
		return password;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	protected String getFirst_name() {
		return first_name;
	}
	protected void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	protected String getLast_name() {
		return last_name;
	}
	protected void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	protected String getAddress() {
		return address;
	}
	protected void setAddress(String address) {
		this.address = address;
	}
	protected String getState() {
		return state;
	}
	protected void setState(String state) {
		this.state = state;
	}
	protected int getZipCode() {
		return zipCode;
	}
	protected void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	protected int getLinked() {
		return linked;
	}
	protected void setLinked(int linked) {
		this.linked = linked;
	}
	protected float getBalance() {
		return balance;
	}
	protected void setBalance(float balance) {
		this.balance = balance;
	}
	protected boolean isAdmin() {
		return admin;
	}
	protected void setAdmin(boolean admin) {
		this.admin = admin;
	}
	protected boolean isEmployee() {
		return employee;
	}
	protected void setEmployee(boolean employee) {
		this.employee = employee;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(address, admin, balance, employee, first_name, last_name, linked, password, state, user_id,
				username, zipCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(address, other.address) && admin == other.admin
				&& Float.floatToIntBits(balance) == Float.floatToIntBits(other.balance) && employee == other.employee
				&& Objects.equals(first_name, other.first_name) && Objects.equals(last_name, other.last_name)
				&& Objects.equals(linked, other.linked) && Objects.equals(password, other.password)
				&& Objects.equals(state, other.state) && Objects.equals(user_id, other.user_id)
				&& Objects.equals(username, other.username) && zipCode == other.zipCode;
	}
	@Override
	public String toString() {
		return "Account [user_id=" + user_id + ", username=" + username + ", password=" + password + ", first_name="
				+ first_name + ", last_name=" + last_name + ", address=" + address + ", state=" + state + ", zipCode="
				+ zipCode + ", linked=" + linked + ", balance=" + balance + ", admin=" + admin + ", employee="
				+ employee + "]";
	}
	
	
}
