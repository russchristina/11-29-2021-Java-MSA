package com.revature.repository.Exception;

public class InvalidUserCredentialIdException extends RuntimeException {
    public InvalidUserCredentialIdException() {
    }

    public InvalidUserCredentialIdException(String user_credential_id_not_found) {
    }
}
