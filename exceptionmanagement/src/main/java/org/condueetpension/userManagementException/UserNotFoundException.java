package org.condueetpension.userManagementException;

public class UserNotFoundException extends BusinessLogicException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
