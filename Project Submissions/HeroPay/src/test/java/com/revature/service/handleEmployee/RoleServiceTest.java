package com.revature.service.handleEmployee;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.repository.DAOClasses.EmployeeRoleDao;
import com.revature.repository.DTO.EmployeeRoleEntity;
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
class RoleServiceTest {

    @Mock
    private EmployeeRoleDao mockERDao;

    private RoleService roleService;
    private int roleId;

    private EmployeeRoleEntity mockEmployeeRoleEntity;
    private List<EmployeeRoleEntity> mockRoleList;

    @BeforeAll
    void setUp() {

        MockitoAnnotations.openMocks(this);
        roleId = 4;
        mockEmployeeRoleEntity = new EmployeeRoleEntity(roleId, "Manager");

        mockRoleList = new ArrayList<>();
        mockRoleList.add(mockEmployeeRoleEntity);

        Mockito.when(mockERDao.getEmployeeRoleById(new EmployeeRoleEntity(roleId, ""))).thenReturn(mockEmployeeRoleEntity);
        Mockito.when(mockERDao.getAllEmployeeRoles()).thenReturn(mockRoleList);

        roleService = new RoleService(mockERDao);
    }

    @Test
    void getEmployeeRoleTest() {
        assertEquals(new EmployeeRoleEntity(roleId, "Manager"), roleService.getEmployeeRole(roleId));
    }

    @Test
    void getEmployeeRoleInvalidIdNullTest() {
        assertNull(roleService.getEmployeeRole(-1));
    }

    @Test
    void getAllEmployeeRoles() {
        assertNotNull(roleService.getAllEmployeeRoles());
    }
}