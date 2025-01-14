package ai.analys.cvbrk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvResponse {
    Long id;
    String titre;
    String path;
    String creat_at;
}
