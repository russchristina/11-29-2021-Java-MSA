package com.revature.service.handleStatistics.interfaces;

import com.revature.presentation.model.requests.PendingRequest;

import java.util.List;

public interface StatisticsServiceInterface {

    double sumOfAmountCompleted(List<PendingRequest>  completedRequests);

    double meanAverage(List<PendingRequest>   completedRequests);


}
