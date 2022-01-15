package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.requests.NewRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;

import java.util.List;

public interface PendingRequestServiceInterface {

    PendingRequestEntity storePendingRequest(NewRequest newRequest, boolean fileCheck);

    void validateNewPendingRequest(NewRequest newRequest);

    List<PendingRequest> getAllEmployeePendingRequest(int employeeId);

    List<PendingRequest> getAllPendingRequests();

    void updatePendingRequestStatus(int requestId, boolean status);

    PendingRequest convertPendingRequestEntity(PendingRequestEntity pendingRequestEntity);

    List<PendingRequest> getAnsweredRequests();

    List<PendingRequest> getAllAnsweredRequestsByEmployeeId(int employeeId);

    List<PendingRequest> getAllAnsweredRequestsByType(int typeId);


}
