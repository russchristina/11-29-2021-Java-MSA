package com.revature;

import java.util.Arrays;
import java.util.List;

import com.revature.model.Reimbursements_Alli;
import com.revature.repository.AlliRepositoryImpl;
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
class AlliServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private AlliRepositoryImpl alliRepositoryImpl;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService();
    }

    @Test
    public void findAllAlli() {

        Mockito.when(alliRepositoryImpl.findAllAlli()).thenReturn(Arrays.asList(
                new Reimbursements_Alli(1, "Alli Employee", "pending", "not approved", 2, "work lunch meeting",
                        "manager reason"),
                new Reimbursements_Alli(2, "Alli Employee", "pending", "not approved", 2, "work lunch meeting",
                        "manager reason")));

        List<Reimbursements_Alli> findAll = employeeService.findAllAlli();

        Assertions.assertEquals("Alli Employee", findAll.get(0).getReimbursed_employee());

    }

    @Test
    public void submitReimbursement(Reimbursements_Alli reimbursements) {
    }

}
