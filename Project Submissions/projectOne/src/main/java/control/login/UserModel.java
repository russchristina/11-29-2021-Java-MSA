package control.login;

import java.util.Objects;

public class UserModel {
	
	private String username;
	private boolean isManager;
	
	public UserModel() {
		super();
		
	}
	public UserModel(String username, boolean isManager) {
		super();
		this.username = username;
		this.isManager = isManager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	@Override
	public int hashCode() {
		return Objects.hash(isManager, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		return isManager == other.isManager && Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "UserModel [username=" + username + ", isManager=" + isManager + "]";
	}
	
	

}
