package com.revature.repository.DAOClasses;

import com.revature.repository.DTO.EmployeeRoleEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeRoleDaoTest {

    private EmployeeRoleDao edao;
    private EmployeeRoleEntity retrievedEntity;
    private int roleId;
    private String roleName;

    @BeforeAll
    void setup(){
        edao = new EmployeeRoleDao();
        roleId = 1;
        roleName = "Knight";
        retrievedEntity = new EmployeeRoleEntity(roleId, roleName);

    }

    @Test
    void getAllEmployeeRolesTest() {
        assertNotNull(edao.getAllEmployeeRoles());
    }

    @Test
    void getEmployeeRoleByIdTest() {
        assertEquals(retrievedEntity, edao.getEmployeeRoleById(new EmployeeRoleEntity(roleId, "")));
    }

    @Test
    void getEmployeeRoleByIdInvalidIdTest() {
        assertNull(edao.getEmployeeRoleById(new EmployeeRoleEntity(-1, "")));
    }

}