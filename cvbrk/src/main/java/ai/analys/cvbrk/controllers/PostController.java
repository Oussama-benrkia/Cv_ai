package ai.analys.cvbrk.controllers;

import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dto.PostRequest;
import ai.analys.cvbrk.dto.PostResponse;
import ai.analys.cvbrk.services.PostService;
import ai.analys.cvbrk.validation.OnCreate;
import ai.analys.cvbrk.validation.OnUpdate;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")

    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        return postService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostResponse>> findAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }
    @GetMapping
    public ResponseEntity<PageResponse<PostResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(postService.findAll(page, size));
    }

    @PostMapping
    @PreAuthorize("hasRole('RH')") // Restricts access to users with the RH role
    public ResponseEntity<PostResponse> savePost(
            @Validated(OnCreate.class)  @ModelAttribute PostRequest request
    ) throws JsonProcessingException {
        return postService.save(request)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.badRequest().build());
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RH')") // Restricts access to users with the RH role
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long id,
            @Validated(OnUpdate.class) @ModelAttribute PostRequest request
    ) throws JsonProcessingException {
        return postService.update(request, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RH')") // Restricts access to users with the RH role
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rh/{rhId}")
    public ResponseEntity<PageResponse<PostResponse>> findAllByRhId(@PathVariable Long rhId,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(postService.findAllByRhId(page, size,rhId));
    }
    @GetMapping("/rh/list/{rhId}")
    public ResponseEntity<List<PostResponse>> findAllByRhId(@PathVariable Long rhId) {
        return ResponseEntity.ok(postService.findAllByRhId(rhId));
    }
    @GetMapping("/my")
    @PreAuthorize("hasRole('RH')") // Restricts access to users with the RH role
    public ResponseEntity<PageResponse<PostResponse>> findmypost(
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(postService.findAllMypost(page, size));
    }
    @GetMapping("/my/{rhId}")
    @PreAuthorize("hasRole('RH')") // Restricts access to users with the RH role
    public ResponseEntity<List<PostResponse>> findmypost(@PathVariable Long rhId) {
        return ResponseEntity.ok(postService.findAllMypost());
    }
}