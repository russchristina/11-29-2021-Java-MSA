package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DTO.PendingRequestEntity;

import java.util.List;

public interface PendingRequestServiceInterface {

    PendingRequestEntity storePendingRequest(PendingRequest pendingRequest);

    boolean validateNewPendingRequest(PendingRequest pendingRequest);

    List<PendingRequest> getAllEmployeePendingRequest(int employeeId);

    List<PendingRequest> getAllPendingRequests();

    List<PendingRequest> getPendingRequestByType(int typeId);

    PendingRequestEntity updatePendingRequestStatus(int requestId, boolean status);

    PendingRequestEntity deletePendingRequest(int requestId);

    PendingRequest convertPendingRequestEntity(PendingRequestEntity pendingRequestEntity);

    List<PendingRequest> getAnsweredRequests();

    List<PendingRequest> getAllAnsweredRequests(int employeeId);

    List<PendingRequest> getAllAnsweredRequestsByType(int typeId);

    List<PendingRequest> getAllAnsweredRequestsByRole(int roleId);
}
