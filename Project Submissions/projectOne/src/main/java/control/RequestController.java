package control;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;

public class RequestController {
	
	private Javalin app;
	private RequestHandlers requestHandler;
	
	public RequestController(Javalin app) {
		this.app = app;
		this.requestHandler = new RequestHandlers();
	}
	
	public void initEndpoints() {
		
		this.app.routes(() -> {
			path("/empPortal", () -> {
			path("/request", () ->{
				path("/all", () -> {
					get(this.requestHandler.viewAll);
				});
				path("/new", () -> {
					post(this.requestHandler.submitNew);
				});
				path("/id/{id}", () -> {
					get(this.requestHandler.findById);
				});
				path("/status/{status}", () -> {
					get(this.requestHandler.findByStatus);
				});
				path("/pending", () ->{
					get(this.requestHandler.viewAllPending);
				});
				path("/complete", () -> {
					get(this.requestHandler.viewAllComplete);
				});
								
			});
		});
		});
	}

}
