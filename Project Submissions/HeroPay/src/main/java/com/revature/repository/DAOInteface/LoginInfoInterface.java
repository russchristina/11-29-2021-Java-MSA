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

    Integer insertLoginInfo(LoginInfoEntity loginInfoEntity);

    //Read

    LoginInfoEntity getLoginInfo(String username);

    //Update

    Integer updateUsername(LoginInfoEntity loginInfoEntity);

    Integer updatePassword(LoginInfoEntity loginInfoEntity);

    //Delete

    void deleteLoginInfo(LoginInfoEntity loginInfoEntity);

}
