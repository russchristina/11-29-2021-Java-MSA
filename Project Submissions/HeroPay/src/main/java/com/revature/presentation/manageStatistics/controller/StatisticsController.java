package com.revature.presentation.manageStatistics.controller;

import com.revature.service.handleStatistics.StatisticsService;
import io.javalin.http.Handler;

public class StatisticsController {



    private StatisticsService statisticsService = null;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public final Handler getGeneralStatistics = ctx -> {
        ctx.json(statisticsService.getGeneralStatistics());
    };

    public final Handler getEmployeeGeneralStatistics = ctx -> {
        ctx.json(statisticsService.getEmployeeRankedList());
    };

    public final Handler getEmployeeStatistics = ctx -> {
        int employeeId = Integer.parseInt(ctx.queryParam("employeeId"));
        ctx.json(statisticsService.getEmployeeStatistics(employeeId));
    };

}
