package com.revature.service.handleStatistics;

import com.revature.presentation.model.CompletedRequest;
import com.revature.presentation.model.Employee;
import com.revature.service.handleStatistics.interfaces.StatisticsServiceInterface;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class StatisticsService implements StatisticsServiceInterface {
    @Override
    public double sumOfAmountCompleted(Map<Double, CompletedRequest> completedRequests) {
        return 0;
    }

    @Override
    public double meanAverage(Map<Double, CompletedRequest> completedRequests) {
        return 0;
    }

    @Override
    public Map<Integer, Double> totalAmountPerEmployee(List<Employee> employees, Map<Double, CompletedRequest> completedRequests) {
        return null;
    }

    @Override
    public Map<Integer, Double> totalAmountPerRole(List<Employee> employees, Map<Double, CompletedRequest> completedRequests) {
        return null;
    }
}
