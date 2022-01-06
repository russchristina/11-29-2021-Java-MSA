package test.dao;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import utilities.repository.EmployeeRequestRepositoryImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class DAOTest {

	@InjectMocks
	private EmployeeRequestRepositoryImpl repo;
} // End test class
