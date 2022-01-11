package com.revature.presentation.manageLogin;

import com.revature.presentation.model.loginRequests.LoginInput;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.LoginInfoEntity;
import com.revature.service.handleLogin.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginControllerTest {

    @Mock
    private LoginService loginService;


    @BeforeAll
    public void setUp(){
        String username = "user";
        String password = "pass";

        int id = 1;
        int employeeId = 1;
        LoginInput mockLogin = new LoginInput(username, password);
        LoginInfoEntity mockLoginInfo = new LoginInfoEntity(id, username, password, new EmployeeAccountEntity());

        MockitoAnnotations.openMocks(this);
        Mockito.when(loginService.validateLogin(mockLogin)).thenReturn(mockLoginInfo);
    }

    @Test
    void authenticateLoginTest() {
        String username = "user";
        String password = "pass";

        Assertions.assertEquals(
                new LoginInfoEntity(1, username, password, new EmployeeAccountEntity()),
                loginService.validateLogin(new LoginInput(username, password)));
    }

    @Test
    void authenticateLoginEmptyUsernameTest(){
        String username = "";
        String password = "pass";

        Assertions.assertNotEquals(
                new LoginInfoEntity(1, username, password, new EmployeeAccountEntity()),
                loginService.validateLogin(new LoginInput(username, password)));
    }

    @Test
    void authenticateLoginEmptyPasswordTest(){
        String username = "user";
        String password = "";

        Assertions.assertNotEquals(
                new LoginInfoEntity(1, username, password, new EmployeeAccountEntity()),
                loginService.validateLogin(new LoginInput(username, password)));
    }

    @Test
    void authenticateLoginCaseIncorrectTest(){

        String username = "USER";
        String password = "PASS";

        Assertions.assertNotEquals(
                new LoginInfoEntity(1, username, password, new EmployeeAccountEntity()),
                loginService.validateLogin(new LoginInput(username, password)));
    }

    @Test
    void authenticateLoginCaseWrongUsernameTest(){

        String username = "alam";
        String password = "pass";

        Assertions.assertNotEquals(
                new LoginInfoEntity(1, username, password, new EmployeeAccountEntity()),
                loginService.validateLogin(new LoginInput(username, password)));
    }

    @Test
    void authenticateLoginCaseWrongPasswordTest(){

        String username = "user";
        String password = "fail";

        Assertions.assertNotEquals(
                new LoginInfoEntity(1, username, password, new EmployeeAccountEntity()),
                loginService.validateLogin(new LoginInput(username, password)));
    }


}