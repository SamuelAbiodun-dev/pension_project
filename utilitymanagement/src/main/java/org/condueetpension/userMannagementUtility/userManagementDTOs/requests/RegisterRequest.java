package org.condueetpension.userMannagementUtility.userManagementDTOs.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.condueetpension.userMannagementUtility.UserManagementUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    @NotNull(message = "field cannot be null")
    @NotEmpty(message = "field cannot be empty")
    @Email(message = "please enter a valid email", regexp = UserManagementUtils.EMAIL_REGEX_STRING )
    @JsonProperty("email")
    private String email;

    @NotNull(message = "password field cannot be null")
    @NotEmpty(message = "password field cannot be empty")
    @Email(message = "please enter a valid password", regexp = UserManagementUtils.PASSWORD_REGEX_STRING )
    @JsonProperty("password")
    private String password;

    @NotNull(message = "phone number field cannot be null")
    @NotEmpty(message = "phone number field cannot be empty")
    @Email(message = "please enter a valid phone number", regexp = UserManagementUtils.PHONE_NUMBER_REGEX_STRING )
    @JsonProperty("Phone")
    private String phoneNumber;
}
