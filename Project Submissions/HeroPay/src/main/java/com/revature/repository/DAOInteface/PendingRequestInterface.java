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

    List<PendingRequestEntity> getAllPendingRequests() throws SQLException;

    List<PendingRequestEntity> getAllPendingRequestsByType(int typeId) throws SQLException;

    Map<Integer, String> getRequestTypeMap() throws SQLException;

    RequestTypeEntity getRequestTypeWithString(String type) throws SQLException;

    List<PendingRequestEntity> getAnsweredRequests() throws SQLException;

    List<PendingRequestEntity> getEmployeeAnsweredRequests(int employeeId) throws SQLException;


    //Update

    PendingRequestEntity updatePendingRequestStatus(int requestId, boolean status) throws SQLException;

    PendingRequestEntity updatePendingRequestType(int requestId, int typeId) throws SQLException;

    PendingRequestEntity updatePendingRequestMessage(int requestId, String requestMessage) throws SQLException;

    PendingRequestEntity updatePendingRequestAmount(int requestId, double amount) throws SQLException;


    //Delete

    PendingRequestEntity deletePendingRequest(int requestId) throws SQLException;



}
