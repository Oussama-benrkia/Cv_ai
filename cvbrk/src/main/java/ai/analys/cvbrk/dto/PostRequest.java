package ai.analys.cvbrk.dto;

import jakarta.validation.constraints.NotBlank;
import ai.analys.cvbrk.validation.OnCreate;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


public record PostRequest (
         @NotBlank(groups = OnCreate.class, message = "Titre must not be blank")
         String titre,
         @NotBlank(groups = OnCreate.class, message = "Description must not be blank")
         String description,
         @NotBlank(groups = OnCreate.class, message = "Keyword must not be blank")
         List<String> keyword,
         String lien,
         String email,
         MultipartFile image
)

{



}