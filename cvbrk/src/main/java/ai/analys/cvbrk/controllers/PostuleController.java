package ai.analys.cvbrk.controllers;

import ai.analys.cvbrk.analyse.ServiceCallanalyse;
import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dao.PostuleRepository;
import ai.analys.cvbrk.dto.PostuleRequest;
import ai.analys.cvbrk.dto.PostuleResponse;
import ai.analys.cvbrk.entity.Postule;
import ai.analys.cvbrk.exception.ResourceNotFoundException;
import ai.analys.cvbrk.services.PostuleeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;
    private final PostuleRepository postuleRepository;

    private final ServiceCallanalyse postuleeProducer;

    public PostuleController(PostuleeService postuleeService, ObjectMapper objectMapper, PostuleRepository postuleRepository, ServiceCallanalyse postuleeProducer) {
        this.postuleeService = postuleeService;
        this.objectMapper = objectMapper;
        this.postuleRepository = postuleRepository;
        this.postuleeProducer = postuleeProducer;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PostuleResponse> findPostule(@PathVariable Long id) {
        return postuleeService.findPostuleByid(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Postule not found with id: " + id ));
    }
    @PostMapping("/{id}")
    public ResponseEntity<PostuleResponse> addPostule(@RequestBody @Valid PostuleRequest request, @PathVariable Long id) throws JsonProcessingException {
        PostuleResponse postuleResponse=postuleeService.add(request,id).orElseThrow();
        postuleeProducer.fetchData(Integer.parseInt(String.valueOf(postuleResponse.getId()))).doOnNext(dataResponse -> {
            try {
                Postule savedPostule= postuleRepository.findById(postuleResponse.getId()).orElseThrow(()->{throw new ResourceNotFoundException("Postule not found with id: " + postuleResponse.getId());});
                savedPostule.setDescription(objectMapper.writeValueAsString(dataResponse.getData()));
                postuleRepository.save(savedPostule);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error serializing data response", e);
            }
        }).block();

        return ResponseEntity.status(HttpStatus.CREATED).body(postuleResponse);
    }
    @DeleteMapping("/{id}")
    public void deletePostule(@PathVariable Long id) {
        postuleeService.deletePostule(id);
    }
    @GetMapping
    public ResponseEntity<PageResponse<PostuleResponse>> findAllPostule(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(postuleeService.findAllPostule(page,size));
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
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkPostule(@RequestParam Long postId,
                                                @RequestParam Long etudiantId) {
        return ResponseEntity.ok(postuleeService.checkPostule(postId,etudiantId));
    }

/*---------------------------------------------------*/
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