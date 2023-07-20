package org.condueetpension.services;
import org.condueetpension.data.models.PfaUser;
import org.condueetpension.data.models.PfcUser;
import org.condueetpension.data.models.User;
import org.condueetpension.userMannagementUtility.userManagementDTOs.requests.*;
import org.condueetpension.userMannagementUtility.userManagementDTOs.response.AuthenticationResponse;
import org.condueetpension.userMannagementUtility.userManagementDTOs.response.UserRegistrationResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthenticationService {
    void registerEndUser(RegisterRequest request);
    AuthenticationResponse authenticateUser(AuthenticateUserRequest request);
    void updateEndUser(String userId, UpdateEndUserRequest request);
    User getUserById(String userId);
    UserRegistrationResponse verifyAccount(User user, String token);
    Optional<User> getUserByEmail(String email);
    PfaUser getPfaUserById(String pfaUserId);
    List<PfaUser> getPfaUsers();
    PfcUser getPfcUserById(String pfcUserId);
    List<PfcUser> getPfcUsers();
    void deleteUserByEmail(String email);
    void deleteAll();
    List<User> getUsers();
    String forgotPassword(ForgetPasswordRequest forgetPasswordRequest);
    String resetPassword(ResetPasswordRequest resetPasswordRequest);
    void updatePfcUser(String userId, UpdatePfa_PfcUserRequest request);
    void updatePfaUser(String userId, UpdatePfa_PfcUserRequest request);
}
