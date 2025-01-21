package ai.analys.cvbrk;

import ai.analys.cvbrk.dto.UserRequest;
import ai.analys.cvbrk.dto.UserResponse;
import ai.analys.cvbrk.entity.Role;
import ai.analys.cvbrk.services.UserService;
import org.apache.catalina.core.ApplicationContext;
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
    CommandLineRunner commandLineRunnerdir() {
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
    @Bean
    CommandLineRunner commandLineRunnerUser(UserService userService) {
        return args -> {
            log.info("Starting user initialization...");

            UserResponse user = userService.findById(1L).orElse(null);
            if (user == null) {
                log.info("Admin user not found. Creating default admin user...");
                UserRequest adminRequest = new UserRequest("admin", "admin", "kay", "adminadmin", Role.ADMIN.toString(), null);
                userService.save(adminRequest);
                log.info("Admin user created successfully with username: {}", adminRequest.email());
            } else {
                log.info("Admin user already exists with username: {}", user.getEmail());
            }

            user = userService.findById(2L).orElse(null);
            if (user == null) {
                log.info("RH user not found. Creating default RH user...");
                UserRequest rhRequest = new UserRequest("rh", "rh", "rh@rh.com", "123456789", Role.RH.toString(), null);
                userService.save(rhRequest);
                log.info("RH user created successfully with username: {}", rhRequest.email());
            } else {
                log.info("RH user already exists with username: {}", user.getEmail());
            }

            log.info("User initialization process completed.");
        };
    }

}
