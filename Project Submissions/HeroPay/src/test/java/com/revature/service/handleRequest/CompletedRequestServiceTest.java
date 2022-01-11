//package com.revature.service.handleRequest;
//
//import com.revature.presentation.model.requests.recieve.CompletedRequest;
//import com.revature.presentation.model.requests.PendingRequest;
//import com.revature.repository.DAOClasses.CompletedRequestDao;
//import com.revature.repository.DTO.CompletedRequestEntity;
//import com.revature.service.serviceExceptions.EmployeeIdException;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.sql.Date;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class CompletedRequestServiceTest {
//
//    @Mock
//    private CompletedRequestDao mockCompletedRequestDao;
//
//    private CompletedRequestService completedRequestService;
//
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
//
//    @BeforeAll
//    void setup(){
//        newRequestId = 0;
//        storedRequestId = 1;
//        employeeId = 2;
//        managerId = 1;
//        response = "You are not here";
//        dateResolved = LocalDate.of(2021, 2, 2);
//        sqlDate = Date.valueOf(dateResolved);
//
//
//        newCompleteRequest = new CompletedRequest(
//                storedRequestId, employeeId, managerId, true, response, dateResolved);
//
//        oldCompleteRequest = new CompletedRequest(
//                storedRequestId, employeeId, managerId, true, response, dateResolved);
//
//        completedRequestEntity = new CompletedRequestEntity(
//                storedRequestId, employeeId, managerId, true, response, sqlDate);
//
//        completedRequestEntityList = new ArrayList<>();
//        completedRequestList = new ArrayList<>();
//
//        completedRequestEntityListByManagerId = new ArrayList<>();
//        completedRequestListByManagerId = new ArrayList<>();
//
//        statusCompleteRequestList = new ArrayList<>();
//        statusRequestList = new ArrayList<>();
//
//        completedRequestEntityList.add(new CompletedRequestEntity(1, employeeId, managerId, true, "Hello", sqlDate));
//        completedRequestEntityList.add(new CompletedRequestEntity(2, employeeId, managerId, false, "Greeting", Date.valueOf(LocalDate.of(2000, 2, 4))));
//        completedRequestEntityList.add(new CompletedRequestEntity(3, employeeId, 5, false, "Bonjour", Date.valueOf(LocalDate.of(1888, 2, 4))));
//
//        completedRequestEntityListByManagerId.add(new CompletedRequestEntity(1, employeeId, managerId, true, "Hello", sqlDate));
//        completedRequestEntityListByManagerId.add(new CompletedRequestEntity(2, employeeId, managerId, false, "Greeting", Date.valueOf(LocalDate.of(2000, 2, 4))));
//
//        completedRequestList.add(new CompletedRequest(1, employeeId, managerId, true, "Hello", sqlDate.toLocalDate()));
//        completedRequestList.add(new CompletedRequest(2, employeeId, managerId, false, "Greeting", LocalDate.of(2000, 2, 4)));
//        completedRequestList.add(new CompletedRequest(3, employeeId, 5, false, "Bonjour", LocalDate.of(1888, 2, 4)));
//
//        completedRequestListByManagerId.add(new CompletedRequest(1, employeeId, managerId, true, "Hello", sqlDate.toLocalDate()));
//        completedRequestListByManagerId.add(new CompletedRequest(2, employeeId, managerId, false, "Greeting", LocalDate.of(2000, 2, 4)));
//
//        statusCompleteRequestList.add(new CompletedRequestEntity(2, employeeId, managerId, false, "Greeting", Date.valueOf(LocalDate.of(2000, 2, 4))));
//        statusCompleteRequestList.add(new CompletedRequestEntity(3, employeeId, 5, false, "Bonjour", Date.valueOf(LocalDate.of(1888, 2, 4))));
//
//        statusRequestList.add(new CompletedRequest(2, employeeId, managerId, false, "Greeting", LocalDate.of(2000, 2, 4)));
//        statusRequestList.add(new CompletedRequest(3, employeeId, 5, false, "Bonjour", LocalDate.of(1888, 2, 4)));
//
//
//
//        MockitoAnnotations.openMocks(this);
//        try {
//            Mockito.when(mockCompletedRequestDao.insertCompletedRequest(1, employeeId, managerId, true, response, dateResolved)).thenReturn(completedRequestEntity);
//            Mockito.when(mockCompletedRequestDao.getAllCompletedRequestList()).thenReturn(completedRequestEntityList);
//            Mockito.when(mockCompletedRequestDao.getCompletedRequestByManagerIdList(managerId)).thenReturn(completedRequestEntityListByManagerId);
//            Mockito.when(mockCompletedRequestDao.getCompletedRequestByStatus(false)).thenReturn(statusCompleteRequestList);
//            Mockito.when(mockCompletedRequestDao.deleteCompletedRequest(storedRequestId)).thenReturn(completedRequestEntity);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        completedRequestService = new CompletedRequestService(mockCompletedRequestDao);
//    }
//
//    @Test
//    void storeCompletedRequestTest() {
//        assertEquals(completedRequestEntity, completedRequestService.storeCompletedRequest(new CompletedRequest(1, employeeId, managerId, true, response, dateResolved)));
//    }
//
//    @Test
//    void storeCompleteRequestNullReturnInvalidRequestTest(){
//        assertNull(completedRequestService.storeCompletedRequest(new CompletedRequest()));
//    }
//
//    @Test
//    void convertCompletedRequestEntityTest() {
//        assertEquals(oldCompleteRequest, completedRequestService.convertCompletedRequestEntity(completedRequestEntity));
//    }
//
//    @Test
//    void validateCompletedRequestInvalidManagerIdExceptionTest(){
//        assertThrows(EmployeeIdException.class, () -> completedRequestService.validateCompletedRequest(
//                new CompletedRequest(0,1,  -1, true, response, dateResolved))
//        );
//    }
//
//    @Test
//    void getAllCompletedRequestsTest() {
//        assertEquals(completedRequestList, completedRequestService.getAllCompletedRequests());
//    }
//
//    @Test
//    void getAllCompletedRequestsByManagerIdTest() {
//        assertEquals(completedRequestListByManagerId, completedRequestService.getAllCompletedRequestsByManagerId(managerId));
//    }
//
//    @Test
//    void getAllCompletedRequestsByManagerIdInvalidManagerIdTest() {
//        assertNotEquals(completedRequestListByManagerId, completedRequestService.getAllCompletedRequestsByManagerId(-1));
//    }
//
//    @Test
//    void getAllCompletedRequestsByStatusTest() {
//        assertEquals(statusRequestList, completedRequestService.getAllCompletedRequestsByStatus(false));
//    }
//
//    @Test
//    void getAllCompletedRequestsByDifferentStatusTest() {
//        assertNotEquals(statusRequestList, completedRequestService.getAllCompletedRequestsByStatus(true));
//    }
//
//    @Test
//    void deleteCompletedRequestTest() {
//        assertEquals(completedRequestEntity, completedRequestService.deleteCompletedRequest(storedRequestId));
//    }
//
//    @Test
//    void deleteCompletedRequestInvalidIdNullTest(){
//        assertNull(completedRequestService.deleteCompletedRequest(-1));
//    }
//
//    @Test
//    void convertPendingRequest() {
//
//    }
//}