package driver;

import control.LoginController;
import control.login.LoginInput;
import repo.LoginImpl;
import service.LoginService;

public class GDriver {
	
	public static void main(String[] args) {
		
//		ManagerService managerService = new ManagerService();
//		Employee employee = new Employee(4, "Ian", "Duncan", "Faculty");
//		managerService.save(employee);
//		System.out.println(managerService.findAllEmp());
		
		
		LoginImpl loginService = new LoginImpl();
		LoginInput loginInput = new LoginInput("frankydart", "olditlady");
		System.out.println(loginService.checkUser(loginInput));
		
//		RequestService requestService = new RequestService();
//		System.out.println(requestService.viewAll());
		
//		LoginController loginController = new LoginController(new LoginService(new LoginImpl()));
//		System.out.println(loginController.authenticateLogin(new LoginInput("gooddean", "dalmat1an")));
}
}
