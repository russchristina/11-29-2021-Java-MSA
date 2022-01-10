package Controller.util;

import Controller.util.models.LoginReceived;
import daolayer.Reimbursements;
import daolayer.UserSpecs;
import io.javalin.http.Handler;
import serviceUtil.ReimbursementBuilder;

import java.util.List;

public class JavalinHandler {
    Reimbursements reimbursements;
    UserSpecs specs;
    ReimbursementBuilder builder = new ReimbursementBuilder();
    public final Handler FINDALLRECORDS = ctx -> {
        ctx.json(builder.sortByStatus());
    };

    public final Handler SAVENEWRECORDS = ctx ->{
        reimbursements = ctx.bodyAsClass(Reimbursements.class);
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
        UserSpecs userSpecs= ctx.bodyAsClass(UserSpecs.class);
        UserSpecs test = builder.validateUser(userSpecs);
        System.out.println(test);
        ctx.json(builder.validateUser(userSpecs));

    };
    public final Handler SHOWBYUSER = ctx -> {
        reimbursements = ctx.bodyAsClass(Reimbursements.class);
        List<Reimbursements> requests = builder.requestsByUserService(reimbursements);
        System.out.println(requests);
        ctx.json(requests);
    };
//    public final Handler FINDBYID = ctx -> {
//
//    }
}
