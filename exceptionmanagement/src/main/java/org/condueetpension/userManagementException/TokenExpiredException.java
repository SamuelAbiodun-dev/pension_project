package org.condueetpension.userManagementException;

public class TokenExpiredException extends BusinessLogicException{
    public TokenExpiredException(String message) {
        super(message);
    }
}
