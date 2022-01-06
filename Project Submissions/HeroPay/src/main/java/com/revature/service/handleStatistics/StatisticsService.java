package com.revature.service.handleStatistics;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.Employee;
import com.revature.presentation.model.PendingRequest;
import com.revature.service.handleStatistics.interfaces.StatisticsServiceInterface;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class StatisticsService implements StatisticsServiceInterface {
    @Override
    public double sumOfAmountCompleted(List<PendingRequest>  completedRequests) {
        return Math.round(completedRequests.stream().mapToDouble(PendingRequest::getAmount).sum() * 100.0) / 100.0;
    }

    @Override
    public double meanAverage(List<PendingRequest> completedRequests) {
        return Math.round(completedRequests.stream().mapToDouble(PendingRequest::getAmount).sum()/completedRequests.size() * 100.0) / 100.0;
    }
}
