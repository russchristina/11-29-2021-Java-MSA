package junit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import models.Employee;
import models.Request;
import repo.ManagerDAO;
import repo.ManagerImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class ManagerDAOTest {
		
		private ManagerDAO managerDAO;
		
		@BeforeAll
		public void setup() {
			managerDAO = new ManagerImpl();
		}
		
		@Test
		public void findAllEmpTest() {
			
			List<Employee> retrievedEmployees = managerDAO.findAllEmp();
			
			Assertions.assertNotNull(retrievedEmployees);
			Assertions.assertEquals(4, retrievedEmployees.size());
		}

		@Test
		public void testFindAllRequests() {
			List<Request> retrievedRequests = managerDAO.viewAll();
			
			Assertions.assertNotNull(retrievedRequests);
			Assertions.assertEquals(2, retrievedRequests.size());
}
}
