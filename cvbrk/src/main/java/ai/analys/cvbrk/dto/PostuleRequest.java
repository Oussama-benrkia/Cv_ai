package ai.analys.cvbrk.dto;

import jakarta.validation.constraints.NotNull;

public record PostuleRequest (
        @NotNull
        Long idcv,
        String mesaage
){
}
