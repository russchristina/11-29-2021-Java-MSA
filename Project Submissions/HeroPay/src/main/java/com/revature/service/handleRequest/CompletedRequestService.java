package com.revature.service.handleRequest;

import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.repository.DAOClasses.CompletedRequestDao;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.repository.DTO.EmployeeRoleEntity;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.service.handleRequest.interfaces.CompletedRequestServiceInterface;
import com.revature.service.serviceExceptions.EmployeeIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CompletedRequestService implements CompletedRequestServiceInterface {
    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private final CompletedRequestDao completedRequestDao;

    public CompletedRequestService(CompletedRequestDao completedRequestDao) {
        this.completedRequestDao = completedRequestDao;
    }

    @Override
    public CompletedRequestEntity storeCompletedRequest(CompletedRequest completedRequest) {
        dLog.debug("Storing completed Request: " + completedRequest);
        return completedRequestDao.getCompletedRequestWithUniqueId(completedRequestDao.insertCompletedRequest(convertCompletedRequestModel(completedRequest)));
    }

    private CompletedRequestEntity convertCompletedRequestModel(CompletedRequest completedRequest) {
        dLog.debug("converting completed request model: " + completedRequest);
        PendingRequestEntity pendingRequestEntity = new PendingRequestEntity();
        pendingRequestEntity.setId(completedRequest.getId());
        EmployeeAccountEntity employeeAccountEntity = new EmployeeAccountEntity();
        employeeAccountEntity.setId(completedRequest.getEmployeeId());
        EmployeeAccountEntity manager = new EmployeeAccountEntity();
        manager.setId(completedRequest.getManagerId());
        return new CompletedRequestEntity(
                pendingRequestEntity,
                employeeAccountEntity,
                manager,
                completedRequest.isStatus(),
                completedRequest.getResponse(),
                Date.valueOf(completedRequest.getDateResolved()),
                0
        );
    }


    @Override
    public CompletedRequest convertCompletedRequestEntity(CompletedRequestEntity completedRequestEntity) {
        dLog.debug("converting Completed request entity to completed request model: " + completedRequestEntity);
        return new CompletedRequest(
                completedRequestEntity.getPendingRequest().getId(),
                completedRequestEntity.getEmployeeAccount().getId(),
                completedRequestEntity.getManagerAccount().getId(),
                completedRequestEntity.isStatus(),
                completedRequestEntity.getResponse(),
                completedRequestEntity.getDateResolved().toLocalDate()
        );
    }

    @Override
    public void validateCompletedRequest(CompletedRequest completedRequest) {
        if(completedRequest.getManagerId() <= 0) throw new EmployeeIdException("Invalid Manager ID, <= 0");
        dLog.debug("Completed request validated");
    }

    @Override
    public List<CompletedRequest> getAllCompletedRequests() {
        dLog.debug("getting all completed requests");
        List<CompletedRequestEntity> completedRequestEntities = completedRequestDao.getAllCompletedRequestList();
        List<CompletedRequest> completedRequests = new ArrayList<>(completedRequestEntities.size());
        completedRequestEntities.forEach(e -> completedRequests.add(convertCompletedRequestEntity(e)));
        return completedRequests;
    }

    public List<CompletedRequestEntity> getAllCompletedRequestsNotConverted(){
        return completedRequestDao.getAllCompletedRequestList();
    }

    @Override
    public List<CompletedRequest> getAllCompletedRequestsByManagerId(int managerId) {
        dLog.debug("getting all completed requests by manager ID");
        List<CompletedRequestEntity> completedRequestEntities = completedRequestDao.getCompletedRequestByManagerId(new EmployeeAccountEntity(managerId, "", "", new EmployeeRoleEntity(4, "Manager")));
        List<CompletedRequest> completedRequests = new ArrayList<>(completedRequestEntities.size());
        completedRequestEntities.forEach(e -> completedRequests.add(convertCompletedRequestEntity(e)));
        return completedRequests;
    }

    @Override
    public List<CompletedRequest> getAllCompletedRequestsByStatus(boolean status) {
        dLog.debug("getting all completed requests by status");
        List<CompletedRequestEntity> completedRequestEntities = completedRequestDao.getCompletedRequestsByStatus(status);
        List<CompletedRequest> completedRequests = new ArrayList<>(completedRequestEntities.size());
        completedRequestEntities.forEach(e -> completedRequests.add(convertCompletedRequestEntity(e)));
        return completedRequests;
    }

    @Override
    public List<CompletedRequest> getAllEmployeeRequests(int employeeId) {
        dLog.debug("getting all completed requests by employee Id");
        List<CompletedRequestEntity> completedRequestEntities = completedRequestDao.getCompletedRequestByEmployeeId(new EmployeeAccountEntity(employeeId, "", "", new EmployeeRoleEntity()));
        List<CompletedRequest> completedRequests = new ArrayList<>(completedRequestEntities.size());
        completedRequestEntities.forEach(e -> completedRequests.add(convertCompletedRequestEntity(e)));
        return completedRequests;
    }

}
