package ai.analys.cvbrk;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class CvbrkApplication {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CvbrkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CvbrkApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            String[] directories = {
                    "uploads",
                    "uploads/user",
                    "uploads/pdf",
                    "uploads/post"
            };

            for (String dir : directories) {
                Path path = Paths.get(dir);
                if (!Files.exists(path)) {
                    try {
                        Files.createDirectories(path);
                        log.info("Created directory: {}", dir);
                    } catch (IOException e) {
                        log.error("Error creating directory: {}", dir, e);
                    }
                } else {
                    log.info("Directory already exists: {}", dir);
                }
            }
        };
    }
}
