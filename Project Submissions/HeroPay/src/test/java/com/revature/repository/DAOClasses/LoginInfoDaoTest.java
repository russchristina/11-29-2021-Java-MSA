package com.revature.repository.DAOClasses;

import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.DTO.LoginInfoEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginInfoDaoTest {

    private LoginInfoDao lDao;
    private LoginInfoEntity returnedLoginInfo;
    private EmployeeAccountEntity returnedEmployeeAccount;
    private EmployeeRoleEntity returnedRoleEntity;
    private int loginInfoId;
    private String username;
    private String password;

    @BeforeAll
    void setUp(){
        lDao = new LoginInfoDao();
        loginInfoId = 7;
        username = "mage1";
        password = "pass7";
        returnedRoleEntity = new EmployeeRoleEntity(3, "Mage");
        returnedEmployeeAccount = new EmployeeAccountEntity(7, "Iphazor", "Thitarum", returnedRoleEntity);
        returnedLoginInfo = new LoginInfoEntity(loginInfoId, username, password, returnedEmployeeAccount);

    }

    @Test
    void getLoginInfoTest() {
        assertEquals(returnedLoginInfo, lDao.getLoginInfo(new LoginInfoEntity(0, username, password, new EmployeeAccountEntity())));
    }

    @Test
    void getLoginInfoInvalidUsernameNullTest() {
        assertNull(lDao.getLoginInfo(new LoginInfoEntity(0, "wrong", password, new EmployeeAccountEntity())));
    }

    @Test
    void getLoginInfoInvalidPasswordNullTest() {
        assertNull(lDao.getLoginInfo(new LoginInfoEntity(0, username, "wrong", new EmployeeAccountEntity())));
    }

    @Test
    void getLoginInfoNullEmployeeAccountTest() {
        assertNull(lDao.getLoginInfo(new LoginInfoEntity(0, "wrong", password, null)));
    }
}