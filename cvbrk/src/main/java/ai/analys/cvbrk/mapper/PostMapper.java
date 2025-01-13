package ai.analys.cvbrk.mapper;

import ai.analys.cvbrk.dto.PostRequest;
import ai.analys.cvbrk.dto.PostResponse;
import ai.analys.cvbrk.entity.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PostMapper implements Mapper<Post, PostResponse, PostRequest> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final Environment environment;
    private final ObjectMapper objectMapper;

    @Autowired
    public PostMapper(Environment environment, ObjectMapper objectMapper) {
        this.environment = environment;
        this.objectMapper = objectMapper;
    }

    @Override
    public Post toEntity(PostRequest data) {
        String keywordJson;
        try {
            keywordJson = objectMapper.writeValueAsString(data.keyword());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while serializing keywords", e);
        }

        return Post.builder()
                .titre(data.titre())
                .description(data.description())
                .keyword(keywordJson)
                .lien(data.lien()!=null?data.lien():"")
                .email(data.email()!=null?data.email():"")
                .build();
    }

    @Override
    public PostResponse toResponse(Post entity) {
        String serverAddress = environment.getProperty("server.address", "localhost");
        String serverPort = environment.getProperty("server.port", "8080");

        String imageUrl = (entity.getImage() != null)
                ? String.format("http://%s:%s/api/images/%s", serverAddress, serverPort, entity.getImage())
                : null;

        List<String> keywords;
        try {
            keywords = objectMapper.readValue(entity.getKeyword(), List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while deserializing keywords", e);
        }

        return PostResponse.builder()
                .id(entity.getId())
                .titre(entity.getTitre())
                .description(entity.getDescription())
                .createdAt(entity.getCreatAt())
                .keyword(keywords)
                .Lien(entity.getLien())
                .email(entity.getEmail())
                .image(imageUrl)
                .build();
    }
}
