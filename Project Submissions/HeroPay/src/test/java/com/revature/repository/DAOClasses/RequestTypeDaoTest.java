package com.revature.repository.DAOClasses;

import com.revature.repository.DTO.RequestTypeEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RequestTypeDaoTest {

    private RequestTypeDao requestTypeDao;
    private RequestTypeEntity retrievedEntity;
    private int requestTypeId;
    private String requestTypeString;

    @BeforeAll
    void setup(){
        requestTypeDao = new RequestTypeDao();
        requestTypeId = 1;
        requestTypeString = "Travel";
        retrievedEntity = new RequestTypeEntity(requestTypeId, requestTypeString);
    }

    @Test
    void getRequestTypeEntityWithIdTest() {
        assertEquals(retrievedEntity, requestTypeDao.getRequestTypeEntityWithId(new RequestTypeEntity(requestTypeId, "")));
    }

    @Test
    void getRequestTypeEntityWithInvalidIdTest() {
        assertNull(requestTypeDao.getRequestTypeEntityWithId(new RequestTypeEntity(99, "")));
    }

    @Test
    void getRequestTypeEntityWithIncorrectResultTypeIdTest() {
        assertEquals(retrievedEntity, requestTypeDao.getRequestTypeEntityWithId(new RequestTypeEntity(requestTypeId, "WrongOne")));
    }

    @Test
    void getRequestTypeEntityWithNullRequestTypeStringIdTest() {
        assertEquals(retrievedEntity, requestTypeDao.getRequestTypeEntityWithId(new RequestTypeEntity(requestTypeId, null)));
    }

    @Test
    void getRequestTypeWithStringTest() {
        assertEquals(retrievedEntity, requestTypeDao.getRequestTypeWithString(new RequestTypeEntity(0, requestTypeString)));
    }

    @Test
    void getRequestTypeWithStringInvalidIdTest() {
        assertEquals(retrievedEntity, requestTypeDao.getRequestTypeWithString(new RequestTypeEntity(-3, requestTypeString)));
    }

    @Test
    void getRequestTypeWithStringNullIdTest() {
        assertEquals(retrievedEntity, requestTypeDao.getRequestTypeWithString(new RequestTypeEntity(0, requestTypeString)));
    }

    @Test
    void getRequestTypeWithInvalidStringTest() {
        assertNull(requestTypeDao.getRequestTypeWithString(new RequestTypeEntity(0, "WrongOne")));
    }

    @Test
    void getRequestTypesTest() {
        assertNotNull(requestTypeDao.getRequestTypes());
    }
}