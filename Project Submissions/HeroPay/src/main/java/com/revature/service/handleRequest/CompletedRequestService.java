package com.revature.service.handleRequest;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.PendingRequest;
import com.revature.repository.DAOClasses.CompletedRequestDao;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.service.handleRequest.interfaces.CompletedRequestServiceInterface;
import com.revature.service.serviceExceptions.EmployeeIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompletedRequestService implements CompletedRequestServiceInterface {
    private final Logger logger = LoggerFactory.getLogger(CompletedRequestService.class);
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private CompletedRequestDao completedRequestDao;

    public CompletedRequestService(CompletedRequestDao completedRequestDao) {
        this.completedRequestDao = completedRequestDao;
    }

    @Override
    public CompletedRequestEntity storeCompletedRequest(CompletedRequest completedRequest) {
        try {
            CompletedRequestEntity cre = completedRequestDao.insertCompletedRequest(
                    completedRequest.getId(),
                    completedRequest.getManagerId(),
                    completedRequest.isStatus(),
                    completedRequest.getResponse(), completedRequest.getDateResolved());
            if(cre != null){
                tLog.info("Completed store completed request in database: " + completedRequest.toString(), CompletedRequestService.class);
                return cre;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally{
            logger.debug("Storing Completed Request in Database: " + completedRequest.toString() , CompletedRequestService.class);
        }
        logger.debug("Store Completed Request Failure");
        return null;
    }

    @Override
    public CompletedRequest convertCompletedRequestEntity(CompletedRequestEntity completedRequestEntity) {
        return new CompletedRequest(
                completedRequestEntity.getId(),
                completedRequestEntity.getManagerId(),
                completedRequestEntity.isStatus(),
                completedRequestEntity.getResponse(),
                completedRequestEntity.getDateResolved().toLocalDate()
        );
    }

    @Override
    public boolean validateCompletedRequest(CompletedRequest completedRequest) {
        if(completedRequest.getManagerId() <= 0) throw new EmployeeIdException("Invalid Manager ID, <= 0");
        logger.debug("Completed request validated");
        return true;
    }

    @Override
    public List<CompletedRequest> getAllCompletedRequests() {

        try {
            List<CompletedRequestEntity> completedRequestEntityList = completedRequestDao.getAllCompletedRequestList();
            List<CompletedRequest> completedRequestList = new ArrayList<>();

            completedRequestEntityList.forEach(e ->
                    completedRequestList.add(
                            new CompletedRequest(e.getId(),e.getManagerId(),e.isStatus(), e.getResponse(), e.getDateResolved().toLocalDate())
                    ));
            return completedRequestList;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally{
            logger.debug("Getting all Completed Requests");
        }
        logger.debug("Failed to get all Completed Requests");
        return null;
    }

    @Override
    public List<CompletedRequest> getAllCompletedRequestsByManagerId(int managerId) {
        try {
            List<CompletedRequestEntity> completedRequestEntityList = completedRequestDao.getCompletedRequestByManagerIdList(managerId);
            List<CompletedRequest> completedRequestList = new ArrayList<>();

            completedRequestEntityList.forEach(e ->
                    completedRequestList.add(
                            new CompletedRequest(e.getId(),e.getManagerId(),e.isStatus(), e.getResponse(), e.getDateResolved().toLocalDate())
                    ));
            return completedRequestList;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally{
            logger.debug("Getting all Completed Requests by Manager Id: " + managerId);
        }
        logger.debug("Failed to get all Completed Requests by Manager-" + managerId);
        return null;
    }

    @Override
    public List<CompletedRequest> getAllCompletedRequestsByStatus(boolean status) {

        try {
            List<CompletedRequestEntity> completedRequestEntityList = completedRequestDao.getCompletedRequestByStatus(status);
            List<CompletedRequest> completedRequestList = new ArrayList<>();

            completedRequestEntityList.forEach(e ->
                    completedRequestList.add(
                            new CompletedRequest(e.getId(),e.getManagerId(),e.isStatus(), e.getResponse(), e.getDateResolved().toLocalDate())
                    ));
            return completedRequestList;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally{
            logger.debug("Getting all Completed Requests by status: " + status);
        }
        logger.debug("Failed to get all Completed Requests by Status-" + status);
        return null;
    }

    @Override
    public CompletedRequestEntity deleteCompletedRequest(int requestId) {
        try {
            return completedRequestDao.deleteCompletedRequest(requestId);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally{
            logger.debug("Deleting Completed Request-" + requestId);
        }
        logger.debug("Failed to delete completed requests-" + requestId);
        return null;
    }

    @Override
    public CompletedRequest convertPendingRequest(PendingRequest pendingRequest, int managerId, boolean status, String response) {
        return convertCompletedRequestEntity(storeCompletedRequest(new CompletedRequest(pendingRequest.getId(), managerId, status, response, LocalDate.now())));
    }

}
