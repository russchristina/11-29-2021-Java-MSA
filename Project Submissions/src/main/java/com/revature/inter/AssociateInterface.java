package com.revature.inter;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.revature.models.Associate;
import com.revature.repo.AssociateRole;

public interface AssociateInterface {
	
	Associate insertAssociateAccount(String fullName, int id) throws SQLException;

	    AssociateRole insertAssociateRole(String roleName) throws SQLException;
	    
	    Associate retreiveAssociateAccount(int employeeId) throws SQLException;

	    List<Associate> retreiveAssociateAccounts() throws SQLException;

	    List<Associate> retreiveAssociateAccountsByRole(int roleId) throws SQLException;

	    AssociateRole retreiveAssociateRoleById(int id) throws SQLException;

	    AssociateRole retreiveAssociateRoleByName(String roleName) throws SQLException;

	    Map<Integer, String> retreiveAssociateRoleMap() throws SQLException;
	    
	    AssociateRole updateEmployeeRole(int employeeId, int role_id) throws SQLException;

	    AssociateRole updateRole(int role_id, String roleName) throws SQLException;

}
