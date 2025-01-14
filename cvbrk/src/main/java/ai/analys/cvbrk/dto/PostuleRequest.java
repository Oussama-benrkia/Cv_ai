package ai.analys.cvbrk.dto;

public record PostuleRequest (
        Long postId,
        String description,
        double pourcentage
){
}
