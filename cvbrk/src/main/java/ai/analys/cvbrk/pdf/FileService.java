package ai.analys.cvbrk.pdf;

import ai.analys.cvbrk.images.ImagesFolder;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class FileService {

    private static final String UPLOADDIRECTORY = "uploads";
    private static final long MAX_FILE_SIZE_MB = 5 * 1024 * 1024; // 5 Mo en octets
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(FileService.class);

    public byte[] fileToByte(String filename) throws IOException {
        Path uploadDirectoryPath = Paths.get(System.getProperty("user.dir"), UPLOADDIRECTORY);
        Path filePath = uploadDirectoryPath.resolve(filename);
        return Files.readAllBytes(filePath);
    }

    public String addFile(MultipartFile file, String nom) {
        if (file == null || file.isEmpty()) {
            log.warn("Le fichier est vide ou nul.");
            return "";
        }

        try {
            // Vérification de la taille du fichier
            if (file.getSize() > MAX_FILE_SIZE_MB) {
                log.error("Le fichier dépasse la taille maximale autorisée de 5 Mo.");
                return "";
            }

            // Vérification de l'extension
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null) {
                throw new IllegalArgumentException("Nom du fichier nul.");
            }
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
            if (!isValidExtension(fileExtension)) {
                log.error("Type de fichier non supporté : {}", fileExtension);
                return "";
            }

            // Génération d'un nom de fichier unique
            String uniqueFileName = this.generator(ImagesFolder.PDF.getValue(), nom) + "." + fileExtension;
            Path uploadDirPath = Paths.get(UPLOADDIRECTORY).toAbsolutePath();
            Path folderPath = Paths.get(ImagesFolder.PDF.getValue());
            Path filePath = uploadDirPath.resolve(folderPath).resolve(uniqueFileName);

            // Création des répertoires si nécessaire
            Files.createDirectories(filePath.getParent());
            file.transferTo(filePath.toFile());
            return ImagesFolder.PDF.getValue() + "/" + uniqueFileName;
        } catch (IOException e) {
            log.error("Erreur lors de l'enregistrement du fichier : {}", file.getOriginalFilename(), e);
            return ""; // Retourne une chaîne vide en cas d'échec
        } catch (IllegalArgumentException e) {
            log.error("Nom de fichier invalide : {}", e.getMessage());
            return "";
        }
    }

    public Boolean deleteFile(String fileName) {
        Path filePath = Paths.get(System.getProperty("user.dir"), UPLOADDIRECTORY, fileName); // Construction dynamique du chemin du fichier
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath); // Suppression du fichier
                return true;
            }
        } catch (IOException e) {
            log.error("Erreur lors de la suppression du fichier : {}", filePath, e);
        }
        return false;
    }

    private String generator(String folder, String nom) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime dateTime = LocalDateTime.now();
        long secondsSinceEpoch = dateTime.toEpochSecond(ZoneOffset.UTC);
        String input = String.valueOf(secondsSinceEpoch);
        int midpoint = input.length() / 2;
        String firstPart = input.substring(0, midpoint);
        String secondPart = input.substring(midpoint);
        return nom + firstPart + uuid + folder + secondPart;
    }

    private boolean isValidExtension(String extension) {
        return extension.equals("pdf") || extension.equals("docx") || extension.equals("doc");
    }
}
