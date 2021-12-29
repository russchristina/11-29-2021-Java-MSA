package com.revature;

import com.revature.repository.AdministratorRepository;
import com.revature.repository.AdministratorRepositoryImplement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class AdminRepositoryTest {

    private AdministratorRepository adminRepository;

    @BeforeAll
    public void setup() {
        adminRepository = new AdministratorRepositoryImplement();
    }

    @Test
    @DisplayName("make sure getting user back")
    public void testPass() {
        String retrievePass = adminRepository.retrievePassword("Tom Jones");

        Assertions.assertNull(retrievePass);
    }

    @Test
    @DisplayName("testing secondary user method")
    public void retrieveAdmin() {
        adminRepository.retrieveAdmin("Jerry Admin");

    }

}