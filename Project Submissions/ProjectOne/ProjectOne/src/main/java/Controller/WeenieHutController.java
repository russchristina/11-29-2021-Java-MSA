package Controller;

import Controller.util.JavalinHandler;
import daolayer.DAOQueries;
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
    JavalinHandler handle = new JavalinHandler();
   private Javalin app;
Logger exceptions = LoggerFactory.getLogger("EXCEPTIONS");
    public WeenieHutController(Javalin app) {
        this.app = app;

    }

    public void WeenieHutEndpoints() {

        this.app.routes(() -> {
            path("/P1LandingPage", () -> {
               get(StaticHTMLController.loadLandingPage);
            });
        path("/reimbursements", () -> {
            path("/validate", () -> {
               post(handle.VALIDATEUSER);
            });
            path("/all", () ->{
                get(handle.SHOWALLUSERS);
            });
            path("new", () -> {
            post(handle.SAVENEWRECORDS);
            });
            path("/manager/all", ()-> {
                get(handle.FINDALLRECORDS);
            });
            path("delete", () ->{
            delete(handle.DELETERECORD);
            });
            path("manager/update",() ->{
            put(handle.UPDATERECORD);
            });
            path("/show", () ->{
                post(handle.SHOWBYUSER);
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

}
