package com.revature.service.handleStatistics.interfaces;

import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.GeneralStatisticsResponse;
import com.revature.presentation.model.statisticsRequests.response.RankedEmployeeResponse;
import com.revature.presentation.model.statisticsRequests.response.SortedEmployee;

import java.math.BigDecimal;
import java.util.List;

public interface StatisticsServiceInterface {

    GeneralStatisticsResponse getGeneralStatistics();

    BigDecimal sumOfAmountCompleted(List<PendingRequest>  completedRequests);

    BigDecimal meanAverage(List<PendingRequest>   completedRequests);

    SortedEmployee getEmployeeStatistics(int employeeId);

    RankedEmployeeResponse getEmployeeRankedList();


}
