package com.revature.repository.DAOClasses;

import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
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
class PendingRequestDaoTest {

    private PendingRequestDao pDao;
    private PendingRequestEntity returnedPendingRequest;
    private EmployeeAccountEntity returnedEmployeeAccount;
    private RequestTypeEntity returnedType;
    private int storedPendingRequestId;
    private String storedRequestMessage;
    private BigDecimal storedAmount;
    private Date dateSubmission;
    private boolean status;

    private PendingRequestEntity testEntity;

    @BeforeAll
    void setUp() {
        pDao = new PendingRequestDao();
        returnedEmployeeAccount = new EmployeeAccountEntity(3, "Gwyndolyn", "Braveheart", new EmployeeRoleEntity(1, "Knight"));
        returnedType = new RequestTypeEntity(1, "Travel");
        storedPendingRequestId = 1;
        storedRequestMessage = "Las Vegas baby";
        storedAmount = new BigDecimal("0.05");
        dateSubmission = Date.valueOf(LocalDate.of(2022, 01, 11));
        status = true;
        returnedPendingRequest = new PendingRequestEntity(storedPendingRequestId, returnedEmployeeAccount, returnedType, storedRequestMessage, storedAmount, dateSubmission, status, false);

        testEntity = new PendingRequestEntity(
                0,
                new EmployeeAccountEntity(2,"William", "Johnson", new EmployeeRoleEntity(1, "Knight")),
                new RequestTypeEntity(1, "Travel"),
                "This is just me testing",
                new BigDecimal("30.30"),
                Date.valueOf(LocalDate.of(2300, 2, 3)),
                false,
                false);
    }

    @Test
    void getPendingRequestsByTypeIdTest() {
        assertEquals(returnedType, pDao.getPendingRequestsByTypeId(returnedType).get(0).getRequestType());
    }

    @Test
    void getPendingRequestsByTypeIdWrongRequestTypeNameTest() {
        assertEquals(returnedType, pDao.getPendingRequestsByTypeId(new RequestTypeEntity(1, "WRONG")).get(0).getRequestType());

    }

    @Test
    void getPendingRequestsByInvalidTypeIdNullTest() {
        assertEquals(0, pDao.getPendingRequestsByTypeId(new RequestTypeEntity(42, "Travel")).size());
    }

    @Test
    void getPendingRequestByStatusTest() {
        assertEquals(status, pDao.getPendingRequestByStatus(status).get(0).isStatus());
    }

    @Test
    void insertPendingRequestTest() {
        int insertedId = pDao.insertPendingRequest(testEntity);
        assertEquals(insertedId, pDao.getPendingRequestByRequestId(insertedId).getId());
    }


    @Test
    void insertPendingRequestInvalidEmployeeAccountIdTest() {
        PendingRequestEntity invalidRequest = new PendingRequestEntity(
                0,
                new EmployeeAccountEntity(99,"", "", new EmployeeRoleEntity(1, "Knight")),
                new RequestTypeEntity(1, "Travel"),
                "This is just me testing",
                new BigDecimal("30.30"),
                Date.valueOf(LocalDate.of(2300, 2, 3)),
                false,
                false);

        assertEquals(-1, pDao.insertPendingRequest(invalidRequest));
    }

    @Test
    void insertPendingRequestInvalidRequestTypeTest() {
        PendingRequestEntity invalidRequest = new PendingRequestEntity(
                0,
                new EmployeeAccountEntity(2,"", "", new EmployeeRoleEntity(1, "Knight")),
                new RequestTypeEntity(-1, "Travel"),
                "This is just me testing",
                new BigDecimal("30.30"),
                Date.valueOf(LocalDate.of(2300, 2, 3)),
                false,
                false);

        assertEquals(-1, pDao.insertPendingRequest(invalidRequest));
    }

    @Test
    void getPendingRequestByRequestIdTest() {
        assertEquals(returnedPendingRequest, pDao.getPendingRequestByRequestId(storedPendingRequestId));
    }

    @Test
    void getEmployeesPendingRequestListTest() {
        assertEquals(returnedEmployeeAccount, pDao.getEmployeesPendingRequestList(returnedEmployeeAccount).get(0).getEmployeeAccount());
    }

    @Test
    void getEmployeesPendingRequestListInvalidEmployeeAccountTest() {
        assertEquals(0, pDao.getEmployeesPendingRequestList(new EmployeeAccountEntity(-1, "", "", new EmployeeRoleEntity(1, ""))).size());
    }

    @Test
    void getAllPendingRequestsTest() {
        assertNotNull(pDao.getAllPendingRequests());
    }

    @Test
    void updatePendingRequestTest() {
        int insertedId = pDao.insertPendingRequest(testEntity);
        PendingRequestEntity originalEntity = pDao.getPendingRequestByRequestId(insertedId);
        PendingRequestEntity updatedEntity = new PendingRequestEntity(
                originalEntity.getId(),
                originalEntity.getEmployeeAccount(),
                originalEntity.getRequestType(),
                originalEntity.getRequestMessage(),
                originalEntity.getAmount(),
                originalEntity.getDateSubmission(),
                true,
                false
        );
        pDao.updatePendingRequest(updatedEntity);
        assertEquals(updatedEntity, pDao.getPendingRequestByRequestId(originalEntity.getId()));
    }

    @Test
    void deletePendingRequestTest() {
        int insertedId = pDao.insertPendingRequest(testEntity);
        PendingRequestEntity originalEntity = pDao.getPendingRequestByRequestId(insertedId);
        pDao.deletePendingRequest(originalEntity);
        assertNull(pDao.getPendingRequestByRequestId(originalEntity.getId()));
    }

    @Test
    void getAnsweredEmployeePendingRequestsEmployeeAccountTest() {
        assertEquals(returnedEmployeeAccount, pDao.getAnsweredEmployeePendingRequests(returnedEmployeeAccount).get(0).getEmployeeAccount());
    }

    @Test
    void getAnsweredEmployeePendingRequestsStatusTest() {
        assertTrue(pDao.getAnsweredEmployeePendingRequests(returnedEmployeeAccount).get(0).isStatus());
    }

    @Test
    void getAnsweredEmployeePendingRequestsInvalidEmployeeTest() {
        assertEquals(0, pDao.getAnsweredEmployeePendingRequests(new EmployeeAccountEntity(-1, "", "", new EmployeeRoleEntity(1, ""))).size());
    }

    @Test
    void getAnsweredPendingRequestsByTypeTest() {
        assertEquals(returnedType, pDao.getAnsweredPendingRequestsByType(returnedType).get(0).getRequestType());
    }

    @Test
    void getAnsweredPendingRequestsByTypeJustIdTest() {
        assertEquals(new RequestTypeEntity(1,"Travel"), pDao.getAnsweredPendingRequestsByType(new RequestTypeEntity(1,"")).get(0).getRequestType());
    }
}