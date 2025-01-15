package ai.analys.cvbrk.dto;

import jakarta.validation.constraints.NotBlank;
import ai.analys.cvbrk.validation.OnCreate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


public record PostRequest (
         @NotBlank(groups = OnCreate.class, message = "Titre must not be blank")
         String titre,
         @NotBlank(groups = OnCreate.class, message = "Description must not be blank")
         String description,
         @NotNull(groups = OnCreate.class, message = "Keyword list must not be empty")
         @Size(min = 1, message = "At least one keyword is required")
         List<String> keyword,
         String lien,
         String email,
         MultipartFile image
)

{



}