package com.revature.service.handleLogin;

import com.revature.presentation.model.loginRequests.LoginInput;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DTO.LoginInfoEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginServiceTest {

    @Mock
    private LoginInfoDao loginInfoDao;


    private LoginService loginService;
    LoginInfoEntity databaseMockLoginInfo;

    @BeforeAll
    void setUp(){

        String mockUsernameInput = "username";
        String mockPasswordInput = "password";

        databaseMockLoginInfo = new LoginInfoEntity(1, mockUsernameInput, mockPasswordInput, 1);



        MockitoAnnotations.openMocks(this);
        try {
            Mockito.when(loginInfoDao.getLoginInfo(mockUsernameInput, mockPasswordInput)).thenReturn(new LoginInfoEntity(1, mockUsernameInput, mockPasswordInput, 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loginService = new LoginService(loginInfoDao);
    }

    @Test
    void validateLoginTest() {
        assertEquals(databaseMockLoginInfo, loginService.validateLogin(new LoginInput("username", "password")));
    }

    @Test
    void validateLoginInvalidUsernameTest() {
        assertNull(loginService.validateLogin(new LoginInput("failuser", "password")));
    }

    @Test
    void validateLoginInvalidPasswordTest() {
        assertNull(loginService.validateLogin(new LoginInput("username", "failpass")));
    }

    @Test
    void validateLoginEmptyInputTest() {
        assertNull(loginService.validateLogin(new LoginInput("", "")));
    }

}