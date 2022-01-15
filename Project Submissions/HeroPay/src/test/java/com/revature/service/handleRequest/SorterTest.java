package com.revature.service.handleRequest;

import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SorterTest {
    private int employeeId;
    private int managerId;
    private int typeId1;
    private int typeId2;
    private String requestMessage;
    private BigDecimal amount1;
    private BigDecimal amount2;
    private BigDecimal amount3;
    private BigDecimal amount4;
    private LocalDate dateSubmission;
    private Date sqlDate;
    private List<RequestTypeEntity> requestTypeEntityList;

    private PendingRequestEntity pr1;
    private PendingRequestEntity pr2;
    private PendingRequestEntity pr3;
    private PendingRequestEntity pr4;

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

    private EmployeeAccountEntity employeeAccount1;
    private EmployeeAccountEntity employeeAccount2;
    private EmployeeAccountEntity managerAccount;

    private RequestTypeEntity requestType1;
    private RequestTypeEntity requestType2;

    private SortingService sortingService;

    @BeforeAll
    void setUp() {

        pendingRequestList = new ArrayList<>();
        completedRequestEntityList = new ArrayList<>();
        completedRequestList = new ArrayList<>();

        employeeId = 1;
        managerId = 2;
        typeId1 = 1;
        typeId2 = 2;

        requestType1 = new RequestTypeEntity(1, "Travel");
        requestType2 = new RequestTypeEntity(2, "Consumable");

        requestTypeEntityList = new ArrayList<>();
        requestTypeEntityList.add(requestType1);
        requestTypeEntityList.add(requestType2);

        amount1 = new BigDecimal("20.01");
        amount2 = new BigDecimal("2.32");
        amount3 = new BigDecimal("214.01");
        amount4 = new BigDecimal("123.23");

        dateSubmission = LocalDate.of(2022, 1, 1);
        sqlDate = Date.valueOf(dateSubmission);

        employeeAccount1 = new EmployeeAccountEntity();
        employeeAccount2 = new EmployeeAccountEntity();
        managerAccount = new EmployeeAccountEntity();

        PendingRequestEntity pre1 = new PendingRequestEntity(1, employeeAccount1, requestType1, "Hello Greg", amount1, Date.valueOf(LocalDate.now()), true, false);
        PendingRequestEntity pre2 = new PendingRequestEntity(2, employeeAccount1, requestType2, "Hello sads", amount2, Date.valueOf(LocalDate.of(2022, 1, 2)), true, false);
        PendingRequestEntity pre3 = new PendingRequestEntity(3, employeeAccount2, requestType1, "sdfo sads", amount3, Date.valueOf(LocalDate.of(2010, 1, 2)), true, false);
        PendingRequestEntity pre4 = new PendingRequestEntity(4, employeeAccount2, requestType2, "dff sddfasdfasdads", amount4, Date.valueOf(LocalDate.of(2019, 1, 2)), true, false);

        PendingRequest pr1 = new PendingRequest(1, 1, requestType1.getRequestType(), "Hello Greg", amount1, (LocalDate.now()), true, false);
        PendingRequest pr2 = new PendingRequest(2, 1, requestType2.getRequestType(), "Hello sads", amount2, (LocalDate.of(2022, 1, 2)), true, false);
        PendingRequest pr3 = new PendingRequest(3, 2, requestType1.getRequestType(), "sdfo sads", amount3, (LocalDate.of(2010, 1, 2)), true, false);
        PendingRequest pr4 = new PendingRequest(4, 2, requestType2.getRequestType(), "dff sddfasdfasdads", amount4, (LocalDate.of(2019, 1, 2)), true, false);

        CompletedRequestEntity cre1 = new CompletedRequestEntity(pre1, employeeAccount1, managerAccount, true, "You got it buddy", Date.valueOf(LocalDate.of(2023, 1, 1)), 1);
        CompletedRequestEntity cre2 = new CompletedRequestEntity(pre2, employeeAccount1, managerAccount, true, "You got it buddy", Date.valueOf(LocalDate.of(2020, 1, 1)), 2);
        CompletedRequestEntity cre3 = new CompletedRequestEntity(pre3, employeeAccount2, managerAccount, true, "You got it buddy", Date.valueOf(LocalDate.of(2300, 1, 1)), 3);
        CompletedRequestEntity cre4 = new CompletedRequestEntity(pre4, employeeAccount2, managerAccount, true, "You got it buddy", Date.valueOf(LocalDate.of(2020, 1, 1)), 4);

        CompletedRequest cr1 = new CompletedRequest(1, employeeId,  managerId, true, "You got it buddy", LocalDate.of(2023, 1, 1));
        CompletedRequest cr2 = new CompletedRequest(2, employeeId,  managerId, true, "You got it buddy", LocalDate.of(2020, 1, 1));
        CompletedRequest cr3 = new CompletedRequest(3, employeeId,  managerId, true, "You got it buddy", LocalDate.of(2300, 1, 1));
        CompletedRequest cr4 = new CompletedRequest(4, employeeId,  managerId, true, "You got it buddy", LocalDate.of(2020, 1, 1));

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


        sortingService = new SortingService();
    }


    @Test
    void orderByDatePending() {
        List<PendingRequest> orderedList = sortingService.orderByDatePending(pendingRequestList);
        assertTrue(orderedList.get(0).getDateSubmission().compareTo(orderedList.get(1).getDateSubmission()) <= 0);
    }

    @Test
    void orderByDateCompleted() {
        List<CompletedRequest> orderedList = sortingService.orderByDateCompleted(completedRequestList);
        assertTrue(orderedList.get(0).getDateResolved().compareTo(orderedList.get(1).getDateResolved()) <= 0);
    }

    @Test
    void orderByAmountPending() {
        List<PendingRequest> orderedList = sortingService.orderByAmountPending(pendingRequestList);
        assertTrue(orderedList.get(0).getAmount().compareTo(orderedList.get(1).getAmount()) <= 0);
    }

}

