package ai.analys.cvbrk.dto;

import ai.analys.cvbrk.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record CvRequest(
        @NotBlank(groups = OnCreate.class, message = "Titre must not be blank")
        String titre,
        @NotNull(groups = OnCreate.class, message = "file must not be blank")
        MultipartFile file
) {
}
