package ai.analys.cvbrk.mapper;

import ai.analys.cvbrk.dto.PostRequest;
import ai.analys.cvbrk.dto.PostResponse;
import ai.analys.cvbrk.entity.Post;
import ai.analys.cvbrk.entity.Rh;
import ai.analys.cvbrk.images.ImagesFolder;
import ai.analys.cvbrk.images.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Component
public class PostMapper implements Mapper<Post, PostResponse, PostRequest> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final Environment environment;
    private final ImgService imgService;
    @Autowired
    public PostMapper(Environment environment, ImgService imgService) {
        this.environment = environment;
        this.imgService = imgService;
    }
    @Override
    public Post toEntity(PostRequest request) {
        Rh rh = new Rh();
        rh.setId(request.getRhId());
        Post post = new Post();
        post.setTitre(request.getTitre());
        post.setDescription(request.getDescription());
        if (request.getImage() != null && !request.getImage().isEmpty()) {
            byte[] imageBytes = decodeBase64Image(request.getImage());
            if (imageBytes != null) {
                String path = imgService.addImage(imageBytes, ImagesFolder.POST);
                post.setImage(path);
            }

        }
        post.setRh(rh);
        return post;
    }
    private byte[] decodeBase64Image(String base64Image){
        try {
            return  Base64.getDecoder().decode(base64Image);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public PostResponse toResponse(Post post) {
        String serverAddress = environment.getProperty("server.address", "localhost");
        String serverPort = environment.getProperty("server.port", "8080");
        String imageUrl = "";
        if (post.getImage() != null && !post.getImage().isEmpty()) {
            imageUrl = "http://" + serverAddress + ":" + serverPort + "/api/image/" + post.getImage();
        }
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setTitre(post.getTitre());
        response.setDescription(post.getDescription());
        response.setImage(imageUrl);
        response.setCreatedAt(post.getCreatAt());
        return response;
    }
}