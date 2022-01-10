package com.revature.service.handleStatistics.interfaces;

import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.GeneralStatisticsResponse;
import com.revature.presentation.model.statisticsRequests.response.QuickSortEmployee;
import com.revature.presentation.model.statisticsRequests.response.RankedEmployeeResponse;
import com.revature.presentation.model.statisticsRequests.response.SortedEmployee;

import java.util.List;

public interface StatisticsServiceInterface {

    GeneralStatisticsResponse getGeneralStatistics();

    double sumOfAmountCompleted(List<PendingRequest>  completedRequests);

    double meanAverage(List<PendingRequest>   completedRequests);

    SortedEmployee getEmployeeStatistics(int employeeId);

    RankedEmployeeResponse getEmployeeRankedList();


}
