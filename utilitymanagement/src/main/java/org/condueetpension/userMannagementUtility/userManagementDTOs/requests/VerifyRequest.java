package org.condueetpension.userMannagementUtility.userManagementDTOs.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerifyRequest {
    private String id;
    private String token;
}
