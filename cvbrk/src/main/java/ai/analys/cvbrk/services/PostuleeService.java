package ai.analys.cvbrk.services;

import ai.analys.cvbrk.analyse.ServiceCallanalyse;
import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dao.CvRepository;
import ai.analys.cvbrk.dao.PostuleRepository;
import ai.analys.cvbrk.dto.PostuleRequest;
import ai.analys.cvbrk.dto.PostuleResponse;
import ai.analys.cvbrk.entity.Cv;
import ai.analys.cvbrk.entity.Post;
import ai.analys.cvbrk.entity.Postule;
import ai.analys.cvbrk.entity.User;
import ai.analys.cvbrk.mapper.PostuleMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostuleeService {
    private final PostuleRepository postuleRepository;
    private final UserService userService;
    private final PostuleMapper postuleMapper;
    private final PostService postService;
    private final CvRepository cvRepository;
    private final ObjectMapper objectMapper;

    private final ServiceCallanalyse postuleeProducer;


    public PostuleeService(PostuleRepository postuleRepository, UserService userService, PostuleMapper postuleMapper, PostService postService, CvRepository cvRepository, ObjectMapper objectMapper, ServiceCallanalyse postuleeProducer) {
        this.postuleRepository = postuleRepository;
        this.userService = userService;
        this.postuleMapper = postuleMapper;
        this.postService = postService;
        this.cvRepository = cvRepository;
        this.objectMapper = objectMapper;
        this.postuleeProducer = postuleeProducer;
    }


    private Postule findPostuleById(Long id) {
        return postuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postule with ID " + id + " not found"));
    }

    private PageResponse<PostuleResponse> createPageResponse(Page<Postule> page) {
        List<PostuleResponse> list = page.getContent().stream()
                .map(postuleMapper::toResponse)
                .toList();
        return PageResponse.<PostuleResponse>builder()
                .content(list)
                .totalElements(page.getTotalElements())
                .number(page.getNumber())
                .last(page.isLast())
                .first(page.isFirst())
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .build();
    }
    public Optional<PostuleResponse> findPostuleByid(Long id) {
        return Optional.ofNullable(postuleMapper.toResponse(this.findPostuleById(id)));
    }
    public Optional<PostuleResponse> add(PostuleRequest request, Long postid) throws JsonProcessingException {
        Post post = postService.findPostById(postid);
        Cv cv = cvRepository.findById(request.idcv())
                .orElseThrow(() -> new EntityNotFoundException("CV with ID " + request.idcv() + " not found"));

        if (checkPostule(postid, cv.getId())) {
            throw new IllegalStateException("This student has already postuled to this job");
        }

        Postule postule = Postule.builder()
                .etudiant(cv)
                .post(post)
                .message(request.mesaage())
                .build();

        Postule savedPostule = postuleRepository.save(postule);


        PostuleResponse postuleResponse = postuleMapper.toResponse(savedPostule);
        return Optional.ofNullable(postuleResponse);
    }


    public Optional<PostuleResponse> deletePostule(Long id) {
        Postule postule = findPostuleById(id);
        postuleRepository.delete(postule);
        return Optional.ofNullable(postuleMapper.toResponse(postule));
    }

    public PageResponse<PostuleResponse> findAllPostule(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Postule> posts = postuleRepository.findAll(pageable);
        return createPageResponse(posts);
    }

    public PageResponse<PostuleResponse> findAllByEtudiantId(int page, int size, Long etudiantId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Postule> posts = postuleRepository.findAll(pageable);
        List<Postule> filteredPosts = posts.getContent().stream()
                .filter(post -> post.getEtudiant().getId().equals(etudiantId))
                .toList();
        return createPageResponse(new org.springframework.data.domain.PageImpl<>(filteredPosts, pageable, filteredPosts.size()));
    }

    public List<PostuleResponse> findAllByEtudiantId(Long etudiantId) {
        return postuleRepository.findByEtudiantId(etudiantId).stream()
                .map(postuleMapper::toResponse)
                .toList();
    }

    public List<PostuleResponse> findAllByPostId(Long postId) {
        return postuleRepository.findByPostId(postId).stream()
                .map(postuleMapper::toResponse)
                .toList();
    }

    public PageResponse<PostuleResponse> findAllByPostId(int page, int size, Long postId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Postule> posts = postuleRepository.findAll(pageable);
        List<Postule> filteredPosts = posts.getContent().stream()
                .filter(post -> post.getPost().getId().equals(postId))
                .toList();
        return createPageResponse(new org.springframework.data.domain.PageImpl<>(filteredPosts, pageable, filteredPosts.size()));
    }

    public PageResponse<PostuleResponse> findAllMypostule(int page, int size) {
        // use current user to get my postule not all posts
        User use = userService.getCurrentUser();
        Pageable pageable = PageRequest.of(page, size);
        Page<Postule> posts = postuleRepository.findAll(pageable);
        List<Postule> filteredPosts = posts.getContent().stream()
                .filter(post -> post.getEtudiant().getId().equals(use.getId()))
                .toList();
        return createPageResponse(new org.springframework.data.domain.PageImpl<>(filteredPosts, pageable, filteredPosts.size()));
    }

    public List<PostuleResponse> findAllMypostule() {
        User use = userService.getCurrentUser();
        return postuleRepository.findByEtudiantId(use.getId()).stream()
                .map(postuleMapper::toResponse)
                .toList();
    }
    public boolean checkPostule(Long postId, Long etudiantId) {
        List<Postule> postules = postuleRepository.findByPostId(postId);
        if (postules == null || postules.isEmpty()) {
            return false;
        }
        return postules.stream()
                .anyMatch(postule -> postule.getEtudiant().getId().equals(etudiantId));

    }
}