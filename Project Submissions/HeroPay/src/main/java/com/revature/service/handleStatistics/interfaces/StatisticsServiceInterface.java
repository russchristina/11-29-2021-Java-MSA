package com.revature.service.handleStatistics.interfaces;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface StatisticsServiceInterface {

    double sumOfAmountCompleted(Map<Double, CompletedRequest>  completedRequests);

    double meanAverage(Map<Double, CompletedRequest>  completedRequests);

    Map<Integer, Double> totalAmountPerEmployee(List<Employee> employees, Map<Double, CompletedRequest>  completedRequests);

    Map<Integer, Double> totalAmountPerRole(List<Employee> employees, Map<Double, CompletedRequest> completedRequests);

}
