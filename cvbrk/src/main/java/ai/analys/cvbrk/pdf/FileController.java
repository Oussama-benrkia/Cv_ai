package ai.analys.cvbrk.pdf;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {
    private final FileService imgService;

    public FileController(FileService imgService) {
        this.imgService = imgService;
    }

    @GetMapping("/{folder}/{file}")
    public ResponseEntity<byte[]> getFile(@PathVariable String folder, @PathVariable String file) throws IOException {
        String fileExtension = file.substring(file.lastIndexOf(".") + 1).toLowerCase();

        byte[] fileData = imgService.fileToByte(folder + "/" + file);
        MediaType mediaType = getMediaType(fileExtension);

        if (mediaType == null) {
            return ResponseEntity.badRequest().body(null); // Retourne une erreur si le type est non supporté
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file + "\"") // Ou "attachment" pour le téléchargement
                .body(fileData);
    }

    private MediaType getMediaType(String extension) {
        switch (extension) {
            case "pdf":
                return MediaType.APPLICATION_PDF;
            case "docx":
                return MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            case "doc":
                return MediaType.parseMediaType("application/msword");
            default:
                return null;
        }
    }
}
