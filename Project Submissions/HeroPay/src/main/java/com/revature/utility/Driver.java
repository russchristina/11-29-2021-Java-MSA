package com.revature.utility;

import com.revature.repository.utility.HibernateSessionFactory;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

public class Driver {

    public static void main(String[] args) {
        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(9002);
        ServerStartup server = new ServerStartup(app);
        server.configureServer();
        HibernateSessionFactory.getSession();

    }

}
