package ServiceTest;

import Driver.serviceUtil.ReimbursementBuilder;
import daolayer.DAOQueries;
import daolayer.Reimbursements;
import daolayer.UserSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceLayerTests {



    private  ReimbursementBuilder reimbursements;

    @Mock
    private  DAOQueries queries;


     List<Reimbursements> arrayList;

    @BeforeAll
    public  void setUp() {

        MockitoAnnotations.openMocks(this);
        arrayList = new ArrayList<>();
        arrayList.add(new Reimbursements("SS33DD","Johnny Sins",Date.valueOf(LocalDate.now()),
                222, "idk", "Approved"));
        arrayList.add(new Reimbursements("SSFF33","Sonny Jins",Date.valueOf(LocalDate.now()),
                555, "YURRR", "Denied"));
        arrayList.add(new Reimbursements("FHSD$#$$@","DANNY D BABEEE",Date.valueOf(LocalDate.now()),
                555, "BRYOOOO", "Approved"));
        reimbursements = new ReimbursementBuilder(queries);


    }
    @Test
    void validateUserTest(){
        Mockito.when(queries.returnRequests()).thenReturn(arrayList);
       arrayList = reimbursements.sortByStatus();

        arrayList.sort(Comparator.comparing(Reimbursements::getStatus));
        System.out.println(arrayList);
    }



    }



