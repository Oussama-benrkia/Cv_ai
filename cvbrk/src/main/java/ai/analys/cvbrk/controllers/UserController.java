package ai.analys.cvbrk.controllers;

import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dto.UserRequest;
import ai.analys.cvbrk.dto.UserResponse;
import ai.analys.cvbrk.exception.ResourceNotFoundException;
import ai.analys.cvbrk.services.UserService;
import ai.analys.cvbrk.validation.OnCreate;
import ai.analys.cvbrk.validation.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageResponse<UserResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String role) {

        PageResponse<UserResponse> responses;
        if (search.isEmpty() && role.isEmpty()) {
            responses = userService.findAll(page, size);
        } else if (search.isEmpty()) {
            responses = userService.findAllWithRole(page, size, role);
        } else {
            responses = userService.findAllWithSearchAndRole(page, size, search, role);
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponse>> findAllUsers(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String role) {

        List<UserResponse> responses;

        if (search.isEmpty() && role.isEmpty()) {
            responses = userService.findAll();
        } else if (search.isEmpty()) {
            responses = userService.findAllWithRole(role);
        } else {
            responses = userService.findAllWithSearchAndRole(search, role);
        }
        return ResponseEntity.ok(responses);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> saveUser(
            @Validated(OnCreate.class) @ModelAttribute UserRequest request) {

        return userService.save(request)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElseThrow(() -> new ResourceNotFoundException("Unable to save user."));
    }


    @PutMapping(path = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Validated(OnUpdate.class) @ModelAttribute UserRequest request) {

        return userService.update(request, id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to update user with id: " + id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}