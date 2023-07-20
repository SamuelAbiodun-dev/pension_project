package org.condueetpension.userMannagementUtility.userManagementDTOs.requests;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String newPassword;
}
