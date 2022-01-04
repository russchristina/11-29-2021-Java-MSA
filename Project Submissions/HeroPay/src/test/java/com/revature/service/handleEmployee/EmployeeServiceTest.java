package com.revature.service.handleEmployee;

import com.revature.presentation.model.Employee;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceTest {

    private EmployeeService employeeService;

    private EmployeeAccountEntity mockedEmployeeAccount;
    private EmployeeRoleEntity mockedEmployeeRole;
    private Employee mockedEmployee;

    private String firstName;
    private String lastName;
    private String roleName;
    private int employeeId;
    private int roleId;

    @Mock
    private EmployeeAccountDao mockEmployeeAccountDao;


    @BeforeAll
    void setUp(){
        MockitoAnnotations.openMocks(this);
        employeeId = 1;
        roleId = 1;
        firstName = "firstName";
        lastName = "lastName";
        roleName = "roleName";
        mockedEmployeeAccount = new EmployeeAccountEntity(employeeId, firstName, lastName, roleId);
        mockedEmployeeRole = new EmployeeRoleEntity(employeeId, roleName);
        mockedEmployee = new Employee(employeeId, firstName, lastName, roleName);

        try {
            Mockito.when(mockEmployeeAccountDao.getEmployeeAccount(employeeId)).thenReturn(mockedEmployeeAccount);
            Mockito.when(mockEmployeeAccountDao.getEmployeeRoleById(roleId)).thenReturn(mockedEmployeeRole);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        employeeService = new EmployeeService(mockEmployeeAccountDao);
    }

    @Test
    void getEmployeeById() {
        assertEquals(new EmployeeAccountEntity(employeeId, firstName, lastName, roleId), employeeService.getEmployeeAccountById(employeeId));
    }

    @Test
    void getEmployeeByIncorrectIdNullTest() {
        assertNull(employeeService.getEmployeeAccountById(0));
    }

    @Test
    void getEmployeeRole() {
        assertEquals(new EmployeeRoleEntity(roleId, roleName), employeeService.getEmployeeRole(roleId));
    }

    @Test
    void getEmployeeRoleNullTest() {
        assertNull(employeeService.getEmployeeRole(0));
    }

    @Test
    void convertEmployeeEntityToEmployee() {
        assertEquals(new Employee(employeeId, firstName, lastName, roleName), employeeService.convertEmployeeEntityToEmployee(mockedEmployeeAccount));
    }

}