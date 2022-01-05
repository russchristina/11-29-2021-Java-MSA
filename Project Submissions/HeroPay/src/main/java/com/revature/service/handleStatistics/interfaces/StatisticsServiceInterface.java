package com.revature.service.handleStatistics.interfaces;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.Employee;
import com.revature.presentation.model.PendingRequest;

import java.util.List;
import java.util.Map;

public interface StatisticsServiceInterface {

    double sumOfAmount(List<CompletedRequest> completedRequests);

    double meanAverage(List<CompletedRequest> completedRequests);

    Map<Integer, Double> totalAmountByEmployee(List<Employee> employees, List<CompletedRequest> completedRequests);

    Map<Employee, Double> mostExpensiveEmployee(List<Employee> employees, List<CompletedRequest> completedRequests)
}
