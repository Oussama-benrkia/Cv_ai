package ai.analys.cvbrk.mapper;

import ai.analys.cvbrk.dto.RegisterRequest;
import ai.analys.cvbrk.entity.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {
    public User toEntity(final RegisterRequest.RegisterRequestRegister userRequest) {
        if (userRequest == null) {
            return null;
        }
        return User.builder()
                .nom(userRequest.getNom())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .prenom(userRequest.getPrenom())
                .build();
    }
}
