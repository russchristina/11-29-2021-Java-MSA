package com.revature.inter;

import java.sql.SQLException;

import com.revature.models.Associate;
import com.revature.repo.LoginUserInfo;

public interface LoginInterface {
	
	LoginUserInfo insertLoginUserInfo(String username, String password, int employeeid) throws SQLException;
	
	 Associate retreiveAssociateLogin(String username, String password) throws SQLException;

	    LoginUserInfo retreiveLoginUserInfo(String username, String password) throws SQLException;
	    
	    LoginUserInfo updateUsername(String newUsername, int employeeId) throws SQLException;

	    LoginUserInfo updatePassword(String newPassword, int employeeId) throws SQLException;
	    
	    LoginUserInfo deleteLoginInfo(String username, int employeeId) throws SQLException;

	    

}
