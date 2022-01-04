package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.PendingRequest;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;

import java.util.List;

public interface PendingRequestServiceInterface {

    PendingRequestEntity storePendingRequest(PendingRequest pendingRequest);

    boolean validateNewPendingRequest(PendingRequest pendingRequest);

    List<PendingRequest> getAllEmployeePendingRequest(int employeeId);

    List<PendingRequest> getAllPendingRequests();

    List<PendingRequest> getPendingRequestByType(int typeId);

    PendingRequestEntity deletePendingRequest(int requestId);

}
