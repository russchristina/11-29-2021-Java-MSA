package com.revature.models.exceptions;

public class FailedToTransferFundsException extends Exception {
    public FailedToTransferFundsException() {
    }

    public FailedToTransferFundsException(String message) {
        super(message);
    }
}
