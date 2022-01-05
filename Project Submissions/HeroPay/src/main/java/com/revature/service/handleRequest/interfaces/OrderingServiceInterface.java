package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.PendingRequest;
import com.revature.repository.DTO.CompletedRequestEntity;

import java.sql.SQLException;
import java.util.List;

public interface OrderingServiceInterface {

    List<PendingRequest> orderByDatePending(List<PendingRequest> pendingRequests);

    List<CompletedRequest> orderByDateCompleted(List<CompletedRequest> completedRequests);

    List<PendingRequest> orderByAmountPending(List<PendingRequest> pendingRequests);

    List<CompletedRequest> orderByAmountCompleted(List<CompletedRequest> completedRequestsTrue) throws SQLException;

}
