package org.condueetpension.userMannagementUtility.userManagementDTOs.requests;

import lombok.Data;
import org.condueetpension.userMannagementUtility.constants.UserStatus;

@Data
public class UpdateEndUserRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String address;
    private String bvn;
    private String image;
    private String role;
    private String pfa;
    private UserStatus userStatus;
}
