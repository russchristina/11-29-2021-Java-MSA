package com.revature.service.handleRequest;

import com.revature.presentation.model.requests.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DAOClasses.CompletedRequestDao;
import com.revature.repository.DTO.CompletedRequestEntity;
import com.revature.service.handleRequest.interfaces.CompletedRequestServiceInterface;
import com.revature.service.serviceExceptions.EmployeeIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompletedRequestService implements CompletedRequestServiceInterface {
    private final Logger dLog = LoggerFactory.getLogger("dLog");
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
                    completedRequest.getEmployeeId(),
                    completedRequest.getManagerId(),
                    completedRequest.isStatus(),
                    completedRequest.getResponse(),
                    completedRequest.getDateResolved());
            if(cre != null){
                tLog.info("Completed store completed request in database: " + completedRequest.toString(), CompletedRequestService.class);
                return cre;
            }
        } catch (SQLException e) {
            dLog.error(e.getMessage(), e);
        } finally{
            dLog.debug("Storing Completed Request in Database: " + completedRequest.toString() , CompletedRequestService.class);
        }
        dLog.debug("Store Completed Request Failure");
        return null;
    }

    @Override
    public CompletedRequest convertCompletedRequestEntity(CompletedRequestEntity completedRequestEntity) {
        return new CompletedRequest(
                completedRequestEntity.getId(),
                completedRequestEntity.getEmployeeId(),
                completedRequestEntity.getManagerId(),
                completedRequestEntity.isStatus(),
                completedRequestEntity.getResponse(),
                completedRequestEntity.getDateResolved().toLocalDate()
        );
    }

    @Override
    public boolean validateCompletedRequest(CompletedRequest completedRequest) {
        if(completedRequest.getManagerId() <= 0) throw new EmployeeIdException("Invalid Manager ID, <= 0");
        dLog.debug("Completed request validated");
        return true;
    }

    @Override
    public List<CompletedRequest> getAllCompletedRequests() {

        try {
            List<CompletedRequestEntity> completedRequestEntityList = completedRequestDao.getAllCompletedRequestList();
            List<CompletedRequest> completedRequestList = new ArrayList<>();

            completedRequestEntityList.forEach(e ->
                    completedRequestList.add(
                            new CompletedRequest(e.getId(), e.getEmployeeId(), e.getManagerId(),e.isStatus(), e.getResponse(), e.getDateResolved().toLocalDate())
                    ));
            return completedRequestList;
        } catch (SQLException e) {
            dLog.error(e.getMessage(), e);
        } finally{
            dLog.debug("Getting all Completed Requests");
        }
        dLog.debug("Failed to get all Completed Requests");
        return null;
    }

    @Override
    public List<CompletedRequest> getAllCompletedRequestsByManagerId(int managerId) {
        try {
            List<CompletedRequestEntity> completedRequestEntityList = completedRequestDao.getCompletedRequestByManagerIdList(managerId);
            List<CompletedRequest> completedRequestList = new ArrayList<>();

            completedRequestEntityList.forEach(e ->
                    completedRequestList.add(
                            new CompletedRequest(e.getId(),e.getEmployeeId(), e.getManagerId(),e.isStatus(), e.getResponse(), e.getDateResolved().toLocalDate())
                    ));
            return completedRequestList;
        } catch (SQLException e) {
            dLog.error(e.getMessage(), e);
        } finally{
            dLog.debug("Getting all Completed Requests by Manager Id: " + managerId);
        }
        dLog.debug("Failed to get all Completed Requests by Manager-" + managerId);
        return null;
    }

    @Override
    public List<CompletedRequest> getAllCompletedRequestsByStatus(boolean status) {

        try {
            List<CompletedRequestEntity> completedRequestEntityList = completedRequestDao.getCompletedRequestByStatus(status);
            List<CompletedRequest> completedRequestList = new ArrayList<>();

            completedRequestEntityList.forEach(e ->
                    completedRequestList.add(
                            new CompletedRequest(e.getId(),e.getEmployeeId(), e.getManagerId(),e.isStatus(), e.getResponse(), e.getDateResolved().toLocalDate())
                    ));
            return completedRequestList;
        } catch (SQLException e) {
            dLog.error(e.getMessage(), e);
        } finally{
            dLog.debug("Getting all Completed Requests by status: " + status);
        }
        dLog.debug("Failed to get all Completed Requests by Status-" + status);
        return null;
    }

    @Override
    public CompletedRequestEntity deleteCompletedRequest(int requestId) {
        try {
            return completedRequestDao.deleteCompletedRequest(requestId);
        } catch (SQLException e) {
            dLog.error(e.getMessage(), e);
        } finally{
            dLog.debug("Deleting Completed Request-" + requestId);
        }
        dLog.debug("Failed to delete completed requests-" + requestId);
        return null;
    }

    @Override
    public CompletedRequest convertPendingRequest(PendingRequest pendingRequest, int managerId, boolean status, String response) {
        return convertCompletedRequestEntity(storeCompletedRequest(new CompletedRequest(pendingRequest.getId(), pendingRequest.getEmployeeId(), managerId, status, response, LocalDate.now())));
    }

    @Override
    public List<CompletedRequest> getAllEmployeeRequests(int employeeId) {
        List<CompletedRequest> completedRequestList = new ArrayList<>();
        try {
            List<CompletedRequestEntity> completedRequestEntityList = completedRequestDao.getCompletedRequestByEmployeeId(employeeId);

            completedRequestEntityList.forEach(e ->
                    completedRequestList.add(
                            new CompletedRequest(e.getId(), e.getEmployeeId(), e.getManagerId(), e.isStatus(), e.getResponse(), e.getDateResolved().toLocalDate())
                    ));
            return completedRequestList;

        } catch(NullPointerException | SQLException e){
            dLog.debug("No Completed Requests for employee: " + employeeId);
            return completedRequestList;
         }finally{
            dLog.debug("Getting all Completed Requests by employeeId: " + employeeId);
        }
    }

}
