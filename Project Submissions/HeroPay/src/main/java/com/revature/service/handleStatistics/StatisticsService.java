package com.revature.service.handleStatistics;

import com.revature.presentation.model.requests.PendingRequest;
import com.revature.service.handleStatistics.interfaces.StatisticsServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StatisticsService implements StatisticsServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    @Override
    public double sumOfAmountCompleted(List<PendingRequest>  completedRequests) {
        dLog.debug("summing amount service" + StatisticsService.class);
        return Math.round(completedRequests.stream().mapToDouble(PendingRequest::getAmount).sum() * 100.0) / 100.0;
    }

    @Override
    public double meanAverage(List<PendingRequest> completedRequests) {
        dLog.debug("mean average service" + StatisticsService.class);
        return Math.round(completedRequests.stream().mapToDouble(PendingRequest::getAmount).sum()/completedRequests.size() * 100.0) / 100.0;
    }
}
