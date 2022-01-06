package com.revature;

import java.util.Enumeration;

import io.javalin.Javalin;

public class HelloJavalin {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8000);
		
		app.get("/hello", ctx -> {
			ctx.res.getWriter().write("Hello from the server side!");
		});
		
		app.get("/request-details", ctx -> {
			/*
			 * What type of data is included in an HTTP request that the client sends to the server?
			 * Well...
			 * 
			 * === Request Head ===
			 * - HTTP Version
			 * - Any Custom Headers that the client has added
			 * - HTTP Verb/Method (e.g. GET, POST, DELETE, PUT, PATCH, TRACE, HEAD, OPTIONS)
			 * 		These verbs have "conventional" uses:
			 * 		- GET: The client is requesting a resource from the server
			 * 		- POST: The client is sending data to the server (there's something in the request body)
			 * 		- DELETE: The client wishes to delete a representation of a resource/data
			 * 		- PUT: The client wishes to update a representation of a resource
			 * 		- PATCH: The client wishes to do a partial modification of a representation of a resource
			 * 		- TRACE: Performs a loop-back test on the way to the target resource
			 * 		- HEAD: Like a GET request, but the client is only requesting the response head without a body
			 * 		- OPTIONS: Describes the communication options for the target resource
			 * - Request URL
			 * 
			 * === Request Body ===
			 * - JSON
			 * - Or XML
			 * - HTML
			 * - Binary
			 * - Whatever the client wants to send
			 */
			
			// Printing the request headers
			Enumeration<String> requestHeaders = ctx.req.getHeaderNames();
			
			while(requestHeaders.hasMoreElements()) {
				System.out.println(requestHeaders.nextElement());
			}
			
			/*
			 * The server responds with an HTTP Response. An HTTP Response includes:
			 * 
			 * === Response Head ===
			 * - Status Codes: There is a range of status from the 100s to the 500s
			 * 		* 100 -> informational
			 * 		* 200 -> Successful Responses
			 * 		* 300 -> Redirects
			 * 		* 400 -> Client Side Errors
			 * 		* 500 -> Server Side Errors
			 * - Custom Headers
			 * - Content Type
			 * 
			 * === Response Body ===
			 * - Whatever data was requested (a JSON string, XML, etc.)
			 */
			
		});
		
		/*
		 * Note that this defines an endpoint with a unique identifier (URI, which stands for
		 * Uniform Resource Identifier). Note also that a URI is different from a URL.
		 * 
		 * URL -> http://localhost:8000/new-ingredient
		 * URI -> /new-ingredient
		 * 
		 * In any case, this endpoint only allows for POST requests. This means that the client cannot
		 * make a GET request or a DELETE request for this resource.
		 */
		app.post("/new-ingredient", ctx -> {
			//TBD
		});
	}
}
