package com.revature.service.handleRequest;

import com.revature.repository.DAOClasses.RequestTypeDao;
import com.revature.repository.DTO.RequestTypeEntity;
import org.checkerframework.checker.units.qual.A;
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
class RequestTypeServiceTest {

    @Mock
    private RequestTypeDao mockRequestDao;

    private RequestTypeService requestTypeService;

    private String requestTypeName;
    private int requestId;

    private RequestTypeEntity storedRequestTypeEntity;
    private List<RequestTypeEntity> requestTypes;

    @BeforeAll
    void setUp() {

        MockitoAnnotations.openMocks(this);

        requestTypeName = "Travel";
        requestId = 1;
        storedRequestTypeEntity = new RequestTypeEntity(requestId, requestTypeName);

        requestTypes = new ArrayList<>();
        requestTypes.add(storedRequestTypeEntity);

        Mockito.when(mockRequestDao.getRequestTypeWithString(new RequestTypeEntity(0, requestTypeName))).thenReturn(storedRequestTypeEntity);
        Mockito.when(mockRequestDao.getRequestTypeEntityWithId(new RequestTypeEntity(requestId, ""))).thenReturn(storedRequestTypeEntity);
        Mockito.when(mockRequestDao.getRequestTypes()).thenReturn(requestTypes);
        requestTypeService = new RequestTypeService(mockRequestDao);
    }

    @Test
    void getRequestTypeWithIdTest() {
        assertEquals(new RequestTypeEntity(1, "Travel"), requestTypeService.getRequestTypeWithId(1));
    }

    @Test
    void getRequestTypeWithIdInvalidIdNullTest() {
        assertNull(requestTypeService.getRequestTypeWithId(-1));
    }

    @Test
    void getRequestWithStringTest() {
        assertEquals(new RequestTypeEntity(1, "Travel"), requestTypeService.getRequestWithString("Travel"));
    }

    @Test
    void getRequestWithStringIncorrectStringNullTest() {
        assertNull(requestTypeService.getRequestWithString("HELESLFD"));
    }

    @Test
    void getAllRequestTypesTest(){
        assertNotNull(requestTypeService.getAllRequestTypes());
    }

}