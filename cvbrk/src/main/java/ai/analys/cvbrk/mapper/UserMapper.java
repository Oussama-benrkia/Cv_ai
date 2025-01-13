package ai.analys.cvbrk.mapper;

import ai.analys.cvbrk.dto.UserRequest;
import ai.analys.cvbrk.dto.UserResponse;
import ai.analys.cvbrk.entity.User;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserResponse, UserRequest> {
    private final Environment environment;

    public UserMapper(Environment environment) {
        this.environment = environment;
    }

    @Override
    public User toEntity(final UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        return User.builder()
                .nom(userRequest.nom())
                .prenom(userRequest.prenom())
                .email(userRequest.email())
                .build();
    }

    @Override
    public UserResponse toResponse(final User user) {
        String serverAddress = environment.getProperty("server.address", "localhost");
        String serverPort = environment.getProperty("server.port", "8080");

        String imageUrl = null;
        if (user != null && user.getImage() != null && !user.getImage().isEmpty()) {
            imageUrl = "http://" + serverAddress + ":" + serverPort + "/api/image/" + user.getImage(); // Assuming image path is in /images
        }
        return user == null ? null : UserResponse.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .image(imageUrl)  // Only set the image URL if available
                .build();
    }
}