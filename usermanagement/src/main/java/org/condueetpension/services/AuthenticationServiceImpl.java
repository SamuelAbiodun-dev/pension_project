package org.condueetpension.services;

import lombok.RequiredArgsConstructor;
import org.condueetpension.config.JwtService;
import org.condueetpension.data.models.*;
import org.condueetpension.data.repository.*;
import org.condueetpension.userManagementException.BusinessLogicException;
import org.condueetpension.userManagementException.TokenNotFoundException;
import org.condueetpension.userManagementException.UserNotFoundException;
import org.condueetpension.userMannagementUtility.constants.UserStatus;
import org.condueetpension.notification.SendMailService;
import org.condueetpension.userMannagementUtility.userManagementDTOs.response.UserRegistrationResponse;
import org.condueetpension.userMannagementUtility.userManagementDTOs.requests.*;
import org.condueetpension.userMannagementUtility.userManagementDTOs.response.AuthenticationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 10;
    private final UserRepository userRepository;
    private Token oneTimePassword;
    private final TokenRepository tokenRepository;
    private final PfaRepository pfaRepository;
    private final PfcRepository pfcRepository;
    private final EndUserRepository endUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JavaMailSender javaMailSender;
    private final AuthenticationManager authenticationManager;
    private final SendMailService sendMailService;

    private final ModelMapper modelmapper;

    public void registerEndUser(RegisterRequest request){
        validateEmail(request.getEmail());
        var user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User savedUser = userRepository.save(user);
        sendVerificationMail(savedUser);
    }

    private void validateEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()) throw new BusinessLogicException("user already exists");
    }

    private String generateVerificationToken() {
        SecureRandom token = new SecureRandom();
        return String.valueOf(token.nextInt(1001, 10000));
    }

    private void buildToken(User user, String token) {
        oneTimePassword = new Token(user, token);
        Optional<Token> existingToken = tokenRepository.findTokenByUserId(user.getUserId());
        existingToken.ifPresent(tokenRepository::delete);
        tokenRepository.save(oneTimePassword);
    }

    public void sendVerificationMail(User user) {
        String token = generateVerificationToken();
        buildToken(user, token);
        sendMailService.send(user,token);
    }

    @Override
    public UserRegistrationResponse verifyAccount(User user, String token) {
        Optional<Token> foundToken = tokenRepository.findTokenByUserIdAndToken(user.getUserId(), token);
        if (foundToken.isEmpty()) throw new BusinessLogicException("token not found");
        if (foundToken.get().getExpiryTime().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(foundToken.get());
            throw new BusinessLogicException("token expired generate new token");
        }
        user.setEnabled(true);
        User updatedUser = updateUser(user);
        tokenRepository.delete(foundToken.get());
        return getUserResponse(updatedUser);
    }

    private UserRegistrationResponse getUserResponse(User user) {
        return modelmapper.map(user, UserRegistrationResponse.class);
    }

    private User updateUser(User user) {
        return userRepository.save(user);
    }

    public AuthenticationResponse authenticateUser(AuthenticateUserRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void updateEndUser(String userId, UpdateEndUserRequest request) {
        get_endUser(userId, request);
        EndUser endUser = new EndUser();
        endUser.setEndUserId(userId);
        endUser.setAddress(request.getAddress());
        endUser.setRole(request.getRole());
        endUser.setBvn(request.getBvn());
        endUser.setImage(request.getImage());
        endUser.setPfa(request.getPfa());
        endUser.setUserStatus(UserStatus.PENDING);
        endUserRepository.save(endUser);
    }

    public void updatePfaUser(String userId, UpdatePfa_PfcUserRequest request) {
        getPfa_PfcUser(userId, request);
        PfaUser pfaUser = new PfaUser();
        pfaUser.setPfaId(request.getPfa_pfcId());
        pfaUser.setOrganisationId(request.getOrganisationId());
        pfaRepository.save(pfaUser);

    }

    @Override
    public void updatePfcUser(String userId, UpdatePfa_PfcUserRequest request) {
        getPfa_PfcUser(userId, request);
        PfcUser pfcUser = new PfcUser();
        pfcUser.setPfcId(request.getPfa_pfcId());
        pfcUser.setOrganisationId(request.getOrganisationId());
    }

    private void getMainUser(String userId, String firstName,
                             String lastName, String middleName,
                             String email, String phoneNumber,
                             String userName, UpdatePfa_PfcUserRequest request) {
        getUserFromStart(userId, firstName, lastName, middleName, email, phoneNumber, userName);
    }

    private void getMainUser(String userId, String firstName,
                             String lastName, String middleName,
                             String email, String phoneNumber,
                             String userName, UpdateEndUserRequest request) {
        getUserFromStart(userId, firstName, lastName, middleName, email, phoneNumber, userName);
    }

    private void getUserFromStart(String userId, String firstName, String lastName, String middleName, String email, String phoneNumber, String userName) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setUserName(userName);
        userRepository.save(user);
    }

    private void getPfa_PfcUser(String userId, UpdatePfa_PfcUserRequest request) {
        getMainUser(userId, request.getFirstName(),
                request.getLastName(),
                request.getMiddleName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getUserName(),
                request);
    }
    private void get_endUser
            (String userId, UpdateEndUserRequest request) {
        getMainUser(userId, request.getFirstName(),
                request.getLastName(),
                request.getMiddleName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getUserName(),
                request);
    }


    @Override
    public String forgotPassword(ForgetPasswordRequest forgetPasswordRequest) {
        User user = userRepository.findByEmail(forgetPasswordRequest.getEmail())
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        String token = generateUserToken();
        user.setToken(token);
        user.setTokenCreationDateTime(LocalDateTime.now());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("samuelabiodun324@gmail.com");
        simpleMailMessage.setSubject(" CONDUEET TECH: Reset password token: ");
        simpleMailMessage.setText(String.format("To reset your password, use this token : %s", token));
        javaMailSender.send(simpleMailMessage);
        return userRepository.save(user).getToken();
    }

    private String generateUserToken() {
        StringBuilder token = new StringBuilder();
        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    public String resetPassword(ResetPasswordRequest resetPasswordRequest){
        User user = userRepository.findByToken(resetPasswordRequest.getToken())
                .orElseThrow(()-> new TokenNotFoundException("Token not found"));
        LocalDateTime tokenCreationDateTime = user.getTokenCreationDateTime();
        if(isTokenExpired(tokenCreationDateTime)){
            return  "Token expired";
        }
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        user.setToken(null);
        user.setTokenCreationDateTime(null);
        userRepository.save(user);
        return "Password successfully updated";
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("End user not found"));
    }

    @Override
    public PfaUser getPfaUserById(String pfaUserId) {
        return pfaRepository.findByUserId(pfaUserId)
                .orElseThrow(()-> new UserNotFoundException("PFA user not found"));
    }

    @Override
    public List<PfaUser> getPfaUsers() {
        return pfaRepository.findAll();
    }

    @Override
    public PfcUser getPfcUserById(String pfcUserId) {
        return pfcRepository.findByUserId(pfcUserId)
                .orElseThrow(()-> new UserNotFoundException("PFC user not found"));
    }

    @Override
    public List<PfcUser> getPfcUsers() {
        return pfcRepository.findAll();
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    private boolean isTokenExpired(LocalDateTime tokenCreationDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration difference = Duration.between(tokenCreationDateTime, now);
        return difference.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
