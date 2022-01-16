package com.revature.presentation.manageStatistics.controller;

import com.revature.service.handleStatistics.StatisticsService;
import com.revature.utility.JWTHandler;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatisticsController {

    private StatisticsService statisticsService;
    private final Logger dLog = LoggerFactory.getLogger("dLog");
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public final Handler getGeneralStatistics = ctx -> {
        if(JWTHandler.verifyAdmin(ctx.header("Authorization"))){
            dLog.debug("Admin Authorized");
            dLog.debug("Getting general statistics.");
            ctx.json(statisticsService.getGeneralStatistics());
        }else{
            dLog.debug("Unauthorized Admin Attempt");
            ctx.status(401);
        }
    };

    public final Handler getEmployeeGeneralStatistics = ctx -> {
        if(JWTHandler.verifyAdmin(ctx.header("Authorization"))){
            dLog.debug("Admin Authorized");
            ctx.json(statisticsService.getEmployeeRankedList());
        }else{
            dLog.debug("Unauthorized Admin Attempt");
            ctx.status(401);
        }
    };

    public final Handler getEmployeeStatistics = ctx -> {
        if(JWTHandler.verifyAdmin(ctx.header("Authorization"))){
            dLog.debug("Admin Authorized");
            int employeeId = Integer.parseInt(ctx.queryParam("employeeId"));
            ctx.json(statisticsService.getEmployeeStatistics(employeeId));
        }else{
            dLog.debug("Unauthorized Admin Attempt");
            ctx.status(401);
        }
    };

}
