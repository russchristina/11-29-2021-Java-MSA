package com.revature.service.handleRequest;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.PendingRequest;
import com.revature.repository.DAOClasses.CompletedRequestDao;
import com.revature.repository.DTO.CompletedRequestEntity;
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
class OrderingServiceTest {

    private OrderingService orderingService;

    @Mock
    private PendingRequestService mockPendingRequestService;
    @Mock
    private CompletedRequestService mockCompletedRequestService;
    @Mock
    private CompletedRequestDao mockCompletedRequestDao;


    private int employeeId;
    private int managerId;
    private int typeId1;
    private int typeId2;
    private String requestMessage;
    private double amount;
    private LocalDate dateSubmission;
    private Date sqlDate;
    private Map<Integer, String> requestTypeMap;

    private PendingRequest pr1;
    private PendingRequest pr2;
    private PendingRequest pr3;
    private PendingRequest pr4;

    private CompletedRequestEntity cre1;
    private CompletedRequestEntity cre2;
    private CompletedRequestEntity cre3;
    private CompletedRequestEntity cre4;

    private CompletedRequest cr1;
    private CompletedRequest cr2;
    private CompletedRequest cr3;
    private CompletedRequest cr4;


    private List<PendingRequest> pendingRequestList;
    private List<CompletedRequestEntity> completedRequestEntityList;
    private List<CompletedRequest> completedRequestList;

    @BeforeAll
    void setUp() {

        pendingRequestList = new ArrayList<>();
        completedRequestEntityList = new ArrayList<>();
        completedRequestList = new ArrayList<>();

        employeeId = 1;
        managerId = 2;
        typeId1 = 1;
        typeId2 = 2;

        requestTypeMap = new HashMap<>();
        requestTypeMap.put(1, "cash");
        requestTypeMap.put(2, "books");

        amount = 20.01;

        dateSubmission = LocalDate.of(2022, 1, 1);
        sqlDate = Date.valueOf(dateSubmission);

        PendingRequest pr1 = new PendingRequest(1, employeeId, requestTypeMap.get(typeId1), "Hello Greg", amount, sqlDate.toLocalDate());
        PendingRequest pr2 = new PendingRequest(2, employeeId, requestTypeMap.get(typeId2), "Hello sads", amount + 33, LocalDate.of(2022, 1, 2));
        PendingRequest pr3 = new PendingRequest(3, employeeId, requestTypeMap.get(typeId1), "sdfo sads", amount + 23.02, LocalDate.of(2010, 1, 2));
        PendingRequest pr4 = new PendingRequest(4, employeeId, requestTypeMap.get(typeId2), "dff sddfasdfasdads", amount + 11, LocalDate.of(2019, 1, 2));

        CompletedRequestEntity cre1 = new CompletedRequestEntity(1, managerId, true, "You got it buddy", Date.valueOf(LocalDate.of(2023, 1, 1)));
        CompletedRequestEntity cre2 = new CompletedRequestEntity(2, managerId, true, "You got it buddy", Date.valueOf(LocalDate.of(2020, 1, 1)));
        CompletedRequestEntity cre3 = new CompletedRequestEntity(3, managerId, true, "You got it buddy", Date.valueOf(LocalDate.of(2300, 1, 1)));
        CompletedRequestEntity cre4 = new CompletedRequestEntity(4, managerId, true, "You got it buddy", Date.valueOf(LocalDate.of(2020, 1, 1)));

        CompletedRequest cr1 = new CompletedRequest(1, managerId, true, "You got it buddy", LocalDate.of(2023, 1, 1));
        CompletedRequest cr2 = new CompletedRequest(2, managerId, true, "You got it buddy", LocalDate.of(2020, 1, 1));
        CompletedRequest cr3 = new CompletedRequest(3, managerId, true, "You got it buddy", LocalDate.of(2300, 1, 1));
        CompletedRequest cr4 = new CompletedRequest(4, managerId, true, "You got it buddy", LocalDate.of(2020, 1, 1));

        pendingRequestList.add(pr1);
        pendingRequestList.add(pr2);
        pendingRequestList.add(pr3);
        pendingRequestList.add(pr4);

        completedRequestEntityList.add(cre1);
        completedRequestEntityList.add(cre2);
        completedRequestEntityList.add(cre3);
        completedRequestEntityList.add(cre4);

        completedRequestList.add(cr1);
        completedRequestList.add(cr2);
        completedRequestList.add(cr3);
        completedRequestList.add(cr4);

        MockitoAnnotations.openMocks(this);


        Mockito.when(mockPendingRequestService.getAllPendingRequests()).thenReturn(pendingRequestList);
        Mockito.when(mockCompletedRequestService.convertCompletedRequestEntity(cre1)).thenReturn(cr1);
        Mockito.when(mockCompletedRequestService.convertCompletedRequestEntity(cre2)).thenReturn(cr2);
        Mockito.when(mockCompletedRequestService.convertCompletedRequestEntity(cre3)).thenReturn(cr3);
        Mockito.when(mockCompletedRequestService.convertCompletedRequestEntity(cre4)).thenReturn(cr4);
        try {
            Mockito.when(mockCompletedRequestDao.getCompletedRequest(1)).thenReturn(cre1);
            Mockito.when(mockCompletedRequestDao.getCompletedRequest(2)).thenReturn(cre2);
            Mockito.when(mockCompletedRequestDao.getCompletedRequest(3)).thenReturn(cre3);
            Mockito.when(mockCompletedRequestDao.getCompletedRequest(4)).thenReturn(cre4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        orderingService = new OrderingService(mockPendingRequestService, mockCompletedRequestService, mockCompletedRequestDao);
    }


    @Test
    void orderByDatePending() {
    }

    @Test
    void orderByDateCompleted() {
    }

    @Test
    void orderByAmountPending() {
    }

    @Test
    void orderByAmountCompleted() {
    }
}