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

    PendingRequestEntity insertPendingRequest(PendingRequestEntity pendingRequestEntity);

    //Read

    PendingRequestEntity getPendingRequest(int requestId);

    List<PendingRequestEntity> getEmployeePendingRequestList(int employeeId);

    List<PendingRequestEntity> getAllPendingRequests();

    List<PendingRequestEntity> getAllPendingRequestsByTypeId(int typeId);

    Map<Integer, String> getRequestTypeMap();


    List<PendingRequestEntity> getAnsweredRequests();

    List<PendingRequestEntity> getEmployeeAnsweredRequests(int employeeId);

    List<PendingRequestEntity> getEmployeeAnsweredRequestsByTypeId(int typeId);


    //Update

    PendingRequestEntity updatePendingRequestStatus(PendingRequestEntity pendingRequestEntity);

    PendingRequestEntity updatePendingRequestMessage(PendingRequestEntity pendingRequestEntity);

    PendingRequestEntity updatePendingRequestAmount(PendingRequestEntity pendingRequestEntity);


    //Delete

    PendingRequestEntity deletePendingRequest(PendingRequestEntity pendingRequestEntity);



}
