package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import com.revature.model.Reimbursements_Alli;
import com.revature.model.Reimbursements_Ben;
import com.revature.model.Reimbursements_Sam;
import com.revature.service.EmployeeService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class EmployeeController {

  private Javalin app;
  private EmployeeService employeeService;

  public EmployeeController(Javalin app) {
    this.app = app;
    this.employeeService = new EmployeeService();
  }

  public void initEndpoints() {

    this.app.routes(() -> {

      path("/employee", () -> {

        path("/login", () -> {
          get(findAllEmployeeLogin);
        });

        path("/Alli_submit", () -> {
          post(submitReimbursementAlli);
        });
        path("/Ben_submit", () -> {
          post(submitReimbursementBen);
        });
        path("/Sam_submit", () -> {
          post(submitReimbursementSam);
        });

        path("/findAll/Alli", () -> {
          get(findAllAlli);
        });
        path("/findAll/Ben", () -> {
          get(findAllBen);
        });
        path("/findAll/Sam", () -> {
          get(findAllSam);
        });

      });
    });

  }

  private Handler submitReimbursementAlli = ctx -> {
    // System.out.println("fffff");
    // System.out.println(ctx.body());
    // // // bodyAsClass changes the json, into the toString method which is a java
    // // // object, you could pass in a save method.
    Reimbursements_Alli submitReimbursements = ctx.bodyAsClass(Reimbursements_Alli.class);
    // System.out.println(submitReimbursements);

    employeeService.submitReimbursement(submitReimbursements);

  };

  private Handler findAllAlli = ctx -> {
    // proof of concept
    // System.out.println(ctx.pathParam("name"));
    // Reimbursements retrieveReimbursements =
    // this.employeeService.findReimburseByName();
    // write the retrieved reimbursement to the response body as JSON
    // System.out.println(retrieveReimbursements);
    ctx.json(employeeService.findAllAlli());
  };

  private Handler submitReimbursementBen = ctx -> {
    Reimbursements_Ben submitReimbursements = ctx.bodyAsClass(Reimbursements_Ben.class);
    employeeService.submitReimbursement(submitReimbursements);
  };

  private Handler findAllBen = ctx -> {
    ctx.json(employeeService.findAllBen());
  };

  private Handler submitReimbursementSam = ctx -> {
    Reimbursements_Sam submitReimbursements = ctx.bodyAsClass(Reimbursements_Sam.class);
    employeeService.submitReimbursement(submitReimbursements);
  };

  private Handler findAllSam = ctx -> {
    ctx.json(employeeService.findAllSam());
  };

  private Handler findAllEmployeeLogin = ctx -> {
    ctx.json(employeeService.findAllEmployeeLogin());
  };
}
