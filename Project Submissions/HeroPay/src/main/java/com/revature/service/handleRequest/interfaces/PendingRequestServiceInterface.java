package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.requests.NewRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;

import java.util.List;

public interface PendingRequestServiceInterface {

    PendingRequestEntity storePendingRequest(NewRequest newRequest);

    void validateNewPendingRequest(NewRequest newRequest);

    List<PendingRequest> getAllEmployeePendingRequest(int employeeId);

    List<PendingRequest> getAllPendingRequests();

    List<PendingRequest> getPendingRequestByType(int typeId);

    void updatePendingRequestStatus(int requestId, boolean status);

    void deletePendingRequest(int requestId);

    PendingRequest convertPendingRequestEntity(PendingRequestEntity pendingRequestEntity);

    List<PendingRequest> getAnsweredRequests();

    List<PendingRequest> getAllAnsweredRequestsByEmployeeId(int employeeId);

    List<PendingRequest> getAllAnsweredRequestsByType(int typeId);

    List<RequestTypeEntity> getRequestTypes();

    List<PendingRequest> getAllAnsweredRequestsByRole(int id);
}
