package com.revature.service.handleLogin;

import com.revature.presentation.model.loginRequests.LoginInput;
import com.revature.repository.DAOClasses.LoginInfoDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.DTO.LoginInfoEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginServiceTest {

    @Mock
    private LoginInfoDao loginInfoDao;

    private LoginService loginService;
    private LoginInfoEntity mockLoginInfoInput;
    private LoginInfoEntity mockLoginInfoReturn;
    private LoginInput userInput;

    @BeforeAll
    void setUp(){
        MockitoAnnotations.openMocks(this);
        userInput = new LoginInput();
        loginService = new LoginService(loginInfoDao);
        mockLoginInfoInput = new LoginInfoEntity(0, "user", "pass", new EmployeeAccountEntity());
        mockLoginInfoReturn = new LoginInfoEntity(1, "user", "pass", new EmployeeAccountEntity(1, "Name", "Sanme", new EmployeeRoleEntity(1, "Test")));
        Mockito.when(loginInfoDao.getLoginInfo(mockLoginInfoInput)).thenReturn(mockLoginInfoReturn);
    }

    @Test
    void validateLoginTest() {
        userInput.setUsername("user");
        userInput.setPassword("pass");
        assertEquals(mockLoginInfoReturn, loginService.validateLogin(userInput));
    }

    @Test
    void validateLoginInvalidUsernameNullTest() {
        userInput.setUsername("userasdfa");
        userInput.setPassword("pass");
        assertNull(loginService.validateLogin(userInput));
    }

    @Test
    void validateLoginInvalidPasswordNullTest() {
        userInput.setUsername("user");
        userInput.setPassword("asdf");
        assertNull(loginService.validateLogin(userInput));
    }
}