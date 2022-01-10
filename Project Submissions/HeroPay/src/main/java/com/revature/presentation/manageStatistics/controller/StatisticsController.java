package com.revature.presentation.manageStatistics.controller;

import com.revature.service.handleStatistics.StatisticsService;
import io.javalin.http.Handler;

public class StatisticsController {



    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public Handler getGeneralStatistics = ctx -> {
        ctx.json(statisticsService.getGeneralStatistics());
    };

    public Handler getEmployeeGeneralStatistics = ctx -> {
        ctx.json(statisticsService.getEmployeeRankedList());
    };

    public Handler getEmployeeStatistics = ctx -> {
        int employeeId = Integer.parseInt(ctx.queryParam("employeeId"));
        ctx.json(statisticsService.getEmployeeStatistics(employeeId));
    };

}
