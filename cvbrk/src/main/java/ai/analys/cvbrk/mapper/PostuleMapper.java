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


    @Override
    public Postule toEntity(PostuleRequest data) {
        return Postule.builder()
                .description(data.description())
                .pourcentage(data.pourcentage())
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