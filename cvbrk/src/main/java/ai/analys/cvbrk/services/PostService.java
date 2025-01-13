package ai.analys.cvbrk.services;

import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dao.PostRepository;
import ai.analys.cvbrk.dto.PostRequest;
import ai.analys.cvbrk.dto.PostResponse;
import ai.analys.cvbrk.entity.Post;
import ai.analys.cvbrk.images.ImagesFolder;
import ai.analys.cvbrk.images.ImgService;
import ai.analys.cvbrk.mapper.PostMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Base64;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final ImgService imgService;
    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper, ImgService imgService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.imgService = imgService;
    }
    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with ID " + id + " not found"));
    }
    private PageResponse<PostResponse> createPageResponse(Page<Post> page) {
        List<PostResponse> list = page.getContent().stream()
                .map(postMapper::toResponse)
                .toList();
        return PageResponse.<PostResponse>builder()
                .content(list)
                .totalElements(page.getTotalElements())
                .number(page.getNumber())
                .last(page.isLast())
                .first(page.isFirst())
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .build();
    }
    public Optional<PostResponse> findById(Long id) {
        return Optional.ofNullable(postMapper.toResponse(findPostById(id)));
    }
    public PageResponse<PostResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> res = postRepository.findAll(pageable);
        return createPageResponse(res);
    }
    public List<PostResponse> findAll() {
        return postRepository.findAll().stream()
                .map(postMapper::toResponse)
                .toList();
    }
    public Optional<PostResponse> save(PostRequest request) {
        Post post = postMapper.toEntity(request);
        if (post.getDescription() !=null && post.getDescription().length() > 5000){
            post.setDescription(post.getDescription().substring(0,5000));
        }
        Post savedPost = postRepository.save(post);
        return Optional.ofNullable(postMapper.toResponse(savedPost));
    }
    public Optional<PostResponse> update(PostRequest request, Long id) {
        Post post = findPostById(id);
        boolean change = false;
       /* if (request.getTitre() != null && !request.getTitre().isEmpty() && !request.getTitre().equals(post.getTitre())) {
            post.setTitre(request.getTitre());
            change = true;
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty() && !request.getDescription().equals(post.getDescription())) {
            String description= request.getDescription();
            if(description.length()>5000){
                description=description.substring(0, 5000);
            }
            post.setDescription(description);
            change = true;
        }
        if (request.getImage() != null && !request.getImage().isEmpty()) {
            if (post.getImage() != null && !post.getImage().isEmpty()) {
                imgService.deleteImage(post.getImage());
            }
            String path = imgService.addImage(decodeBase64Image(request.getImage()), ImagesFolder.POST);
            post.setImage(path);
            change = true;
        }
        if (change) {
            post= postRepository.save(post);
        }*/
        return Optional.ofNullable(postMapper.toResponse(post));
    }
    private byte[] decodeBase64Image(String base64Image){
        try {
            return  Base64.getDecoder().decode(base64Image);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
    public Optional<PostResponse> delete(Long id) {
        Post post = findPostById(id);
        if(post.getImage() != null && !post.getImage().isEmpty()) {
            imgService.deleteImage(post.getImage());
        }
        postRepository.delete(post);
        return Optional.ofNullable(postMapper.toResponse(post));
    }
    public PageResponse<PostResponse> findAllByRhId(int page, int size, Long rhId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> filteredPosts = posts.getContent().stream()
                .filter(post -> post.getRh().getId().equals(rhId))
                .toList();
        return createPageResponse(new org.springframework.data.domain.PageImpl<>(filteredPosts,pageable, filteredPosts.size()));
    }
    public List<PostResponse> findAllByRhId(Long rhId) {
        return postRepository.findByRhId(rhId).stream()
                .map(postMapper::toResponse)
                .toList();
    }
}