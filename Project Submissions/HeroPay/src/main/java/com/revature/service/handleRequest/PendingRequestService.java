package com.revature.service.handleRequest;

import com.revature.presentation.model.requests.NewRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DAOClasses.PendingRequestDao;
import com.revature.repository.DAOClasses.RequestTypeDao;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.service.handleEmployee.EmployeeService;
import com.revature.service.handleRequest.interfaces.PendingRequestServiceInterface;
import com.revature.service.serviceExceptions.EmployeeIdException;
import com.revature.service.serviceExceptions.NegativeAmountException;
import com.revature.service.serviceExceptions.RequestMessageShortException;
import com.revature.service.serviceExceptions.RequestTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PendingRequestService implements PendingRequestServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private final PendingRequestDao pendingRequestDao;
    private final RequestTypeDao requestTypeDao;
    private final EmployeeService employeeService;


    public PendingRequestService(PendingRequestDao pendingRequestDao, RequestTypeDao requestTypeDao, EmployeeService employeeService) {
        this.pendingRequestDao = pendingRequestDao;
        this.requestTypeDao = requestTypeDao;
        this.employeeService = employeeService;
    }

    @Override
    public PendingRequestEntity storePendingRequest(NewRequest newRequest) {
        dLog.debug("Storing new request: " + newRequest);
        PendingRequestEntity pendingRequestEntity = convertNewRequest(newRequest);
        dLog.debug("Storing new request converted to pending request entity: " + pendingRequestEntity);
        int storedPendingRequestId = pendingRequestDao.insertPendingRequest(pendingRequestEntity);
        if(storedPendingRequestId < 0 ) return null;
        dLog.debug("Stored pending request ID: " + storedPendingRequestId);
        PendingRequestEntity retrievedPendingRequest = pendingRequestDao.getPendingRequestByRequestId(storedPendingRequestId);
        dLog.debug("retrieved pending request: " + retrievedPendingRequest);
        return retrievedPendingRequest;
    }

    private PendingRequestEntity convertNewRequest(NewRequest newRequest) {

        dLog.debug("Converting new request to PendingRequestEntity: " + newRequest);
        EmployeeAccountEntity employeeAccountEntity = employeeService.getEmployeeAccountById(newRequest.getEmployeeId());
        RequestTypeEntity requestTypeEntity = requestTypeDao.getRequestTypeWithString(newRequest.getType());;
        BigDecimal amount = newRequest.getAmount();
        PendingRequestEntity pendingRequestEntity = new PendingRequestEntity(0, employeeAccountEntity, requestTypeEntity, newRequest.getRequestMessage(),amount, Date.valueOf(LocalDate.now()), false);
        dLog.debug("New request to pendingRequestEntity complete: " + pendingRequestEntity);
        return pendingRequestEntity;
    }

    @Override
    public void validateNewPendingRequest(NewRequest newRequest) {
        if(newRequest.getEmployeeId() <= 0) throw new EmployeeIdException("Invalid employee ID, <= 0");
        if(newRequest.getType().length() <=1) throw new RequestTypeException("Invalid request Type String given, <= 1 character");
        if(newRequest.getRequestMessage().length() < 5) throw new RequestMessageShortException("Pending Request is < 5 characters");
        dLog.debug("pending request validated: " + PendingRequestService.class);
    }

    @Override
    public List<PendingRequest> getAllEmployeePendingRequest(int employeeId) {
        dLog.debug("Getting all Employee Pending Requests Entities, and converting to models");
        List<PendingRequestEntity> pendingRequestEntityList = pendingRequestDao.getEmployeesPendingRequestList(employeeId);
        List<PendingRequest> pendingRequest = new ArrayList<>(pendingRequestEntityList.size());
        pendingRequestEntityList.forEach(p -> pendingRequest.add(convertPendingRequestEntity(p)));
        return pendingRequest;
    }

    @Override
    public PendingRequest convertPendingRequestEntity(PendingRequestEntity p) {
        dLog.debug("Converting Pending Request Entity to pending Request model: " + p);
        return new PendingRequest(
                p.getId(),
                p.getEmployeeAccount().getId(),
                p.getRequestType().getRequestType(),
                p.getRequestMessage(),
                p.getAmount(),
                p.getDateSubmission().toLocalDate(),
                p.isStatus());
    }

    @Override
    public void deletePendingRequest(int requestId) {
        dLog.debug("Deleting pending request-" + requestId + " :" + PendingRequestService.class);
        pendingRequestDao.deletePendingRequest(pendingRequestDao.getPendingRequestByRequestId(requestId));
    }

    @Override
    public List<PendingRequest> getAllPendingRequests() {
        List<PendingRequestEntity> pendingRequestsEntities = pendingRequestDao.getAllPendingRequests();
        List<PendingRequest> pendingRequests = new ArrayList<>(pendingRequestsEntities.size());
        pendingRequestsEntities.forEach(p -> pendingRequests.add(convertPendingRequestEntity(p)));
        return pendingRequests;
 }

    @Override
    public List<PendingRequest> getPendingRequestByType(int typeId) {
        dLog.debug("getting pending requests entities and converting to pending request by request typeId - " + typeId);
        List<PendingRequestEntity> pendingRequestsEntities = pendingRequestDao.getPendingRequestsByTypeId(typeId);
        List<PendingRequest> pendingRequests = new ArrayList<>(pendingRequestsEntities.size());
        pendingRequestsEntities.forEach(p -> pendingRequests.add(convertPendingRequestEntity(p)));
        return pendingRequests;
        }

    @Override
    public void updatePendingRequestStatus(int requestId, boolean status) {
        dLog.debug("Updating pending request status");
        PendingRequestEntity pendingRequest = pendingRequestDao.getPendingRequestByRequestId(requestId);
        pendingRequest.setStatus(status);
        pendingRequestDao.updatePendingRequestStatus(pendingRequest);
   }

    @Override
    public List<PendingRequest> getAnsweredRequests() {
        dLog.debug("getting pending requests entities and converting to pending request by answered");
        List<PendingRequestEntity> pendingRequestsEntities = pendingRequestDao.getPendingRequestByStatus(true);
        List<PendingRequest> pendingRequests = new ArrayList<>(pendingRequestsEntities.size());
        pendingRequestsEntities.forEach(p -> pendingRequests.add(convertPendingRequestEntity(p)));
        return pendingRequests;
    }

    @Override
    public List<PendingRequest> getAllAnsweredRequestsByEmployeeId(int employeeId) {
        dLog.debug("getting pending requests entities and converting to pending request by answered");
        List<PendingRequestEntity> pendingRequestsEntities = pendingRequestDao.getAnsweredEmployeePendingRequests(employeeId);
        List<PendingRequest> pendingRequests = new ArrayList<>(pendingRequestsEntities.size());
        pendingRequestsEntities.forEach(p -> pendingRequests.add(convertPendingRequestEntity(p)));
        return pendingRequests;
    }

    @Override
    public List<PendingRequest> getAllAnsweredRequestsByType(int typeId) {
        dLog.debug("getting pending requests entities and converting to pending request by answered requests and type");
        List<PendingRequestEntity> pendingRequestsEntities = pendingRequestDao.getAnsweredPendingRequestsByType(typeId);
        List<PendingRequest> pendingRequests = new ArrayList<>(pendingRequestsEntities.size());
        pendingRequestsEntities.forEach(p -> pendingRequests.add(convertPendingRequestEntity(p)));
        return pendingRequests;
    }

    @Override
    public List<RequestTypeEntity> getRequestTypes() {
        return requestTypeDao.getRequestTypes();
    }

    @Override
    public List<PendingRequest> getAllAnsweredRequestsByRole(int id) {
        return null;
    }
}
