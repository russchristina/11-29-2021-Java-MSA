package projectzero;

public class VirtualDollarsUser {

	// Fields
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String dateOfBirth;
	private String email;
	private String address;
	private VirtualDollarsAccount account;
	
	// Constructor
	public VirtualDollarsUser(String username, String firstName, String lastName, String password, String dateOfBirth,
			String email, String address, VirtualDollarsAccount account) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.address = address;
		this.account = account;
	} // End constructor
	
	// Constructor
	public VirtualDollarsUser(String username) {
		this.username = username;
	} // End constructor

	// Getters/setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public VirtualDollarsAccount getAccount() {
		return account;
	}
	
	public void setAccount(VirtualDollarsAccount account) {
		this.account = account;
	}
	
	@Override
	public String toString() {
		return "VirtualDollarsUser [ Username = " + username + ", First Name = " + firstName + ", Last Name = " + lastName
				+ ", Password = " + password + ", Date of Birth = " + dateOfBirth + ", Email = " + email + ", Address = "
				+ address + ", " + account + " ]";
	} // End toString
} // End class
