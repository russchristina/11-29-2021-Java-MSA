package junit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import models.Request;
import repo.RequestDAO;
import repo.RequestImpl;

@TestInstance(Lifecycle.PER_CLASS)
public class RequestDAOTest {

	private RequestDAO requestDAO;
	
	@BeforeAll
	public void setup() {
		requestDAO = new RequestImpl();
	}
	
	@Test
	public void testViewAllRequests() {
		List<Request> retrievedRequests = requestDAO.viewAll();
		
		Assertions.assertNotNull(retrievedRequests);
		Assertions.assertEquals(2, retrievedRequests.size());
	}
	
	@Test
	public void testFindById() {
		Request retrievedRequest = requestDAO.findById(1);
		
	}
}
