package com.revature.service.handleRequest;

import com.revature.presentation.model.PendingRequest;
import com.revature.repository.DAOClasses.PendingRequestDao;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.service.serviceExceptions.EmployeeIdException;
import com.revature.service.serviceExceptions.NegativeAmountException;
import com.revature.service.serviceExceptions.RequestMessageShortException;
import com.revature.service.serviceExceptions.RequestTypeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PendingRequestServiceTest {

    @Mock
    private PendingRequestDao pendingRequestDao;

    private PendingRequestService pendingRequestService;

    private PendingRequest mockNewPendingRequestModel;
    private PendingRequestEntity mockedPendingRequestEntity;

    private RequestTypeEntity mockedRequestTypeEntity;

    private int newPendingRequestId;
    private int storedPendingRequestId;
    private int employeeId;
    private int typeId1;
    private int typeId2;
    private String requestMessage;
    private double amount;
    private LocalDate dateSubmission;
    private Date sqlDate;
    private Map<Integer, String> requestTypeMap;

    private List<PendingRequest> pendingRequestList;
    private List<PendingRequestEntity> storedPendingRequestList;

    private List<PendingRequestEntity> storedPendingRequestByTypeList;
    private List<PendingRequest> pendingRequestByTypeList;

    @BeforeAll
    void setUp() {
        newPendingRequestId = 0;
        storedPendingRequestId = 1;
        employeeId = 1;
        typeId1 = 1;
        typeId2 = 2;
        requestMessage = "hello, my name is Yoshikage Kira";
        amount = 20.01;
        dateSubmission = LocalDate.of(2022, 1, 1);
        sqlDate = Date.valueOf(dateSubmission);

        requestTypeMap = new HashMap<>();
        requestTypeMap.put(1, "cash");
        requestTypeMap.put(2, "books");

        mockedRequestTypeEntity = new RequestTypeEntity(typeId1, requestTypeMap.get(typeId1));

        mockNewPendingRequestModel = new PendingRequest(newPendingRequestId, employeeId, requestTypeMap.get(typeId1), requestMessage, amount, dateSubmission);

        mockedPendingRequestEntity = new PendingRequestEntity(storedPendingRequestId, employeeId, typeId1, requestMessage, amount, sqlDate);

        storedPendingRequestList = new ArrayList<>();
        pendingRequestList = new ArrayList<>();

        storedPendingRequestByTypeList = new ArrayList<>();
        pendingRequestByTypeList = new ArrayList<>();

        storedPendingRequestList.add(new PendingRequestEntity(1, employeeId, typeId1, "Hello Greg", amount, sqlDate));
        storedPendingRequestList.add(new PendingRequestEntity(2, employeeId, typeId2, "Hello sads", amount + 33, Date.valueOf(LocalDate.of(2022, 1, 2))));
        storedPendingRequestList.add(new PendingRequestEntity(3, employeeId, typeId1, "sdfo sads", amount + 23.02, Date.valueOf(LocalDate.of(2010, 1, 2))));
        storedPendingRequestList.add(new PendingRequestEntity(4, employeeId, typeId2, "dff sddfasdfasdads", amount + 11, Date.valueOf(LocalDate.of(2019, 1, 2))));

        pendingRequestList.add(new PendingRequest(1, employeeId, requestTypeMap.get(typeId1), "Hello Greg", amount, sqlDate.toLocalDate()));
        pendingRequestList.add(new PendingRequest(2, employeeId, requestTypeMap.get(typeId2), "Hello sads", amount + 33, LocalDate.of(2022, 1, 2)));
        pendingRequestList.add(new PendingRequest(3, employeeId, requestTypeMap.get(typeId1), "sdfo sads", amount + 23.02, LocalDate.of(2010, 1, 2)));
        pendingRequestList.add(new PendingRequest(4, employeeId, requestTypeMap.get(typeId2), "dff sddfasdfasdads", amount + 11, LocalDate.of(2019, 1, 2)));

        storedPendingRequestByTypeList.add(new PendingRequestEntity(1, employeeId, typeId1, "Hello Greg", amount, sqlDate));
        storedPendingRequestByTypeList.add(new PendingRequestEntity(3, employeeId, typeId1, "sdfo sads", amount + 23.02, Date.valueOf(LocalDate.of(2010, 1, 2))));

        pendingRequestByTypeList.add(new PendingRequest(1, employeeId, requestTypeMap.get(typeId1), "Hello Greg", amount, sqlDate.toLocalDate()));
        pendingRequestByTypeList.add(new PendingRequest(3, employeeId, requestTypeMap.get(typeId1), "sdfo sads", amount + 23.02, LocalDate.of(2010, 1, 2)));

        MockitoAnnotations.openMocks(this);

        try {
            Mockito.when(pendingRequestDao.insertPendingRequest(employeeId, typeId1, requestMessage, amount, sqlDate)).thenReturn(mockedPendingRequestEntity);
            Mockito.when(pendingRequestDao.getRequestTypeWithString(requestTypeMap.get(typeId1))).thenReturn(mockedRequestTypeEntity);
            Mockito.when(pendingRequestDao.getEmployeePendingRequestList(employeeId)).thenReturn(storedPendingRequestList);
            Mockito.when(pendingRequestDao.getRequestTypeMap()).thenReturn(requestTypeMap);
            Mockito.when(pendingRequestDao.deletePendingRequest(storedPendingRequestId)).thenReturn(mockedPendingRequestEntity);
            Mockito.when(pendingRequestDao.getAllPendingRequests()).thenReturn(storedPendingRequestList);
            Mockito.when(pendingRequestDao.getAllPendingRequestsByType(typeId1)).thenReturn(storedPendingRequestByTypeList);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            pendingRequestService = new PendingRequestService(pendingRequestDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void storePendingRequestTest() {
        assertEquals(mockedPendingRequestEntity, pendingRequestService.storePendingRequest(mockNewPendingRequestModel));
    }

    @Test
    void validatePendingRequest() {
        assertTrue(pendingRequestService.validateNewPendingRequest(mockNewPendingRequestModel));
    }

    @Test
    void validatePendingRequestThrowEmployeeIdException(){
        assertThrows(EmployeeIdException.class, () -> pendingRequestService.validateNewPendingRequest(new PendingRequest(0, -1, requestTypeMap.get(typeId1), requestMessage, amount, dateSubmission)));
    }

    @Test
    void validatePendingRequestThrowNegativeAmountException(){
        assertThrows(NegativeAmountException.class, () -> pendingRequestService.validateNewPendingRequest(new PendingRequest(0, 1, requestTypeMap.get(typeId1), requestMessage, -1, dateSubmission)));
    }
    @Test
    void validatePendingRequestThrowRequestMessageShortException(){
        assertThrows(RequestMessageShortException.class, () -> pendingRequestService.validateNewPendingRequest(new PendingRequest(0, 1, requestTypeMap.get(typeId1), "lol", amount, dateSubmission)));
    }

    @Test
    void validatePendingRequestThrowRequestTypeException(){
        assertThrows(RequestTypeException.class, () -> pendingRequestService.validateNewPendingRequest(new PendingRequest(0, 1, "", requestMessage, amount, dateSubmission)));
    }

    @Test
    void getAllEmployeePendingRequest() {
        assertEquals(pendingRequestList.get(1), pendingRequestService.getAllEmployeePendingRequest(employeeId).get(1));
    }

    @Test
    void getAllEmployeePendingRequestInvalidEmployeeIdTest() {
        assertNotEquals(pendingRequestList, pendingRequestService.getAllEmployeePendingRequest(-1));
    }

    @Test
    void deletePendingRequest() {
        assertEquals(mockedPendingRequestEntity, pendingRequestService.deletePendingRequest(storedPendingRequestId));
    }

    @Test
    void deletePendingRequestInvalidRequestId() {
        assertNull(pendingRequestService.deletePendingRequest(-1));
    }


    @Test
    void getAllPendingRequests() {
        assertEquals(pendingRequestList.get(1), pendingRequestService.getAllPendingRequests().get(1));
    }


    @Test
    void getPendingRequestByType() {
        assertEquals(pendingRequestByTypeList, pendingRequestService.getPendingRequestByType(typeId1));
    }

    @Test
    void getPendingRequestByTypeInvalidTypeId() {
        assertNotEquals(pendingRequestByTypeList, pendingRequestService.getPendingRequestByType(-1));
    }

}