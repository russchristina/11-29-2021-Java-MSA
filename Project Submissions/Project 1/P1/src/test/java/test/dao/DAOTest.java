package test.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import models.Employee;
import models.Request;
import utilities.repository.EmployeeRequestRepositoryImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class DAOTest {
	
	@Mock
	private EmployeeRequestRepositoryImpl repo;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		repo = new EmployeeRequestRepositoryImpl();
	} // End setup
	
	@Test
	public void testfindByEmployeeName() {
		Mockito.when(repo.findByEmployeeName("Ash")).thenReturn(new Employee("Ash", "pass123", false));
				
		Employee e = repo.findByEmployeeName("Ash");
		Assertions.assertEquals("Ash", e.getName());
	} // End test
	
	@Test
	public void testFindAllRequests() {
		Mockito.when(repo.findAllRequests()).thenReturn(Arrays.asList(
				new Request(1, "Ash", 150, "Travel", "Approved", "Go catch'em all!"),
				new Request(2, "Dawn", 50, "Work-related expense", "Denied", "There are berries right outside."),
				new Request(3, "Oak", 150, "Equipment", "Pending", null)));
				
		List<Request> requestList = repo.findAllRequests();
		Assertions.assertEquals("Dawn", requestList.get(1).getEmployeeName());
	} // End test
	
	@Test
	public void testFindPendingRequests() {
		Mockito.when(repo.findPendingRequests()).thenReturn(Arrays.asList(
				new Request(1, "Ash", 150, "Travel", "Pending", "Go catch'em all!"),
				new Request(2, "Dawn", 50, "Work-related expense", "Denied", "There are berries right outside."),
				new Request(3, "Oak", 150, "Equipment", "Pending", null)));
				
		List<Request> requestList = repo.findPendingRequests();
		Assertions.assertEquals("Dawn", requestList.get(1).getEmployeeName());
	} // End test

	@Test
	public void testHighestSpender() {
		Mockito.when(repo.highestSpender()).thenReturn(new Request("Ash", 100));
				
		Request r = repo.highestSpender();
		Assertions.assertEquals(100, r.getAmount());
	} // End test
	
	@Test
	public void testNumberOfRequests() {
		Mockito.when(repo.numberOfRequests()).thenReturn(3);
				
		int c = repo.numberOfRequests();
		Assertions.assertEquals(3, c);
	} // End test
	
	@Test
	public void testAverageCost() {
		Mockito.when(repo.averageCost()).thenReturn(120.55);
				
		double a = repo.averageCost();
		Assertions.assertEquals(120.55, a);
	} // End test
} // End test class
