package org.condueetpension.auth;

import org.condueetpension.userMannagementUtility.userManagementDTOs.requests.RegisterRequest;
import org.condueetpension.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationServiceImplTest {

    private AuthenticationService authenticationService;

    RegisterRequest registerRequest = new RegisterRequest();


    @BeforeEach
    void setUp() {
        registerRequest.setEmail("taiye");
        registerRequest.setPassword("1234");
        registerRequest.setFirstName("Daddy wa");
        registerRequest.setLastName("Olaoluwa");
        registerRequest.setPhoneNumber("223344");
    }

    @Test
    void endUserRegistration() {
        authenticationService.registerEndUser(registerRequest);
    }
}