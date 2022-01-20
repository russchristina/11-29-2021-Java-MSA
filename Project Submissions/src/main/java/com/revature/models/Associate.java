package com.revature.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "associate")



public class Associate {
	
	@Id
	@Column(name = "AssociateName")
	private String fullName;
	@Column(name = "AssociatePasswprd")
	private String password;
	@Column(name = "role")
	private String manager;
	
	
	
	public Associate(String fullName, String manager, String password) {
	
		this.manager = manager;
		this.fullName = fullName;
		this.password = password;
		
	}
	
	
	
	public String retreivefullName() {
		return fullName;
		
	}
	
	public void establishID(String fullName) {
		this.fullName = fullName;
	}
	
	public String retreiveRole() {
		return manager;
	}
	
	public void establishRole(String manager) {
		this.manager = manager;
		
	}
	
	public String retreivePassword() {
		return password;
	}
	
	public void establishPassword(String password) {
		this.password = password;
		
	}
	

    @Override
    public int hashCode() {
        return Objects.hash(password, fullName, manager);
    }

    @Override
    public String toString() {
        return "{\"Associate\":{"
                
                + ", \"fullName\":\"" + fullName + "\""
                + ", \"manager\":\"" + manager + "\""
                + ", \"password\":\"" + password + "\""
                + "}}";
	}
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Associate)) return false;
        Associate associate = (Associate) o;
        return manager == associate.manager && Objects.equals(fullName, associate.fullName)
        		&& Objects.equals(password, associate.password);
    }

	
}
