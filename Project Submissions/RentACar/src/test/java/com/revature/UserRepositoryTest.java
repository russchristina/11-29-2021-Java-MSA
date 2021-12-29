package com.revature;

import com.revature.model.Users;
import com.revature.repository.UsersRepository;
import com.revature.repository.UsersRepositoryImplement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    private UsersRepository usersRepository;

    @BeforeAll
    public void setup() {
        usersRepository = new UsersRepositoryImplement();
    }

    @Test
    @DisplayName("make sure getting user back")
    public void testUser() {
        Users retrieveName = usersRepository.findByName("Tom Jones");

        Assertions.assertNull(retrieveName);
    }

    @Test
    @DisplayName("testing secondary user method")
    public void changeOrAddSecondaryUser() {
        usersRepository.changeOrAddSecondaryUser("Anne Jones", "Tom Jones");

    }

    @Test
    @DisplayName("testing addusers() which is used for registration")
    public void addUsers() {
        Users newUser = new Users(1, "String", "String", "String", 0.0f, "String", "String");
        usersRepository.addUsers(newUser);
    }
}
