package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PendingRequestInterface {

    /*
    Create
    Read
    Update
    Delete
     */

    //Create

    PendingRequestEntity insertPendingRequest(int employeeId, int typeId, String requestMessage, double amount, java.sql.Date dateSubmission) throws SQLException;

    RequestTypeEntity insertPendingRequestType(String type) throws SQLException;

    //Read

    PendingRequestEntity getPendingRequest(int requestId) throws SQLException;

    List<PendingRequestEntity> getEmployeePendingRequestList(int employeeId) throws SQLException;

    List<PendingRequestEntity> getAllPendingRequests();

    List<PendingRequestEntity> getAllPendingRequestsByType(int typeId);

    Map<Integer, String> getRequestTypeMap();

    RequestTypeEntity getRequestTypeWithId(int id);

    RequestTypeEntity getRequestTypeWithString(String type);


    //Update

    PendingRequestEntity updatePendingRequestTyoe(int requestId, int typeId);

    PendingRequestEntity updatePendingRequestMessage(int requestId, String requestMessage);

    PendingRequestEntity updatePendingRequestAmount(int requestId, double amount);

    PendingRequestEntity updatePendingRequest(int requestId, int employeeId, int typeId, String requestMessage, double amount, java.sql.Date dateSubmission);


    //Delete

    PendingRequestEntity deletePendingRequest(int requestId);



}
