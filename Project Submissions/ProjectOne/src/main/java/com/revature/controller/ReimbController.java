package com.revature.controller;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

import java.util.List;

import com.revature.model.Reimb;

import io.javalin.Javalin;

public class ReimbController {
	private Javalin app;

	public ReimbController(Javalin app) {
		this.app = app;
	}

	public void initEndpoints() {
		// EndpointGroup defined by Javalin is a func interface, we pass a
		// lambda expression to the "routes" method.
		// An endpoint group is a group of endpoints (defined using get(), post(), etc).
		this.app.routes(() -> {
			path("/reimb", () -> {
				path("/all", () -> {
					List<Reimb> reimbs = this.ReimbService.findAll();
					get(ctx -> {
					});
				});
				path("/new", () -> {
					// allow client to add new reimb
				});

			});
		});
	}
}
