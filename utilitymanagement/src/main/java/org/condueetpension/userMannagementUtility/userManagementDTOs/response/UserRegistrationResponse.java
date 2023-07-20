package org.condueetpension.userMannagementUtility.userManagementDTOs.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.condueetpension.userMannagementUtility.constants.Role;

@Getter
@Setter
@Builder
public class UserRegistrationResponse {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String userName;
    private String rsaPin;
    private Role role;
    private boolean isEnabled;
}
