package ai.analys.cvbrk.services;

import ai.analys.cvbrk.dao.TokenRepository;
import ai.analys.cvbrk.dao.UserRepository;
import ai.analys.cvbrk.dto.RegisterRequest;
import ai.analys.cvbrk.dto.RegisterResponse;
import ai.analys.cvbrk.entity.Role;
import ai.analys.cvbrk.entity.Token;
import ai.analys.cvbrk.entity.User;
import ai.analys.cvbrk.exception.OperationNotPermittedException;
import ai.analys.cvbrk.images.ImagesFolder;
import ai.analys.cvbrk.images.ImgService;
import ai.analys.cvbrk.mapper.RegisterMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class AuthenticationUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final JwtUtils jwtService;
    private final AuthenticationManager authenticationManager;
    private final RegisterMapper registerMapper;
    private final TokenRepository tokenRepository;
    private final ImgService imgService;

    public AuthenticationUserService(PasswordEncoder passwordEncoder, UserRepository userRepo, JwtUtils jwtService, AuthenticationManager authenticationManager, RegisterMapper registerMapper, TokenRepository tokenRepository, ImgService imgService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.registerMapper = registerMapper;
        this.tokenRepository = tokenRepository;
        this.imgService = imgService;
    }

    /**
     * Registers a new user.
     */
    public RegisterResponse register(RegisterRequest.RegisterRequestRegister registerRequest) {
        if (userRepo.existsByEmail(registerRequest.getEmail())) {
            throw new EntityNotFoundException("User with this email already exists.");
        }
        User newUser = registerMapper.toEntity(registerRequest);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole(registerRequest.getRole() != null ? Role.valueOf(registerRequest.getRole()) : Role.ETUDIANT);
        String path = imgService.addImage(registerRequest.getFile(), ImagesFolder.USER);
        newUser.setImage(path);
        userRepo.save(newUser);
        String jwt = jwtService.generateAccessToken(newUser);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), newUser);
        this.saveUserToken(newUser, jwt, false, refreshToken);
        this.saveUserToken(newUser, refreshToken, true, "");
        return RegisterResponse.builder()
                .statusCode(200)
                .message("User registered successfully")
                .token(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Authenticates a user with their email and password.
     */
    public RegisterResponse login(RegisterRequest.RegisterRequestLogin loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found with this email."));

        String jwt = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        this.saveUserToken(user, jwt, false, refreshToken);
        this.saveUserToken(user, refreshToken, true, "");

        return RegisterResponse.builder()
                .statusCode(200)
                .message("Login successful")
                .token(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Refreshes a user's JWT token.
     */
    public RegisterResponse refreshToken(RegisterRequest.RegisterTokenRequest  refreshTokenRequest) {
        String email = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        if (!jwtService.isRefershTokenValid(refreshTokenRequest.getToken(), user)) {
            throw new OperationNotPermittedException("Invalid token.");
        }

        String newJwt = jwtService.generateAccessToken(user);
        this.saveUserToken(user, newJwt, false, refreshTokenRequest.getToken());
        return RegisterResponse.builder()
                .statusCode(200)
                .message("Token refreshed successfully")
                .token(newJwt)
                .refreshToken(refreshTokenRequest.getToken())
                .build();
    }

    private void saveUserToken(User user, String jwtToken, boolean refreshToken, String refreshTokenUser) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .isRefreshToken(refreshToken)
                .refreshToken(refreshTokenUser)
                .build();
        tokenRepository.save(token);
    }

}
