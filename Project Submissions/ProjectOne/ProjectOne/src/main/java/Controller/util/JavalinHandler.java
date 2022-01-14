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
        System.out.println("PRINTED HERE" + specs);
        System.out.println(specs.getUserLogin());
        System.out.println(specs.getUserPass());
        ctx.json(builder.validateUserService(specs));

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
//    public final Handler FINDBYID = ctx -> {
//
//    }
}
