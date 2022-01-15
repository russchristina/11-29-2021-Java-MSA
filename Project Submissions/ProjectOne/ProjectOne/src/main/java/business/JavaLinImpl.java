package business;

import Controller.WeenieHutController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import io.javalin.http.staticfiles.Location;

public class JavaLinImpl {


public static void main(String[]args){
    Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(8800);

//  app._conf.enableCorsForAllOrigins();

    app._conf.addStaticFiles("/static", Location.CLASSPATH);
    WeenieHutController weenieHutController = new WeenieHutController(app);
    weenieHutController.WeenieHutEndpoints();

    }

}
