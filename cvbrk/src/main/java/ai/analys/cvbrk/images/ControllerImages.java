package ai.analys.cvbrk.images;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ControllerImages {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ControllerImages.class);
    private final ImgService imgService;

    public ControllerImages(ImgService imgService) {
        this.imgService = imgService;
    }

    @GetMapping("/{folder}/{file}")
    public ResponseEntity<byte[]> image(@PathVariable String folder, @PathVariable String file) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Set appropriate content type
                .body(imgService.imageToByte(folder + "/" + file));
    }
}
