package Controller.util;

import daolayer.Reimbursements;
import daolayer.UserSpecs;
import io.javalin.http.Handler;
import Driver.serviceUtil.ReimbursementBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JavalinHandler {
    Reimbursements reimbursements;
    UserSpecs specs;
    ReimbursementBuilder builder = new ReimbursementBuilder();
    Logger logins = LoggerFactory.getLogger("LOGINS");
    public final Handler FINDALLRECORDS = ctx -> {
        ctx.json(builder.sortByStatus());
    };

    public final Handler SAVENEWRECORDS = ctx ->{
        reimbursements = ctx.bodyAsClass(Reimbursements.class);
        System.out.println(reimbursements);
        builder.newRequestService(reimbursements);

    };
    public final Handler DELETERECORD = ctx ->{
        reimbursements = ctx.bodyAsClass(Reimbursements.class);
        System.out.println(reimbursements);

        builder.deleteRequestService(reimbursements);
    };
    public final Handler UPDATERECORD = ctx -> {
        reimbursements = ctx.bodyAsClass(Reimbursements.class);
        System.out.println(reimbursements);
        builder.updateRequestService(reimbursements);
    };
    public final Handler VALIDATEUSER = ctx -> {
           specs= ctx.bodyAsClass(UserSpecs.class);
        System.out.println("PRINTING" + specs);
        List<UserSpecs> specsList = builder.validateUserService(specs);
        System.out.println(specsList);
           ctx.json(builder.validateUserService(specs));
//           logins.info("User" + specs.getUserLogin() + "has successfully logged in");
//
//           logins.error("User " + specs.getUserLogin() + " attempted to log in, but the attempt was unsuccessful\n" +
//                   "" + e.getMessage(),e);
//       }

    };
    public final Handler SHOWBYUSER = ctx -> {
        reimbursements = ctx.bodyAsClass(Reimbursements.class);
        List<Reimbursements> requests = builder.requestsByUserService(reimbursements);
        System.out.println(requests);
        ctx.json(requests);
    };
    public final Handler SHOWALLUSERS = ctx -> {
         ctx.json(builder.returnAllUserService());

    };
    public final Handler SHOWSTATS = ctx -> {
//        ctx.json(builder.returnSumService());
//        ctx.jsonStream(builder.returnSumService());
        ctx.res.getWriter().println("Listing each User, Total Amount Spent, and their Total Amount of Requests:\n");
        ctx.res.getWriter().println(builder.returnSumService());
        ctx.res.getWriter().println("\nAverage cost to company: " + builder.returnMeanService());

    };
//    public final Handler FINDBYID = ctx -> {
//
//    }
}
