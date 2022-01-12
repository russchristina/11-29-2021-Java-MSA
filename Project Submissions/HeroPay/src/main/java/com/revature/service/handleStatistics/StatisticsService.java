package com.revature.service.handleStatistics;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.*;
import com.revature.repository.DAOClasses.EmployeeRoleDao;
import com.revature.repository.DAOClasses.RequestTypeDao;
import com.revature.repository.DTO.*;
import com.revature.service.handleEmployee.EmployeeService;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.SortingService;
import com.revature.service.handleRequest.PendingRequestService;
import com.revature.service.handleStatistics.interfaces.StatisticsServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class StatisticsService implements StatisticsServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private PendingRequestService pendingRequestService;
    private EmployeeService employeeService;
    private SortingService sortingService;
    private CompletedRequestService completedRequestService;

    public StatisticsService() {
    }

    public StatisticsService(PendingRequestService pendingRequestService, EmployeeService employeeService, SortingService sortingService, CompletedRequestService completedRequestService) {
        this.pendingRequestService = pendingRequestService;
        this.employeeService = employeeService;
        this.sortingService = sortingService;
        this.completedRequestService = completedRequestService;
    }

    @Override
    public GeneralStatisticsResponse getGeneralStatistics() {
        GeneralStatisticsResponse generalStatistics = new GeneralStatisticsResponse();

        List<PendingRequest> allAnsweredRequests = pendingRequestService.getAnsweredRequests();
        generalStatistics.setTotal(new SortedAll("Total", meanAverage(allAnsweredRequests), sumOfAmountCompleted(allAnsweredRequests)));

        List<SortedType> sortedTypeList = new ArrayList<>();
        List<RequestTypeEntity> requestTypes = pendingRequestService.getRequestTypes();
        for(int i = 0; i < requestTypes.size(); i++){
            dLog.debug("Sorting Request By Type: " + i);
            List<PendingRequest> answeredRequestsByType = pendingRequestService.getAllAnsweredRequestsByType(requestTypes.get(i).getId());
            sortedTypeList.add(
                    new SortedType(
                            requestTypes.get(i).getRequestType(),
                        meanAverage(answeredRequestsByType),
                        sumOfAmountCompleted(answeredRequestsByType)
                    )
            );
        }

        generalStatistics.setSortedTypes(sortedTypeList);
//        List<SortedRole> sortedRoleList = new ArrayList<>();
//        List<EmployeeRoleEntity> employeeRoles = emRoleRoleDao.getAllEmployeeRoles();
//        for(int i = 1; i < employeeRoles.size() + 1; i++){
//            dLog.debug("Sorting Request By Role: " + i);
//            List<PendingRequest> answeredRequestsByRole = pendingRequestService.getAllAnsweredRequestsByRole(employeeRoles.get(i).getId());
//            sortedRoleList.add(
//                    new SortedRole(
//                            employeeRoles.get(i).getRoleName(),
//                            meanAverage(answeredRequestsByRole),
//                            sumOfAmountCompleted(answeredRequestsByRole)
//                    )
//            );
//        }
        dLog.debug("Got all General statistics: " + generalStatistics);
        return generalStatistics;
    }

    @Override
    public RankedEmployeeResponse getEmployeeRankedList() {
        dLog.debug("Getting Ranked Employee List");
        RankedEmployeeResponse rankedEmployeeResponse = new RankedEmployeeResponse();
        List<EmployeeAccountEntity> account = employeeService.getAllEmployeesNotConverted();
        List<CompletedRequestEntity> allEmployeesCompletedRequests = completedRequestService.getAllCompletedRequestsNotConverted();

        HashMap<EmployeeAccountEntity, BigDecimal> employeeSet = new HashMap<>(account.size());
        account.forEach(e -> employeeSet.put(e, BigDecimal.valueOf(0.0)));
        allEmployeesCompletedRequests.forEach(
                e ->  employeeSet.replace(
                        e.getEmployeeAccount(), employeeSet.get(
                                e.getEmployeeAccount()).add(
                                        e.getPendingRequest().getAmount())));

        rankedEmployeeResponse.setOrderedList(sortingService.employeeRankedSort(employeeSet));
        return rankedEmployeeResponse;

 }

    @Override
    public double sumOfAmountCompleted(List<PendingRequest>  completedRequests) {
        dLog.debug("summing amount service" + StatisticsService.class);
        return 0;
    }

    @Override
    public double meanAverage(List<PendingRequest> completedRequests) {
        dLog.debug("mean average service" + StatisticsService.class);
        return 0;
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
        List<PendingRequest> allAnsweredRequests = pendingRequestService.getAllAnsweredRequestsByEmployeeId(employeeId);
        sortedEmployee.setAverage(meanAverage(allAnsweredRequests));
        sortedEmployee.setSum(sumOfAmountCompleted(allAnsweredRequests));

        dLog.debug("Employee Statistics: " + sortedEmployee);

        return sortedEmployee;
    }
}
