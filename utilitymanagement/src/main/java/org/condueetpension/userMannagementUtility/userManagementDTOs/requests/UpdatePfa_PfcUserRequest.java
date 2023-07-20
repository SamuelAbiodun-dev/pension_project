package org.condueetpension.userMannagementUtility.userManagementDTOs.requests;

import lombok.Data;

@Data
public class UpdatePfa_PfcUserRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String address;
    private String pfa_pfcId;
    private String organisationId;
}
