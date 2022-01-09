package com.revature.service.handleStatistics.interfaces;

import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.generalStatisticsResponse;

import java.util.List;

public interface StatisticsServiceInterface {

    generalStatisticsResponse getGeneralStatistics();

    double sumOfAmountCompleted(List<PendingRequest>  completedRequests);

    double meanAverage(List<PendingRequest>   completedRequests);


}
