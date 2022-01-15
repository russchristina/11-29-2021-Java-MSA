package com.revature.service.handleRequest;

import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DAOClasses.CompletedRequestDao;
import com.revature.repository.DTO.*;
import com.revature.service.serviceExceptions.EmployeeIdException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CompletedRequestServiceTest {

    @Mock
    private CompletedRequestDao mockCompletedRequestDao;

    private CompletedRequestService completedRequestService;




    //Variable
    private int employeeId;
    private int managerId;
    private boolean managerStatus;
    private String managerResponse;
    private LocalDate dateResolved;

    private int storedPendingRequestId;
    private String storedRequestMessage;
    private BigDecimal storedAmount;
    private Date dateSubmission;
    private boolean status;

    //objects
    private EmployeeAccountEntity employeeAccountEntity;
    private EmployeeAccountEntity managerAccountEntity;
    private PendingRequestEntity returnedPendingRequest;
    private CompletedRequest inputCompletedRequestModel;

    private CompletedRequestEntity convertedCompletedRequest;

    private CompletedRequestEntity returnedCompletedRequest;

    private EmployeeAccountEntity returnedManagerAccount;

    private int uniqueId;

    private EmployeeAccountEntity returnedEmployeeAccount;
    private RequestTypeEntity returnedType;

    private CompletedRequest entityConvertedModel;



//    private CompletedRequest newCompleteRequest;
//    private CompletedRequest oldCompleteRequest;
//    private CompletedRequestEntity completedRequestEntity;
//    private PendingRequest pendingRequest;
//
//    private int newRequestId;
//    private int storedRequestId;
//    private int employeeId;
//    private int managerId;
//    private String response;
//    private LocalDate dateResolved;
//    private Date sqlDate;
//
//    private List<CompletedRequest> completedRequestList;
//    private List<CompletedRequestEntity> completedRequestEntityList;
//    private List<CompletedRequestEntity> completedRequestEntityListByManagerId;
//    private List<CompletedRequest> completedRequestListByManagerId;
//    private List<CompletedRequestEntity> statusCompleteRequestList;
//    private List<CompletedRequest> statusRequestList;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
        employeeId = 3;
        managerId = 2;
        managerResponse = "RESPONSE";
        managerStatus = true;
        dateResolved = LocalDate.of(2222, 2, 2);
        storedPendingRequestId = 1;
        uniqueId = 5;

        inputCompletedRequestModel = new CompletedRequest(storedPendingRequestId, employeeId, managerId, managerStatus, managerResponse, dateResolved);

        returnedEmployeeAccount = new EmployeeAccountEntity(employeeId, "Gwyndolyn", "Braveheart", new EmployeeRoleEntity(1, "Knight"));
        returnedType = new RequestTypeEntity(1, "Travel");
        returnedPendingRequest = new PendingRequestEntity(storedPendingRequestId, returnedEmployeeAccount, returnedType, storedRequestMessage, storedAmount, dateSubmission, status, false);
        storedRequestMessage = "Las Vegas baby";
        storedAmount = new BigDecimal("0.05");
        dateSubmission = Date.valueOf(LocalDate.of(2022, 01, 11));
        status = true;

        returnedManagerAccount = new EmployeeAccountEntity(12, "Misha", "Marcus", new EmployeeRoleEntity(4, "Manager"));

        convertedCompletedRequest = new CompletedRequestEntity(
                new PendingRequestEntity(inputCompletedRequestModel.getId(), new EmployeeAccountEntity(), new RequestTypeEntity(), "", BigDecimal.ZERO, Date.valueOf(LocalDate.now()), true, false),
                new EmployeeAccountEntity(inputCompletedRequestModel.getEmployeeId(), "", "", new EmployeeRoleEntity()),
                new EmployeeAccountEntity(inputCompletedRequestModel.getManagerId(), "", "", new EmployeeRoleEntity()),
                inputCompletedRequestModel.isStatus(),
                inputCompletedRequestModel.getResponse(),
                Date.valueOf(inputCompletedRequestModel.getDateResolved()),
                0
        );
        returnedCompletedRequest = new CompletedRequestEntity(
                returnedPendingRequest,
                returnedEmployeeAccount,
                returnedManagerAccount,
                managerStatus,
                managerResponse,
                Date.valueOf(dateResolved),
                uniqueId
        );

        entityConvertedModel = new CompletedRequest(
                returnedCompletedRequest.getPendingRequest().getId(),
                returnedCompletedRequest.getEmployeeAccount().getId(),
                returnedCompletedRequest.getManagerAccount().getId(),
                returnedCompletedRequest.isStatus(),
                returnedCompletedRequest.getResponse(),
                returnedCompletedRequest.getDateResolved().toLocalDate()
        );


        Mockito.when(mockCompletedRequestDao.insertCompletedRequest(convertedCompletedRequest)).thenReturn(uniqueId);
        Mockito.when(mockCompletedRequestDao.getCompletedRequestWithUniqueId(uniqueId)).thenReturn(returnedCompletedRequest);

        completedRequestService = new CompletedRequestService(mockCompletedRequestDao);
    }


    @Test
    void storeCompletedRequestTest() {
        assertEquals(returnedCompletedRequest, completedRequestService.storeCompletedRequest(inputCompletedRequestModel));
    }

    @Test
    void convertCompletedRequestEntityTest() {
        assertEquals(entityConvertedModel, completedRequestService.convertCompletedRequestEntity(returnedCompletedRequest));
    }

    @Test
    void validateCompletedRequestTest() {
        assertDoesNotThrow(() -> completedRequestService.validateCompletedRequest(inputCompletedRequestModel));
    }

    @Test
    void validateCompletedRequestInvalidManagerIdExceptionTest() {
        CompletedRequest inputCompletedRequestModelFail = new CompletedRequest(storedPendingRequestId, employeeId, -1, managerStatus, managerResponse, dateResolved);
        assertThrows(EmployeeIdException.class, () -> completedRequestService.validateCompletedRequest(inputCompletedRequestModelFail));
    }
}