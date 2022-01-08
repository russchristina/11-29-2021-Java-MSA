package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.LoginInfoEntity;

import java.sql.SQLException;

public interface LoginInfoInterface {

    /*
    Create
    Read
    Update
    Delete
     */

    //Create

    LoginInfoEntity insertLoginInfo(String username, String password, int employeeId) throws SQLException;

    //Read

    LoginInfoEntity getLoginInfo(String username, String password) throws SQLException;

    //Update

    LoginInfoEntity updateUsername(String newUsername, int employeeId) throws SQLException;

    LoginInfoEntity updatePassword(String newPassword, int employeeId) throws SQLException;

    //Delete

    LoginInfoEntity deleteLoginInfo(String username, int employeeId) throws SQLException;

}