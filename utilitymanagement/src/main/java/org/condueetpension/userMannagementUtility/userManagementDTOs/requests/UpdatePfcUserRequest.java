package org.condueetpension.userMannagementUtility.userManagementDTOs.requests;

import lombok.Data;

@Data
public class UpdatePfcUserRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String address;
    private String pfcId;
    private String organisationId;
}
