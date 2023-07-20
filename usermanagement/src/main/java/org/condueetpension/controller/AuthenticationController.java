package org.condueetpension.controller;

import lombok.AllArgsConstructor;
import org.condueetpension.data.models.User;
import org.condueetpension.services.AuthenticationService;
import org.condueetpension.userManagementException.TokenExpiredException;
import org.condueetpension.userManagementException.TokenNotFoundException;
import org.condueetpension.userManagementException.UserNotFoundException;
import org.condueetpension.userMannagementUtility.userManagementDTOs.requests.*;
import org.condueetpension.userMannagementUtility.userManagementDTOs.response.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_management")
@AllArgsConstructor
public class AuthenticationController {
    public final AuthenticationService service;

    @PostMapping("endUser/register")
    public ResponseEntity<?> endUserCreation(@RequestBody RegisterRequest request){
        service.registerEndUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Check your email");
    }

    @PostMapping("endUser/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyRequest request){
        User user = service.getUserById(request.getId());
        var verifyResponse = service.verifyAccount(user, request.getToken());
        return ResponseEntity.ok(verifyResponse);
    }

    @PostMapping("endUser/login")
    public ResponseEntity<AuthenticationResponse> loginEndUser(@RequestBody AuthenticateUserRequest authenticateUserRequest){
        return ResponseEntity.ok(service.authenticateUser(authenticateUserRequest));
    }

    @PutMapping("endUser/updateProfile/{userId}")
    public ResponseEntity<String> updateEndUser(
            @PathVariable("userId") String userId,
            @RequestBody UpdateEndUserRequest request
    ) {
        try {
            service.updateEndUser(userId, request);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + exception.getMessage());
        }
    }

    @PostMapping("/endUser/forgotPassword")
    public ResponseEntity<String> forgotPassword(
            @RequestBody ForgetPasswordRequest forgetPasswordRequest
    ) {
        try {
            String token = service.forgotPassword(forgetPasswordRequest);
            return ResponseEntity.ok("Password reset token generated: " + token);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/endUser/resetPassword")
    public ResponseEntity<String> resetPassword(
            @RequestBody ResetPasswordRequest resetPasswordRequest
    ) {
        try {
            String result = service.resetPassword(resetPasswordRequest);
            return ResponseEntity.ok(result);
        } catch (TokenNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Token not found");
        } catch (TokenExpiredException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Token expired");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + exception.getMessage());
        }
    }



}
