package ai.analys.cvbrk.mapper;

import ai.analys.cvbrk.dto.CvRequest;
import ai.analys.cvbrk.dto.CvResponse;
import ai.analys.cvbrk.entity.Cv;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
@Component
public class CvMapper implements Mapper<Cv, CvResponse,CvRequest> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final Environment environment;
    private final ObjectMapper objectMapper;

    @Autowired
    public CvMapper(Environment environment, ObjectMapper objectMapper) {
        this.environment = environment;
        this.objectMapper = objectMapper;
    }
    @Override
    public Cv toEntity(CvRequest query) {
        return Cv.builder()
                .titre(query.titre())
                .build();
    }

    @Override
    public CvResponse toResponse(Cv entity) {
        String serverAddress = environment.getProperty("server.address", "localhost");
        String serverPort = environment.getProperty("server.port", "8080");

        String Url = null;
        if (entity != null && entity.getPath() != null && !entity.getPath().isEmpty()) {
            Url = "http://" + serverAddress + ":" + serverPort + "/api/file/" + entity.getPath(); // Assuming image path is in /images
        }
        return CvResponse.builder()
                .creat_at(formatter.format(entity.getCreatAt()))
                .id(entity.getId())
                .titre(entity.getTitre())
                .path(Url)
                .build();
    }
}
