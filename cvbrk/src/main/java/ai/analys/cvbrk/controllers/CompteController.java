package ai.analys.cvbrk.controllers;

import ai.analys.cvbrk.dto.UserRequest;
import ai.analys.cvbrk.dto.UserResponse;
import ai.analys.cvbrk.services.UserService;
import ai.analys.cvbrk.validation.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compte")
public class CompteController {

    private final UserService userService;

    public CompteController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getProfile() {
        return userService.getProfile()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateProfile(
            @Validated(OnUpdate.class) @ModelAttribute UserRequest request) {
        return userService.updateProfile(request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
