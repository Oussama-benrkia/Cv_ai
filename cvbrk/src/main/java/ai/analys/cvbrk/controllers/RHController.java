package ai.analys.cvbrk.controllers;

import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dto.UserRequest;
import ai.analys.cvbrk.dto.UserResponse;
import ai.analys.cvbrk.services.RHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rhs")
public class RHController {

    private final RHService rhService;

    @Autowired
    public RHController(RHService rhService) {
        this.rhService = rhService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return rhService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> findAllRhs() {
        return ResponseEntity.ok(rhService.findAll());
    }

    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(rhService.findAll(page, size));
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveRh(
            @Validated @RequestBody UserRequest request
    ) {
        return rhService.save(request)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateRh(
            @PathVariable Long id,
            @Validated @RequestBody UserRequest request
    ) {
        return rhService.update(request, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRh(@PathVariable Long id) {
        rhService.delete(id);
        return ResponseEntity.noContent().build();
    }
}