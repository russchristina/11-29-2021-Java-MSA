package com.revature.service.handleStatistics.interfaces;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.Employee;
import com.revature.presentation.model.PendingRequest;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface StatisticsServiceInterface {

    double sumOfAmountCompleted(List<PendingRequest>  completedRequests);

    double meanAverage(List<PendingRequest>   completedRequests);


}
