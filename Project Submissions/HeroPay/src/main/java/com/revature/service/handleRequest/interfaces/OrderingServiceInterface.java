package com.revature.service.handleRequest.interfaces;

import com.revature.presentation.model.requests.recieve.CompletedRequest;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
import com.revature.presentation.model.statisticsRequests.response.RankedEmployeeResponse;
import com.revature.presentation.model.statisticsRequests.response.SortedEmployee;

import java.sql.SQLException;
import java.util.List;
import java.util.SortedMap;

public interface OrderingServiceInterface {

    List<PendingRequest> orderByDatePending(List<PendingRequest> pendingRequests);

    List<CompletedRequest> orderByDateCompleted(List<CompletedRequest> completedRequests);

    List<PendingRequest> orderByAmountPending(List<PendingRequest> pendingRequests);

    SortedMap<Double, CompletedRequest> orderByAmountCompleted(List<CompletedRequest> completedRequestsTrue) throws SQLException;

    List<QuickSortEmployee> orderSortedEmployee(List<QuickSortEmployee> sortedEmployees);
}
