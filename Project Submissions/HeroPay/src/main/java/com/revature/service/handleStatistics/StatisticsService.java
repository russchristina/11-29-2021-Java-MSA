package com.revature.service.handleStatistics;

import com.revature.presentation.model.employeeRequests.Employee;
import com.revature.presentation.model.requests.PendingRequest;
import com.revature.presentation.model.statisticsRequests.response.*;
import com.revature.repository.DTO.*;
import com.revature.service.DTO.RolesToEmployee;
import com.revature.service.handleEmployee.EmployeeService;
import com.revature.service.handleEmployee.RoleService;
import com.revature.service.handleRequest.CompletedRequestService;
import com.revature.service.handleRequest.SortingService;
import com.revature.service.handleRequest.PendingRequestService;
import com.revature.service.handleStatistics.interfaces.StatisticsServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class StatisticsService implements StatisticsServiceInterface {

    private final Logger dLog = LoggerFactory.getLogger("dLog");

    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private PendingRequestService pendingRequestService;
    private EmployeeService employeeService;
    private RoleService roleService;
    private SortingService sortingService;
    private CompletedRequestService completedRequestService;

    public StatisticsService() {
    }

    public StatisticsService(PendingRequestService pendingRequestService, EmployeeService employeeService, RoleService roleService, SortingService sortingService, CompletedRequestService completedRequestService) {
        this.pendingRequestService = pendingRequestService;
        this.employeeService = employeeService;
        this.roleService = roleService;
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
        List<SortedRole> sortedRoleList = new ArrayList<>();
        List<EmployeeRoleEntity> employeeRoles = roleService.getAllEmployeeRoles();
        List<RolesToEmployee> rolesToEmployees = getSortedEmployeeRoles(employeeRoles);
        for(int i = 0; i < rolesToEmployees.size(); i++){
            dLog.debug("Sorting Request By Role: " + i);
            List<EmployeeAccountEntity> employeeAccountEntities = rolesToEmployees.get(i).getEmployees();
            SortedRole sortedRole = new SortedRole();
            sortedRole.setRoleName(roleService.getEmployeeRole(rolesToEmployees.get(i).getRoleId()).getRoleName());
            sortedRole.setMeanAverage(BigDecimal.ZERO);
            sortedRole.setSum(BigDecimal.ZERO);
            for (EmployeeAccountEntity employeeAccountEntity : employeeAccountEntities) {
                SortedEmployee sortedEmployee = getEmployeeStatistics(employeeAccountEntity.getId());
                sortedRole.setSum(sortedRole.getSum().add(sortedEmployee.getSum(), new MathContext(2)));
            }
            if(employeeAccountEntities.size() != 0) sortedRole.setMeanAverage(sortedRole.getSum().divide(BigDecimal.valueOf(employeeAccountEntities.size()), new MathContext(2)));
            else sortedRole.setSum(BigDecimal.ZERO);
            sortedRoleList.add(sortedRole);
        }
        generalStatistics.setSortedRoles(sortedRoleList);
        dLog.debug("Got all General statistics: " + generalStatistics);
        return generalStatistics;
    }

    private List<RolesToEmployee> getSortedEmployeeRoles(List<EmployeeRoleEntity> employeeRoles) {
        List<RolesToEmployee> rolesToEmployees = new ArrayList<>(employeeRoles.size());
        employeeRoles.forEach(e -> rolesToEmployees.add(new RolesToEmployee(e.getId(), employeeService.getAllEmployeesByRole(e.getId()))));
        return rolesToEmployees;
    }

    @Override
    public RankedEmployeeResponse getEmployeeRankedList() {
        dLog.debug("Getting Ranked Employee List");
        RankedEmployeeResponse rankedEmployeeResponse = new RankedEmployeeResponse();
        List<EmployeeAccountEntity> account = employeeService.getAllEmployeesAccountEntities();
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
    public BigDecimal sumOfAmountCompleted(List<PendingRequest>  completedRequests) {
        dLog.debug("summing amount service" + StatisticsService.class);
        BigDecimal result = BigDecimal.ZERO;
        for (PendingRequest completedRequest : completedRequests) {
            result = result.add(completedRequest.getAmount(), new MathContext(2));
        }
        return result;
    }

    @Override
    public BigDecimal meanAverage(List<PendingRequest> completedRequests) {
        dLog.debug("mean average service" + StatisticsService.class);
        BigDecimal result = BigDecimal.ZERO;
        for (PendingRequest completedRequest : completedRequests) {
            result = result.add(completedRequest.getAmount(), new MathContext(2));
        }
        if(completedRequests.size()!= 0) result = result.divide(BigDecimal.valueOf(completedRequests.size()), new MathContext(2));
        return result;
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
