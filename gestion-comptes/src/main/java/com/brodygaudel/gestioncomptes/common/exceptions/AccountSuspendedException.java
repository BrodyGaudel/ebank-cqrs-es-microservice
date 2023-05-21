package com.brodygaudel.gestioncomptes.common.exceptions;

public class AccountSuspendedException extends Exception{
    public AccountSuspendedException(String message) {
        super(message);
    }
}
