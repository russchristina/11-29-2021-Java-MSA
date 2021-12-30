package com.revature.project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainDisplayTest {
MainDisplay mainDisplay;

   @BeforeEach
    void  setUP(){
    mainDisplay = mock(MainDisplay.class);
//    when(mainDisplay.key).thenReturn("s");
   }
    @Test
    void getUnregisteredDecision() {
    }

    @Test
    void getRegisteredDecision() {
    }

    @Test
    void setRegisterdDecision() {
    }

    @Test
    void getUsername() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void setPassword() {
    }

    @Test
    void main() {
    }
}