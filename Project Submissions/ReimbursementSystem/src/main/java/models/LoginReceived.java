package models;

import java.util.Objects;

public class LoginReceived {
	private String user;
	private String pass;
	
	public LoginReceived(){
		super();
	}
	
	public LoginReceived(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	protected void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	protected void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pass, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginReceived other = (LoginReceived) obj;
		return Objects.equals(pass, other.pass) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "LoginRecieved [user=" + user + ", pass=" + pass + "]";
	}
	
	
	
}
