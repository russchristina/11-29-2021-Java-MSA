package com.revature.utility;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.markdown.JavalinCommonmark;

public class hello {
	public static void main(String[] args) {

        JavalinRenderer.register(JavalinCommonmark.INSTANCE, "html");

        Javalin app = Javalin.create().start(7000);
        app.get("", ctx -> ctx.render("login.html"));
    }
}
