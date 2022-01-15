package com.revature.repository.DAOClasses;

import com.revature.repository.DTO.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CompletedRequestDaoTest {

    private CompletedRequestDao cDao;
    private CompletedRequestEntity returnedCompletedRequest;
    private PendingRequestEntity returnedPendingRequest;
    private EmployeeAccountEntity returnedManagerAccount;
    private boolean managerStatus;
    private String managerResponse;
    private Date dateResolved;
    private int uniqueId;

    private EmployeeAccountEntity returnedEmployeeAccount;
    private RequestTypeEntity returnedType;
    private int storedPendingRequestId;
    private String storedRequestMessage;
    private BigDecimal storedAmount;
    private Date dateSubmission;
    private boolean status;

    private PendingRequestEntity testPendingEntity;
    private CompletedRequestEntity testCompletedEntity;

    @BeforeAll
    void setUp() {
        cDao = new CompletedRequestDao();

        returnedEmployeeAccount = new EmployeeAccountEntity(3, "Gwyndolyn", "Braveheart", new EmployeeRoleEntity(1, "Knight"));
        returnedType = new RequestTypeEntity(1, "Travel");
        storedPendingRequestId = 1;
        storedRequestMessage = "Las Vegas baby";
        storedAmount = new BigDecimal("0.05");
        dateSubmission = Date.valueOf(LocalDate.of(2022, 01, 11));
        status = true;
        returnedPendingRequest = new PendingRequestEntity(storedPendingRequestId, returnedEmployeeAccount, returnedType, storedRequestMessage, storedAmount, dateSubmission, status, false);

        returnedManagerAccount = new EmployeeAccountEntity(12, "Misha", "Marcus", new EmployeeRoleEntity(4, "Manager"));
        managerStatus = false;
        managerResponse = "Request for more bro.";
        dateResolved = Date.valueOf(LocalDate.of(2022,01,11));
        uniqueId = 5;

        returnedCompletedRequest = new CompletedRequestEntity(
                returnedPendingRequest,
                returnedEmployeeAccount,
                returnedManagerAccount,
                managerStatus,
                managerResponse,
                dateResolved,
                uniqueId
                );

        testPendingEntity = new PendingRequestEntity(
                0,
                new EmployeeAccountEntity(2,"William", "Johnson", new EmployeeRoleEntity(1, "Knight")),
                new RequestTypeEntity(1, "Travel"),
                "This is just me testing",
                new BigDecimal("30.30"),
                Date.valueOf(LocalDate.of(2300, 2, 3)),
                false,
                false);

        testCompletedEntity = new CompletedRequestEntity(testPendingEntity, testPendingEntity.getEmployeeAccount(), returnedManagerAccount, false, "Testing Completion", dateResolved, 0);

    }

    @Test
    @Ignore
    void getCompletedRequest() {
        assertEquals(returnedCompletedRequest, cDao.getCompletedRequestWithUniqueId(uniqueId));
    }

    @Test
    void insertCompletedRequestSuccessTest() {
        assertTrue(cDao.insertCompletedRequest(returnedCompletedRequest) != 0);
    }

    @Test
    void insertCompletedRequestInvalidPendingRequestTest() {
        assertEquals(-1, cDao.insertCompletedRequest(new CompletedRequestEntity(testPendingEntity, returnedEmployeeAccount, returnedManagerAccount, false, "Testing", dateResolved, 0)));
    }

    @Test
    void getCompletedRequestByEmployeeIdTest() {
        assertEquals(returnedEmployeeAccount, cDao.getCompletedRequestByEmployeeId(returnedEmployeeAccount).get(0).getEmployeeAccount());
    }

    @Test
    void getCompletedRequestByEmployeeIdInvalidIdTest() {
        assertEquals(0, cDao.getCompletedRequestByEmployeeId(new EmployeeAccountEntity(-1, "", "", new EmployeeRoleEntity())).size());
    }

    @Test
    void getCompletedRequestByManagerIdTest() {
        assertEquals(returnedManagerAccount, cDao.getCompletedRequestByManagerId(returnedManagerAccount).get(0).getManagerAccount());
    }

    @Test
    void getAllCompletedRequestListTest() {
        assertNotNull(cDao.getAllCompletedRequestList());
    }

    @Test
    void getCompletedRequestsByStatusTrueTest() {
        assertTrue(cDao.getCompletedRequestsByStatus(true).get(0).isStatus());
    }

    @Test
    void getCompletedRequestsByStatusFalseTest() {
        assertFalse(cDao.getCompletedRequestsByStatus(false).get(0).isStatus());
    }
}