package ai.analys.cvbrk.controllers;

import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dto.PostuleRequest;
import ai.analys.cvbrk.dto.PostuleResponse;
import ai.analys.cvbrk.services.PostuleeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/postules")
public class PostuleController {

    private final PostuleeService postuleeService;


    public PostuleController(PostuleeService postuleeService) {
        this.postuleeService = postuleeService;
    }

    @PostMapping
    public ResponseEntity<Optional<PostuleResponse>> addPostule(@RequestBody @Valid PostuleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postuleeService.add(request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<PostuleResponse>> deletePostule(@PathVariable Long id) {
        return ResponseEntity.ok(postuleeService.deletePostule(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<PostuleResponse>> findAllPostule(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(postuleeService.findAllPostule(page,size));
    }
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<PageResponse<PostuleResponse>> findAllByEtudiantId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long etudiantId)
    {
        return ResponseEntity.ok(postuleeService.findAllByEtudiantId(page,size,etudiantId));
    }
    @GetMapping("/etudiantList/{etudiantId}")
    public ResponseEntity<List<PostuleResponse>> findAllByEtudiantId(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(postuleeService.findAllByEtudiantId(etudiantId));
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<PageResponse<PostuleResponse>> findAllByPostId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long postId)
    {
        return ResponseEntity.ok(postuleeService.findAllByPostId(page,size,postId));
    }
    @GetMapping("/postList/{postId}")
    public ResponseEntity<List<PostuleResponse>> findAllByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(postuleeService.findAllByPostId(postId));
    }
    @GetMapping("/me")
    public ResponseEntity<PageResponse<PostuleResponse>> findAllMyPostule(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(postuleeService.findAllMypostule(page,size));
    }
    @GetMapping("/meList")
    public ResponseEntity<List<PostuleResponse>> findAllMyPostule() {
        return ResponseEntity.ok(postuleeService.findAllMypostule());
    }

}