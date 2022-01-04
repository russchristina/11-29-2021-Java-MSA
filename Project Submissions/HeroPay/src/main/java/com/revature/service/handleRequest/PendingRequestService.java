package com.revature.service.handleRequest;

import com.revature.presentation.model.PendingRequest;
import com.revature.repository.DAOClasses.PendingRequestDao;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;
import com.revature.service.handleRequest.interfaces.PendingRequestServiceInterface;
import com.revature.service.serviceExceptions.EmployeeIdException;
import com.revature.service.serviceExceptions.NegativeAmountException;
import com.revature.service.serviceExceptions.RequestMessageShortException;
import com.revature.service.serviceExceptions.RequestTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PendingRequestService implements PendingRequestServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(PendingRequestService.class);
    private final Logger transactionLogger = LoggerFactory.getLogger("transactionLogger");

    private PendingRequestDao pendingRequestDao;
    private Map<Integer, String> requestMap;

    public PendingRequestService(PendingRequestDao pendingRequestDao) throws SQLException {

        this.pendingRequestDao = pendingRequestDao;
        this.requestMap = pendingRequestDao.getRequestTypeMap();
    }

    @Override
    public PendingRequestEntity storePendingRequest(PendingRequest pendingRequest) {

        try {
            PendingRequestEntity pr = pendingRequestDao.insertPendingRequest(
                    pendingRequest.getEmployeeId(),
                    pendingRequestDao.getRequestTypeWithString(pendingRequest.getType()).getId(),
                    pendingRequest.getRequestMessage(),
                    pendingRequest.getAmount(),
                    Date.valueOf(pendingRequest.getDateSubmission())
            );
            if(pr != null) transactionLogger.info("pending request stored in database");
            return pr;
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
        logger.debug("Failed to store pending request in database");
        return null;
    }

    @Override
    public boolean validateNewPendingRequest(PendingRequest pendingRequest) {

        if(pendingRequest.getEmployeeId() <= 0) throw new EmployeeIdException("Invalid employee ID, <= 0");
        if(pendingRequest.getType().length() <=1) throw new RequestTypeException("Invalid request Type String given, <= 1 character");
        if(pendingRequest.getRequestMessage().length() < 5) throw new RequestMessageShortException("Pending Request is < 5 characters");
        if(pendingRequest.getAmount() <= 0) throw new NegativeAmountException("pending request amount");

        logger.debug("pending request validated");
        return true;
    }

    @Override
    public List<PendingRequest> getAllEmployeePendingRequest(int employeeId) {

        try {
            List<PendingRequestEntity> pendingRequestList =  pendingRequestDao.getEmployeePendingRequestList(employeeId);
            List<PendingRequest> pendingModelList = new ArrayList<>();
            pendingRequestList.forEach(
                    pendingRequestEntity ->
                            pendingModelList.add(new PendingRequest(
                                    pendingRequestEntity.getId(),
                                    pendingRequestEntity.getEmployeeId(),
                                    requestMap.get(pendingRequestEntity.getRequestType()),
                                    pendingRequestEntity.getRequestMessage(),
                                    pendingRequestEntity.getAmount(),
                                    pendingRequestEntity.getDateSubmission().toLocalDate())));
            return pendingModelList;
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }

        return null;
    }

    @Override
    public PendingRequestEntity deletePendingRequest(int requestId) {

        try {
            return pendingRequestDao.deletePendingRequest(requestId);
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }
        logger.debug("Failed to delete pending request: " + requestId);
        return null;
    }

    @Override
    public List<PendingRequest> getAllPendingRequests() {
        try {
            List<PendingRequestEntity> pendingRequestList =  pendingRequestDao.getAllPendingRequests();
            List<PendingRequest> pendingModelList = new ArrayList<>();
            pendingRequestList.forEach(
                    pendingRequestEntity ->
                            pendingModelList.add(new PendingRequest(
                                    pendingRequestEntity.getId(),
                                    pendingRequestEntity.getEmployeeId(),
                                    requestMap.get(pendingRequestEntity.getRequestType()),
                                    pendingRequestEntity.getRequestMessage(),
                                    pendingRequestEntity.getAmount(),
                                    pendingRequestEntity.getDateSubmission().toLocalDate())));
            return pendingModelList;
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }

        return null;    }

    @Override
    public List<PendingRequest> getPendingRequestByType(int typeId) {
        try {
            List<PendingRequestEntity> pendingRequestList =  pendingRequestDao.getAllPendingRequestsByType(typeId);
            List<PendingRequest> pendingModelList = new ArrayList<>();
            pendingRequestList.forEach(
                    pendingRequestEntity ->
                            pendingModelList.add(new PendingRequest(
                                    pendingRequestEntity.getId(),
                                    pendingRequestEntity.getEmployeeId(),
                                    requestMap.get(pendingRequestEntity.getRequestType()),
                                    pendingRequestEntity.getRequestMessage(),
                                    pendingRequestEntity.getAmount(),
                                    pendingRequestEntity.getDateSubmission().toLocalDate())));
            return pendingModelList;
        } catch (SQLException e) {
            logger.error(String.valueOf(e));
        }

        return null;    }

    @Override
    public PendingRequest convertPendingRequestEntity(PendingRequestEntity pendingRequestEntity) {
        return new PendingRequest(
                pendingRequestEntity.getId(),
                pendingRequestEntity.getEmployeeId(),
                requestMap.get(pendingRequestEntity.getRequestType()),
                pendingRequestEntity.getRequestMessage(),
                pendingRequestEntity.getAmount(),
                pendingRequestEntity.getDateSubmission().toLocalDate());
    }

}
