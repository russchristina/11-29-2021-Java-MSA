package com.revature.service.handleEmployee;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.repository.DAOClasses.EmployeeAccountDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.utility.JWTHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceTest {

    private EmployeeService employeeService;


    private EmployeeAccountEntity mockedEmployeeAccountInput;
    private EmployeeAccountEntity mockedManagerAccountInput;
    private EmployeeAccountEntity mockedManagerAccountStored;
    private EmployeeAccountEntity mockedEmployeeAccountStored;
    private EmployeeRoleEntity mockedEmployeeRole;
    private EmployeeRoleEntity mockedManagerRole;
    private Employee mockEmployeeModel;
    private Employee mockManagerModel;


    private int employeeId;
    private int managerId;

    private List<EmployeeAccountEntity> employeeAccountList;
    private List<EmployeeAccountEntity> employeeAccountListByRole;

    @Mock
    private EmployeeAccountDao mockEmployeeAccountDao;


    @BeforeAll
    void setUp(){
        MockitoAnnotations.openMocks(this);
        employeeId = 1;
        managerId = 2;
        String firstName = "firstName";
        String lastName = "lastName";

        mockedEmployeeAccountInput = new EmployeeAccountEntity(employeeId, "", "", new EmployeeRoleEntity());
        mockedManagerAccountInput = new EmployeeAccountEntity(managerId, "", "", new EmployeeRoleEntity());

        mockedManagerRole = new EmployeeRoleEntity(4, "Manager");
        mockedEmployeeRole = new EmployeeRoleEntity(1, "Employee");


        mockedEmployeeAccountStored = new EmployeeAccountEntity(employeeId, firstName, lastName, mockedEmployeeRole);
        mockedManagerAccountStored = new EmployeeAccountEntity(managerId, firstName, lastName, mockedManagerRole);


        mockEmployeeModel = new Employee(
                mockedEmployeeAccountStored.getId(),
                mockedEmployeeAccountStored.getFirstName(),
                mockedEmployeeAccountStored.getLastName(),
                mockedEmployeeAccountStored.getEmployeeRole().getRoleName(),
                false
                );

        mockManagerModel = new Employee(
                    mockedManagerAccountStored.getId(),
                    mockedManagerAccountStored.getFirstName(),
                    mockedManagerAccountStored.getLastName(),
                    mockedManagerAccountStored.getEmployeeRole().getRoleName(),
                    true
        );

        employeeAccountList = new ArrayList<>();
        employeeAccountList.add(mockedEmployeeAccountStored);
        employeeAccountList.add(mockedManagerAccountStored);
        employeeAccountListByRole = new ArrayList<>();
        employeeAccountListByRole.add(mockedManagerAccountStored);

        Mockito.when(mockEmployeeAccountDao.getEmployeeAccount(mockedEmployeeAccountInput)).thenReturn(mockedEmployeeAccountStored);
        Mockito.when(mockEmployeeAccountDao.getEmployeeAccount(mockedManagerAccountInput)).thenReturn(mockedManagerAccountStored);
        Mockito.when(mockEmployeeAccountDao.getAllEmployeeAccountList()).thenReturn(employeeAccountList);
        Mockito.when(mockEmployeeAccountDao.getEmployeeAccountsByRoleId(new EmployeeRoleEntity(mockedManagerRole.getId(), ""))).thenReturn(employeeAccountListByRole);

        employeeService = new EmployeeService(mockEmployeeAccountDao);

    }


    @Test
    void getEmployeeAccountByIdTest() {
        assertEquals(mockedEmployeeAccountStored, employeeService.getEmployeeAccountById(employeeId));
    }

    @Test
    void getEmployeeAccountByIdManagerTest() {
        assertEquals(mockedManagerAccountStored, employeeService.getEmployeeAccountById(managerId));
    }

    @Test
    void getEmployeeAccountByIdInvalidIdNullTest() {
        assertNull(employeeService.getEmployeeAccountById(-1));
    }

    @Test
    void convertEmployeeEntityToEmployee() {
        assertEquals(mockEmployeeModel, employeeService.convertEmployeeEntityToEmployee(mockedEmployeeAccountStored));
    }

    @Test
    void convertManagerEntityToManagerEmployeeTest() {
        assertEquals(mockManagerModel, employeeService.convertEmployeeEntityToEmployee(mockedManagerAccountStored));
    }

    @Test
    void getEmployeeModelWithEmployeeIdTest() {
        assertEquals(mockEmployeeModel, employeeService.getEmployeeModelWithEmployeeId(employeeId));
    }

    @Test
    void getEmployeeModelWithManagerIdTest() {
        assertEquals(mockManagerModel, employeeService.getEmployeeModelWithEmployeeId(managerId));
    }

    @Test
    void getEmployeeModelWithEmployeeIdInvalidIdNullTest() {
        assertNull(employeeService.getEmployeeModelWithEmployeeId(-1));
    }

    @Test
    void getAllEmployeesAccountEntitiesTest() {
        assertNotNull(employeeService.getAllEmployeesAccountEntities());
    }

    @Test
    void getAllEmployeesByRoleTest() {
        assertEquals(mockedManagerRole, employeeService.getAllEmployeesByRole(mockedManagerRole.getId()).get(0).getEmployeeRole());
    }

    @Test
    void getAllEmployeesByRoleRoleCheckTest() {
        assertNotEquals(mockedEmployeeRole, employeeService.getAllEmployeesByRole(mockedManagerRole.getId()).get(0).getEmployeeRole());
    }
}