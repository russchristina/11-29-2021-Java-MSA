package models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserLogin {
	
	@Id
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private boolean isManager;
	
	
	
	public UserLogin() {
		super();
	}

	public UserLogin(String username, String password, boolean isManager) {
		super();

		this.username = username;
		this.password = password;
		this.isManager = isManager;
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
		return Objects.hash(isManager, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogin other = (UserLogin) obj;
		return isManager == other.isManager && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "UserEntity [username=" + username + ", password=" + password + ", isManager=" + isManager + "]";
	}
	
	
	
	

}
