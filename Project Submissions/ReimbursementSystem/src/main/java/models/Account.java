package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="account")
public class Account implements Serializable{
	
	@Id
	@Column(name="emp_id")
	@GeneratedValue(generator="emp_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, sequenceName = "emp_id_seq", name = "emp_id_seq")
	private int emp_id;
	@Column(name="username")
	private String username;
	@Column(name="pass")
	private String pass;
	@Column(name="first_name")
	private String first_name;
	@Column(name="last_name")
	private String last_name;
	@Column(name="street_address")
	private String street_address;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="zip")
	private int zip;
	@Column(name="social_number")
	private String social_number;
	@Column(name="balance")
	private float balance;
	@Column(name="manager")
	private boolean manager;
	
	public Account() {
		
	}
	
	public Account(String username, String pass, String first_name, String last_name, String street_address,
			String city, String state, int zip, String social_number, float balance, boolean manager) {
		super();
		this.username = username;
		this.pass = pass;
		this.first_name = first_name;
		this.last_name = last_name;
		this.street_address = street_address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.social_number = social_number;
		this.balance = balance;
		this.manager = manager;
	}

	public Account(int pKey, String username, String password, String fName, String lName, String address, String city, String state, int zip,
			String social_number, float balance, boolean manager) {
		super();
		this.emp_id = pKey;
		this.username = username;
		this.pass = password;
		this.first_name = fName;
		this.last_name = lName;
		this.street_address = address;
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
		return pass;
	}

	public void setPassword(String password) {
		this.pass = password;
	}

	public String getfName() {
		return first_name;
	}

	public void setfName(String fName) {
		this.first_name = fName;
	}

	public String getlName() {
		return last_name;
	}

	public void setlName(String lName) {
		this.last_name = lName;
	}

	public String getAddress() {
		return street_address;
	}

	public void setAddress(String address) {
		this.street_address = address;
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
		result = prime * result + ((street_address == null) ? 0 : street_address.hashCode());
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
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
		if (street_address == null) {
			if (other.street_address != null)
				return false;
		} else if (!street_address.equals(other.street_address))
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
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
		return "Employee [pKey=" + emp_id + ", fName=" + first_name + ", lName=" + last_name + ", address=" + street_address + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", social_number=" + social_number + ", balance="
				+ balance + ", manager=" + manager + "]";
	}
}
