package models;

import java.io.Serializable;

public class Account implements Serializable{
	private int emp_id;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private String address;
	private String city;
	private String state;
	private int zip;
	private String social_number;
	private float balance;
	private boolean manager;
	
	public Account() {
		
	}
	
	public Account(int pKey, String username, String password, String fName, String lName, String address, String city, String state, int zip,
			String social_number, float balance, boolean manager) {
		super();
		this.emp_id = pKey;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.social_number = social_number;
		this.balance = balance;
		this.manager = manager;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getSocial_number() {
		return social_number;
	}

	public void setSocial_number(String social_number) {
		this.social_number = social_number;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + (manager ? 1231 : 1237);
		result = prime * result + emp_id;
		result = prime * result + ((social_number == null) ? 0 : social_number.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + zip;
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
		Account other = (Account) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (manager != other.manager)
			return false;
		if (emp_id != other.emp_id)
			return false;
		if (social_number == null) {
			if (other.social_number != null)
				return false;
		} else if (!social_number.equals(other.social_number))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zip != other.zip)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [pKey=" + emp_id + ", fName=" + fName + ", lName=" + lName + ", address=" + address + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", social_number=" + social_number + ", balance="
				+ balance + ", manager=" + manager + "]";
	}
}
