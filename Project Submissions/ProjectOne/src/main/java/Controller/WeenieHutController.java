package Controller;

import daolayer.Reimbursements;
import io.javalin.Javalin;

import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serviceUtil.ReimbursementBuilder;

import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;
public class WeenieHutController {
ReimbursementBuilder builder = new ReimbursementBuilder();
   private Javalin app;
Logger exceptions = LoggerFactory.getLogger("EXCEPTIONS");
    public WeenieHutController(Javalin app) {
        this.app = app;

    }

    public void WeenieHutEndpoints() {
        this.app.routes(() -> {
        path("/reimbursements", () -> {
            path("new", () -> {
            post(saveNewRequests);
            exceptions.debug("Borked it");
            });
            path("all", ()-> {
                get(findallRequests);
            });

        });

        });
        //        this.app.get("/reimbursements/all", ctx ->{
//        });
//
//        this.app.post("reimbursements/new", ctx ->{
//
//        });
//    }
//

    }
    private Handler findallRequests = ctx -> {
    ctx.json(builder.compareByStatus());
    };

    private Handler saveNewRequests = ctx ->{
        System.out.println(ctx.body());
        Reimbursements reimbursements = ctx.bodyAsClass(Reimbursements.class);
//        System.out.println(reimbursements);
    };
}
