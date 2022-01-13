package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.EmployeeAccountEntity;
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

    Integer insertPendingRequest(PendingRequestEntity pendingRequestEntity);

    //Read

    PendingRequestEntity getPendingRequestByRequestId(int requestId);

    List<PendingRequestEntity> getEmployeesPendingRequestList(EmployeeAccountEntity employee);

    List<PendingRequestEntity> getAllPendingRequests();

//    List<PendingRequestEntity> getAllPendingRequestsByTypeId(int typeId);

//    Map<Integer, String> getRequestTypeMap();


//    List<PendingRequestEntity> getRequestsByStatus();

//    List<PendingRequestEntity> getEmployeeAnsweredRequests(int employeeId);

//    List<PendingRequestEntity> getEmployeeAnsweredRequestsByTypeId(int typeId);


    //Update

    void updatePendingRequest(PendingRequestEntity pendingRequestEntity);

    //Delete

    void deletePendingRequest(PendingRequestEntity pendingRequestEntity);


    List<PendingRequestEntity> getPendingRequestsByTypeId(RequestTypeEntity requestType);

    List<PendingRequestEntity> getPendingRequestByStatus(boolean status);

    List<PendingRequestEntity> getAnsweredEmployeePendingRequests(EmployeeAccountEntity employee);

    List<PendingRequestEntity> getAnsweredPendingRequestsByType(RequestTypeEntity requestType);
}
