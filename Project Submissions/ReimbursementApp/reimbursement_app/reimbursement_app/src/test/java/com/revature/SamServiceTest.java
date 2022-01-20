package com.revature;

import java.util.Arrays;
import java.util.List;

import com.revature.model.Reimbursements_Sam;
import com.revature.repository.SamRepositoryImpl;
import com.revature.service.EmployeeService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class SamServiceTest {

  @InjectMocks
  private EmployeeService employeeService;

  @Mock
  private SamRepositoryImpl samRepositoryImpl;

  @BeforeAll
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    employeeService = new EmployeeService();
  }

  @Test
  public void findAllSam() {
    Mockito.when(samRepositoryImpl.findAllSam()).thenReturn(Arrays.asList(
        new Reimbursements_Sam(1, "Sam Employee", "pending", "not approved", 2, "work lunch meeting",
            "manager reason"),
        new Reimbursements_Sam(2, "Sam Employee", "pending", "not approved", 2, "work lunch meeting",
            "manager reason")));

    List<Reimbursements_Sam> findAll = employeeService.findAllSam();

    Assertions.assertEquals("Sam Employee", findAll.get(0).getReimbursed_employee());

  }

}
