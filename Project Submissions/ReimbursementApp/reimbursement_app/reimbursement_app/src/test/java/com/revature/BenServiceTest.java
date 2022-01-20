
package com.revature;

import java.util.Arrays;
import java.util.List;

import com.revature.model.Reimbursements_Ben;
import com.revature.repository.BenRepositoryImpl;
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
class BenServiceTest {

  @InjectMocks
  private EmployeeService employeeService;

  @Mock
  private BenRepositoryImpl benRepositoryImpl;

  @BeforeAll
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    employeeService = new EmployeeService();
  }

  @Test
  public void findAllBen() {

    Mockito.when(benRepositoryImpl.findAllBen()).thenReturn(Arrays.asList(
        new Reimbursements_Ben(1, "Ben Employee", "pending", "not approved", 2, "work lunch meeting",
            "manager reason"),
        new Reimbursements_Ben(2, "Ben Employee", "pending", "not approved", 2, "work lunch meeting",
            "manager reason")));

    List<Reimbursements_Ben> findAll = employeeService.findAllBen();

    Assertions.assertEquals("Ben Employee", findAll.get(0).getReimbursed_employee());

  }

  // @Test
  // public void submitReimbursement(Reimbursements_Ben reimbursements) {

  // Mockito.when(employeeService.submitReimbursement(Mockito.any(Reimbursements_Ben.class)).thenReturn()));

  // Reimbursements_Ben ben = new Reimbursements_Ben(1, "Ben Employee", "pending",
  // "not approved", 2,
  // "work lunch meeting",
  // "manager reason");

  // Assertions.assertNotNull(ben);

  // }

}
