package ai.analys.cvbrk.images;

import ai.analys.cvbrk.images.ImagesFolder;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.Base64;

@Service
public class ImgService {

    private static final String UPLOADDIRECTORY = "uploads";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImgService.class);


    public byte[] imageToByte(String filename) throws IOException {
        Path uploadDirectoryPath = Paths.get(System.getProperty("user.dir"), UPLOADDIRECTORY);
        Path filePath = uploadDirectoryPath.resolve(filename);
        return Files.readAllBytes(filePath);
    }

    public String addImage(byte[] fileBytes, ImagesFolder folder) {
        if (fileBytes == null || fileBytes.length == 0) {
            return "";
        }
        try {
            String uniqueFileName = this.genrator(folder.toString()) + ".jpg";
            Path uploadDirPath = Paths.get(UPLOADDIRECTORY).toAbsolutePath();
            Path folderPath = Paths.get(folder.getValue());
            Path filePath = uploadDirPath.resolve(folderPath).resolve(uniqueFileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, fileBytes);
            return folder.getValue() + "/" + uniqueFileName;
        } catch (IOException e) {
            log.error("Error saving file.", e);
            return "";
        }
    }

    public Boolean deleteImage(String fileName) {
        Path uploadDirPath = Paths.get(UPLOADDIRECTORY).toAbsolutePath();
        Path filePath = uploadDirPath.resolve(fileName);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return true;
            }
        } catch (IOException e) {
            log.error("Error deleting file: {}", filePath, e);
        }
        return false;
    }


    private String genrator(String folder) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime dateTime = LocalDateTime.now();
        long secondsSinceEpoch = dateTime.toEpochSecond(ZoneOffset.UTC);
        String input = String.valueOf(secondsSinceEpoch);
        int midpoint = input.length() / 2;
        String firstPart = input.substring(0, midpoint);
        String secondPart = input.substring(midpoint);
        return firstPart + uuid + folder + secondPart;
    }
}