package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.requests.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.SortedMap;

public interface OrderingServiceInterface {

    List<PendingRequest> orderByDatePending(List<PendingRequest> pendingRequests);

    List<CompletedRequest> orderByDateCompleted(List<CompletedRequest> completedRequests);

    List<PendingRequest> orderByAmountPending(List<PendingRequest> pendingRequests);

    SortedMap<Double, CompletedRequest> orderByAmountCompleted(List<CompletedRequest> completedRequestsTrue) throws SQLException;

}
