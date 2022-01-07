package business;

import Controller.WeenieHutController;
import daolayer.DAOQueries;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class JavaLinImpl {


public static void main(String[]args){
    Javalin app = Javalin.create().start(8800);
    app._conf.addStaticFiles("/static", Location.CLASSPATH);
    WeenieHutController weenieHutController = new WeenieHutController(app);
    weenieHutController.WeenieHutEndpoints();


    }

}
