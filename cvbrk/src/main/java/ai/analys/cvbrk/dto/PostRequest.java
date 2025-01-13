package ai.analys.cvbrk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ai.analys.cvbrk.validation.OnCreate;
import org.springframework.web.multipart.MultipartFile;


import java.util.Objects;


public record PostRequest (
        @NotBlank(groups = OnCreate.class, message = "Titre must not be blank")
         String titre,
                @NotBlank(groups = OnCreate.class, message = "Description must not be blank")
 String description,

 MultipartFile image,
@NotNull(groups = OnCreate.class, message = "Rh ID must not be null")
 Long rhId
)

{



}