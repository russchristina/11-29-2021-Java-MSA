package com.revature.service.handleRequest;

import com.revature.presentation.model.requests.NewRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DAOClasses.PendingRequestDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.service.serviceExceptions.EmployeeIdException;
import com.revature.service.serviceExceptions.RequestMessageShortException;
import com.revature.service.serviceExceptions.RequestTypeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PendingRequestServiceTest {

    @Mock
    private PendingRequestDao mockDao;
    @Mock
    private RequestTypeService mockRTS;

    private PendingRequestService pendingRequestService;

    private PendingRequestEntity mockStoredPendingRequest;
    private EmployeeAccountEntity returnedEmployeeAccount;
    private EmployeeAccountEntity convertedEmployeeAccount;
    private RequestTypeEntity mockRequestType;


    private NewRequest inputNewRequest;

    private PendingRequestEntity inputPendingRequestEntity;
    private PendingRequest mockConvertedPendingRequest;

    private PendingRequestEntity updatedPendingRequestByStatus;

    private List<PendingRequestEntity> pendingRequestEntitiesList;
    private List<PendingRequest> pendingRequests;


    private int storedRequestId;
    private String message;
    private BigDecimal amount;
    private Date dateSubmitted;

    @BeforeAll
    void setUp() {

        MockitoAnnotations.openMocks(this);
        storedRequestId = 1;
        message = "TESTasdfa";
        amount = new BigDecimal("10.02");
        dateSubmitted = Date.valueOf(LocalDate.now());

        mockRequestType = new RequestTypeEntity(1, "Travel");

        returnedEmployeeAccount = new EmployeeAccountEntity(1, "Name", "Name", new EmployeeRoleEntity(1, "Knight"));

        convertedEmployeeAccount = new EmployeeAccountEntity(1, "", "", new EmployeeRoleEntity(0, ""));
        mockStoredPendingRequest = new PendingRequestEntity(
                storedRequestId, returnedEmployeeAccount,  mockRequestType, message, amount, dateSubmitted, false, false
        );

        inputNewRequest = new NewRequest(1, "Travel", message, amount);
        inputPendingRequestEntity = new PendingRequestEntity(
                0,
                convertedEmployeeAccount,
                mockRequestType,
                message,
                amount,
                dateSubmitted,
                false,
                false);

        mockConvertedPendingRequest = new PendingRequest(
                mockStoredPendingRequest.getId(),
                mockStoredPendingRequest.getEmployeeAccount().getId(),
                mockStoredPendingRequest.getRequestType().getRequestType(),
                mockStoredPendingRequest.getRequestMessage(),
                mockStoredPendingRequest.getAmount(),
                mockStoredPendingRequest.getDateSubmission().toLocalDate(),
                mockStoredPendingRequest.isStatus(),
                mockStoredPendingRequest.isFileUploadCheck());

        pendingRequestEntitiesList = new ArrayList<>();
        pendingRequests = new ArrayList<>();
        pendingRequestEntitiesList.add(mockStoredPendingRequest);
        pendingRequests.add(mockConvertedPendingRequest);

        updatedPendingRequestByStatus = new PendingRequestEntity(
                mockStoredPendingRequest.getId(),
                mockStoredPendingRequest.getEmployeeAccount(),
                mockStoredPendingRequest.getRequestType(),
                mockStoredPendingRequest.getRequestMessage(),
                mockStoredPendingRequest.getAmount(),
                mockStoredPendingRequest.getDateSubmission(),
                mockStoredPendingRequest.isStatus(),
                mockStoredPendingRequest.isFileUploadCheck());

        Mockito.when(mockDao.getPendingRequestByRequestId(storedRequestId)).thenReturn(mockStoredPendingRequest);
        Mockito.when(mockDao.insertPendingRequest(inputPendingRequestEntity)).thenReturn(storedRequestId);
        Mockito.when(mockDao.getAllPendingRequests()).thenReturn(pendingRequestEntitiesList);
        Mockito.when(mockDao.getEmployeesPendingRequestList(new EmployeeAccountEntity(1, "", "", new EmployeeRoleEntity()))).thenReturn(pendingRequestEntitiesList);

        pendingRequestService = new PendingRequestService(mockDao);

    }

    @Test
    void storePendingRequestTest() {
        assertEquals(mockStoredPendingRequest, pendingRequestService.storePendingRequest(inputNewRequest, false));
    }

    @Test
    void storePendingRequestInvalidNewRequestEmployeeIdTest() {
        assertThrows(EmployeeIdException.class, () -> pendingRequestService.storePendingRequest(new NewRequest(-1, "Travel", "message", new BigDecimal("0231.23")), false));
    }

    @Test
    void storePendingRequestInvalidNewRequestMessageTest() {
        assertThrows(RequestMessageShortException.class, () -> pendingRequestService.storePendingRequest(new NewRequest(1, "Travel", "a", new BigDecimal("0231.23")), false));
    }

    @Test
    void storePendingRequestInvalidNewRequestTypeTest() {
        assertThrows(RequestTypeException.class, () -> pendingRequestService.storePendingRequest(new NewRequest(1, "a", "aasdfaw", new BigDecimal("0231.23")), false));
    }

    @Test
    void getAllEmployeePendingRequestTest() {

        assertEquals(returnedEmployeeAccount.getId(), pendingRequestService.getAllEmployeePendingRequest(1).get(0).getId());
    }

    @Test
    void getAllEmployeePendingRequestInvalidEmployeeIdTest() {
        assertEquals(0, pendingRequestService.getAllEmployeePendingRequest(-1).size());
    }

    @Test
    void convertPendingRequestEntity() {
        assertEquals(mockConvertedPendingRequest, pendingRequestService.convertPendingRequestEntity(mockStoredPendingRequest));
    }

    @Test
    void getAllPendingRequests() {
        assertNotNull(pendingRequestService.getAllPendingRequests());
    }
}