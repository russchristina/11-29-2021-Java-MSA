package com.revature.utility;

import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(9002);
        ServerStartup server = new ServerStartup(app);
        server.configureServer();

    }

}
