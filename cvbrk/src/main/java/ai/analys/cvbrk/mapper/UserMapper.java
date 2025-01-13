package ai.analys.cvbrk.mapper;

import ai.analys.cvbrk.dto.UserRequest;
import ai.analys.cvbrk.dto.UserResponse;
import ai.analys.cvbrk.entity.Rh;
import ai.analys.cvbrk.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserResponse, UserRequest> {
    @Override
    public User toEntity(UserRequest userRequest) {
        // Create new Rh object and cast it to user
        Rh rh = new Rh();
        rh.setNom(userRequest.getNom());
        rh.setEmail(userRequest.getEmail());
        rh.setPassword(userRequest.getPassword());
        rh.setImage(userRequest.getImage());
        return rh;
    }
    @Override
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setNom(user.getNom());
        response.setEmail(user.getEmail());
        response.setImage(user.getImage());
        response.setCreatAt(user.getCreatAt());
        return response;
    }
}