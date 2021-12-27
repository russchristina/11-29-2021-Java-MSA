package project0.models;

public class User{
	
	private String userName;
	private String password;
	private String fullName;
	private int birthMonth;
	private int birthDay;
	private String address;
	private String city;
	private String state;
	private String phone;
	
	public User() {
		super();
	}

	public User(String userName, String password, String fullName, int birthMonth, int birthDay, String address,
			String city, String state, String phone) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.birthMonth = birthMonth;
		this.birthDay = birthDay;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		 this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		 this.fullName = fullName;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		 this.birthMonth = birthMonth;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		 this.birthDay = birthDay;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		 this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + birthDay;
		result = prime * result + birthMonth;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthDay != other.birthDay)
			return false;
		if (birthMonth != other.birthMonth)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", fullName=" + fullName + ", birthMonth="
				+ birthMonth + ", birthDay=" + birthDay + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", phone=" + phone + "]";
	}
	
	
	
}



		
		

	
		

	

	
	
	
	
	
	
	
	
	
	
	
	
	


	

