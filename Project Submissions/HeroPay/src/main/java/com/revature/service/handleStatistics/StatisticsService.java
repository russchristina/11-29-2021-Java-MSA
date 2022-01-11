package com.revature.service.handleStatistics;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.*;
import com.revature.repository.DTO.EmployeeAccountEntity;
import com.revature.service.handleEmployee.EmployeeService;
import com.revature.service.handleRequest.OrderingService;
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
    private OrderingService orderingService;

    public StatisticsService() {
    }

    public StatisticsService(PendingRequestService pendingRequestService, EmployeeService employeeService, OrderingService orderingService) {
        this.pendingRequestService = pendingRequestService;
        this.employeeService = employeeService;
        this.orderingService = orderingService;
    }

    @Override
    public GeneralStatisticsResponse getGeneralStatistics() {
        GeneralStatisticsResponse generalStatistics = new GeneralStatisticsResponse();

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
        return generalStatistics;
    }

    @Override
    public RankedEmployeeResponse getEmployeeRankedList() {
        dLog.debug("Getting Ranked Employee List");
        RankedEmployeeResponse rankedEmployeeResponse = new RankedEmployeeResponse();
        List<QuickSortEmployee> allEmployees = employeeService.getQuickSort();
        rankedEmployeeResponse.setOrderedList(allEmployees);
        return rankedEmployeeResponse;
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

    @Override
    public SortedEmployee getEmployeeStatistics(int employeeId) {

        dLog.debug("Getting Employee statistics ID: " + employeeId);
        SortedEmployee sortedEmployee = new SortedEmployee();
        EmployeeAccountEntity employeeAccount = employeeService.getEmployeeAccountById(employeeId);
        Employee employee = employeeService.convertEmployeeEntityToEmployee(employeeAccount);

        sortedEmployee.setEmployeeId(employeeId);
        sortedEmployee.setFirstName(employeeAccount.getFirstName());
        sortedEmployee.setLastName(employeeAccount.getLastName());
        sortedEmployee.setRole(employee.getRole());
        List<PendingRequest> allAnsweredRequests = pendingRequestService.getAllAnsweredRequests(employeeId);
        sortedEmployee.setAverage(meanAverage(allAnsweredRequests));
        sortedEmployee.setSum(sumOfAmountCompleted(allAnsweredRequests));

        dLog.debug("Employee Statistics: " + sortedEmployee);

        return sortedEmployee;
    }
}
