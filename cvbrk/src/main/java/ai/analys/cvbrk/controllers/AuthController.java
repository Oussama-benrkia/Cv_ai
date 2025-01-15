package ai.analys.cvbrk.controllers;

import ai.analys.cvbrk.dto.RegisterRequest;
import ai.analys.cvbrk.dto.RegisterResponse;
import ai.analys.cvbrk.services.AuthenticationUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationUserService authservice;

    public AuthController(AuthenticationUserService authservice) {
        this.authservice = authservice;
    }

    @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RegisterResponse> register(@Validated @ModelAttribute RegisterRequest.RegisterRequestRegister registerRequest) {
        RegisterResponse registerResponse = authservice.register(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponse> login(@Validated @RequestBody RegisterRequest.RegisterRequestLogin loginRequest) {
        RegisterResponse loginResponse = authservice.login(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RegisterResponse> refreshToken(@Validated @RequestBody RegisterRequest.RegisterTokenRequest  refreshTokenRequest) {
        RegisterResponse refreshResponse = authservice.refreshToken(refreshTokenRequest);
        if (refreshResponse != null) {
            return new ResponseEntity<>(refreshResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

