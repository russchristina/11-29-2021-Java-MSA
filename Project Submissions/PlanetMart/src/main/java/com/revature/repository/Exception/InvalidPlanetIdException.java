package com.revature.repository.Exception;

public class InvalidPlanetIdException extends RuntimeException {
    public InvalidPlanetIdException(String planet_id_not_found) {
    }

    public InvalidPlanetIdException() {
    }
}
