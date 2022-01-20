package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import models.Account;
import models.RepayForm;
import service.FormService;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.List;

public class FormController {
	private final Logger dLog = LoggerFactory.getLogger("dLog");
	private RepayForm form;
	private Javalin app;
	private FormService fs;
	
	public FormController(Javalin app) {
		this.app = app;
		this.form = new RepayForm();
		this.fs = new FormService();
	}
	
	public void initEndpoints() {
		this.app.routes(() -> {
			path("/Forms", () -> {
				
				path("/reimbursement", () -> {
					post(submitReimbursement);
				});
				
				path("/allnew", () -> {
					get(allFalseRequests);
				});
				
				path("/handled", () -> {
					post(approvalDecisionMade);
				});
				
				path("/all", () -> {
					post(all);
				});
				
				path("/actually-all", () -> {
					get(actuallyAll);
				});
			
			});
		});
	}
	
	private final Handler actuallyAll = ctx -> {
		dLog.debug("Actually all requests handler");
		
		List<RepayForm> forms = this.fs.selectAllRequests();
		
		try {
			ctx.json(forms);
		}catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	private final Handler all = ctx -> {
		dLog.debug("All Requests Handler");
		
		Account ac = ctx.bodyAsClass(Account.class);
		
		List<RepayForm> forms = this.fs.selectRequestsById(ac.getEmp_id());
		
		try {
			ctx.json(forms);
		}catch (Exception e) {
			dLog.debug("No ID or malformed form");
			e.printStackTrace();
		}
	};
	
	private final Handler submitReimbursement = ctx -> {
		dLog.debug("submitReimbursement Handler");
		
		this.form = ctx.bodyAsClass(RepayForm.class);
		
		this.fs.save(this.form);
		try {
			ctx.json(this.form);
		} catch (Exception e) {
			dLog.debug(this.form.toString());
			e.printStackTrace();
		}
	};
	
	private final Handler allFalseRequests = ctx -> {
		dLog.debug("allFalseRequests Handler");
		
		List<RepayForm> forms = this.fs.selectAllNullRequests();
		
		try {
			ctx.json(forms);
		}catch (Exception e) {
			dLog.debug("allFalseRequests handler", forms);
			e.printStackTrace();
		}
	};
	
	
	private final Handler approvalDecisionMade = ctx -> {
		dLog.debug("accepted request");
		
		this.form = ctx.bodyAsClass(RepayForm.class);
		this.form.setApproval(true);
		
		this.fs.merge(form);
		
		try {
			ctx.json(form);
		}catch(Exception e) {
			dLog.debug("Could not merge form");
			e.printStackTrace();
		}
	};
}
