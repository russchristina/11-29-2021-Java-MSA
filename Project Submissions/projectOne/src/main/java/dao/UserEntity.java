package dao;

import java.util.Objects;

public class UserEntity {
	
	private int id;
	private String username;
	private String password;
	private boolean isManager;
	public UserEntity(int id, String username, String password, boolean isManager) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isManager = isManager;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, isManager, password, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return id == other.id && isManager == other.isManager && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", isManager=" + isManager
				+ "]";
	}
	
	

}
