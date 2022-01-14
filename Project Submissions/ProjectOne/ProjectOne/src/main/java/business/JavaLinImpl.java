package business;

import Controller.WeenieHutController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class JavaLinImpl {


public static void main(String[]args){
    Javalin app = Javalin.create().start(8800);

//  app._conf.enableCorsForAllOrigins();
  app.before(context ->{
    context.header("Access-Control-Allow-Origin", "*");
    context.header("Access-Control-Allow-Methods: GET, POST, PATCH, PUT, DELETE, OPTIONS");
    context.header("Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token");
  });
    app._conf.addStaticFiles("/static", Location.CLASSPATH);
    WeenieHutController weenieHutController = new WeenieHutController(app);
    weenieHutController.WeenieHutEndpoints();


    }

}
