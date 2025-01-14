package ai.analys.cvbrk.mapper;

import ai.analys.cvbrk.dto.PostuleRequest;
import ai.analys.cvbrk.dto.PostuleResponse;
import ai.analys.cvbrk.entity.Cv;
import ai.analys.cvbrk.entity.Post;
import ai.analys.cvbrk.entity.Postule;
import ai.analys.cvbrk.entity.User;
import ai.analys.cvbrk.services.PostService;
import ai.analys.cvbrk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostuleMapper implements Mapper<Postule, PostuleResponse, PostuleRequest> {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostuleMapper(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public Postule toEntity(PostuleRequest data) {
        Post post = postService.findPostById(data.postId());
        User user = userService.getCurrentUser();
        List<Cv> cvs = user.getCvs();
        if (cvs == null || cvs.isEmpty()) {
            throw new IllegalStateException("User must have at least one cv to postule");
        }
        Cv cv= cvs.get(0);
        return Postule.builder()
                .post(post)
                .description(data.description())
                .pourcentage(data.pourcentage())
                .etudiant(cv)
                .build();
    }

    @Override
    public PostuleResponse toResponse(Postule entity) {
        PostuleResponse response = new PostuleResponse();
        response.setId(entity.getId());
        response.setPostId(entity.getPost().getId());
        response.setDescription(entity.getDescription());
        response.setPourcentage(entity.getPourcentage());
        response.setCreatAt(entity.getCreatAt());
        response.setEtudiantId(entity.getEtudiant().getId());
        return response;
    }
}