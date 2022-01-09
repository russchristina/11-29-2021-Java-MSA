package com.revature.service.handleStatistics;

import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.SortedAll;
import com.revature.presentation.model.statisticsRequests.response.SortedRole;
import com.revature.presentation.model.statisticsRequests.response.SortedType;
import com.revature.presentation.model.statisticsRequests.response.generalStatisticsResponse;
import com.revature.service.handleEmployee.EmployeeService;
import com.revature.service.handleRequest.PendingRequestService;
import com.revature.service.handleStatistics.interfaces.StatisticsServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsService implements StatisticsServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private PendingRequestService pendingRequestService;
    private EmployeeService employeeService;

    public StatisticsService() {
    }

    public StatisticsService(PendingRequestService pendingRequestService, EmployeeService employeeService) {
        this.pendingRequestService = pendingRequestService;
        this.employeeService = employeeService;
    }

    @Override
    public generalStatisticsResponse getGeneralStatistics() {
        generalStatisticsResponse generalStatistics = new generalStatisticsResponse();

        List<PendingRequest> allAnsweredRequests = pendingRequestService.getAnsweredRequests();
        generalStatistics.setTotal(new SortedAll("Total", meanAverage(allAnsweredRequests), sumOfAmountCompleted(allAnsweredRequests)));

        List<SortedType> sortedTypeList = new ArrayList<>();
        Map<Integer, String> pendingRequestMap = pendingRequestService.getRequestMap();

        for(int i = 1; i < pendingRequestMap.size() + 1; i++){
            dLog.debug("Sorting Request By Type: " + i);
            List<PendingRequest> answeredRequestsByType = pendingRequestService.getAllAnsweredRequestsByType(i);

            sortedTypeList.add(
                    new SortedType(
                        pendingRequestMap.get(i),
                        meanAverage(answeredRequestsByType),
                        sumOfAmountCompleted(answeredRequestsByType)
                    )
            );
        }

        generalStatistics.setSortedTypes(sortedTypeList);
        List<SortedRole> sortedRoleList = new ArrayList<>();
        Map<Integer, String> employeeRoleMap = employeeService.getEmployeeRoleMap();

        for(int i = 1; i < employeeRoleMap.size() + 1; i++){
            dLog.debug("Sorting Request By Role: " + i);
            List<PendingRequest> answeredRequestsByRole = pendingRequestService.getAllAnsweredRequestsByRole(i);
            sortedRoleList.add(
                    new SortedRole(
                            employeeRoleMap.get(i),
                            meanAverage(answeredRequestsByRole),
                            sumOfAmountCompleted(answeredRequestsByRole)
                    )
            );
        }

        generalStatistics.setSortedRole(sortedRoleList);
        System.out.println(sortedRoleList);
        return generalStatistics;
    }

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
