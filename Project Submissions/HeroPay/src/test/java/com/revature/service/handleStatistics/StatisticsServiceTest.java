//package com.revature.service.handleStatistics;
//
//import com.revature.presentation.model.requests.PendingRequest;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class StatisticsServiceTest {
//
//    private List<PendingRequest> completedRequests;
//    private StatisticsService statisticsService;
//
//    @BeforeAll
//    void setUp(){
//        completedRequests = new ArrayList<>();
//        PendingRequest cr1 = new PendingRequest(1, 1, "hello", "oh dm", 20.3, LocalDate.of(2990, 1, 1));
//        PendingRequest cr2 = new PendingRequest(2, 1, "hello", "oh dm", 20.3, LocalDate.of(2990, 1, 1));
//        PendingRequest cr3 = new PendingRequest(3, 1, "hello", "oh dm", 20.3, LocalDate.of(2990, 1, 1));
//        completedRequests.add(cr1);
//        completedRequests.add(cr2);
//        completedRequests.add(cr3);
//
//        statisticsService = new StatisticsService();
//    }
//
//    @Test
//    void sumOfAmountCompleted() {
//        assertEquals(60.9, statisticsService.sumOfAmountCompleted(completedRequests));
//    }
//
//    @Test
//    void meanAverage() {
//        assertEquals(20.3, statisticsService.meanAverage(completedRequests));
//    }
//}