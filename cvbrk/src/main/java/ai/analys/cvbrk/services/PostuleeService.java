package ai.analys.cvbrk.services;

import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dao.PostuleRepository;
import ai.analys.cvbrk.dto.PostResponse;
import ai.analys.cvbrk.dto.PostuleRequest;
import ai.analys.cvbrk.entity.Postule;
import ai.analys.cvbrk.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostuleeService {
    private final PostuleRepository postuleRepository;
    private final UserService userService;
    public PostuleeService(PostuleRepository postuleRepository, UserService userService) {
        this.postuleRepository = postuleRepository;
        this.userService = userService;
    }

    public Optional<PostResponse> add(PostuleRequest request) {
        User user=userService.getCurrentUser();
        return null;
    }

    public Optional<PostResponse> deletePostule(PostuleRequest request) {
        return null;

    }
    public PageResponse<PostResponse> findAllPostule(Long page,Long size) {

        return null;
    }
}
