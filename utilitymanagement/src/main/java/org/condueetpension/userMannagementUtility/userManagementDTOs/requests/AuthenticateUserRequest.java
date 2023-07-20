package org.condueetpension.userMannagementUtility.userManagementDTOs.requests;

import lombok.Data;

@Data
public class AuthenticateUserRequest {
    private String email;
    private String password;
}
