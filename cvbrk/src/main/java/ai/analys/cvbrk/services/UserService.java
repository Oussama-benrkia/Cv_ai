package ai.analys.cvbrk.services;


import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dao.UserRepository;
import ai.analys.cvbrk.dto.UserRequest;
import ai.analys.cvbrk.dto.UserResponse;
import ai.analys.cvbrk.entity.Role;
import ai.analys.cvbrk.entity.User;
import ai.analys.cvbrk.exception.OperationNotPermittedException;
import ai.analys.cvbrk.images.ImagesFolder;
import ai.analys.cvbrk.images.ImgService;
import ai.analys.cvbrk.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final ImgService imgService;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepo, UserMapper userMapper, ImgService imgService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.imgService = imgService;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    public Optional<UserResponse> findById(Long id) {
        return userRepo.findById(id).map(userMapper::toResponse);
    }

    public PageResponse<UserResponse> findAll(int page, int size) {
        Page<User> users = userRepo.findAll(PageRequest.of(page, size));
        return createPageResponse(users);
    }

    public List<UserResponse> findAll() {
        return userRepo.findAll().stream().map(userMapper::toResponse).toList();
    }

    public Optional<UserResponse> save(UserRequest request) {
        if (userRepo.existsByEmail(request.email())) {
            throw new OperationNotPermittedException("User with email " + request.email() + " already exists");
        }
        String path = imgService.addImage(request.file(), ImagesFolder.USER);

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Optional.ofNullable(request.role()).map(Role::valueOf).orElse(Role.ETUDIANT));
        user.setImage(path);

        User savedUser = userRepo.save(user);
        return Optional.of(userMapper.toResponse(savedUser));
    }

    public Optional<UserResponse> update(UserRequest request, Long id) {
        User user = findUserById(id);
        boolean updated = updateUserFields(request, user);

        if (updated) {
            user = userRepo.save(user);
        }

        return Optional.of(userMapper.toResponse(user));
    }

    public Optional<UserResponse> delete(Long id) {
        User user = findUserById(id);
        userRepo.delete(user);
        if (user.getImage() != null && !user.getImage().isEmpty()) {
            imgService.deleteImage(user.getImage());
        }
        return Optional.of(userMapper.toResponse(user));
    }


    public Optional<UserResponse> getProfile() {
        return Optional.of(userMapper.toResponse(getCurrentUser()));
    }

    public Optional<UserResponse> updateProfile(UserRequest request) {
        User currentUser = getCurrentUser();
        return update(request, currentUser.getId());
    }

    public List<UserResponse> findAllWithSearchAndRole(String search, String role) {
        if (role.isEmpty()) {
            return userRepo.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, search)
                    .stream().map(userMapper::toResponse).toList();
        }

        Role userRole = Role.valueOf(role);
        return userRepo.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCaseAndRole(
                        search, search, search, userRole)
                .stream().map(userMapper::toResponse).toList();
    }

    public PageResponse<UserResponse> findAllWithSearchAndRole(int page, int size, String search, String role) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = role.isEmpty() ?
                userRepo.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, search, pageable) :
                userRepo.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCaseAndRole(
                        search, search, search, Role.valueOf(role), pageable);
        return createPageResponse(users);
    }

    public List<UserResponse> findAllWithRole(String role) {
        return userRepo.findAllByRole(role)
                .stream().map(userMapper::toResponse).toList();
    }

    public PageResponse<UserResponse> findAllWithRole(int page, int size, String role) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepo.findAllByRole(role, pageable);
        return createPageResponse(users);
    }


    private User findUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails userDetails)) {
            throw new IllegalStateException("Principal is not an instance of UserDetails");
        }

        return userRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("User not found with email: " + userDetails.getUsername()));
    }

    private boolean updateUserFields(UserRequest request, User user) {
        boolean updated = false;

        if (isUpdated(request.nom(), user.getNom())) {
            user.setNom(request.nom());
            updated = true;
        }


        if (isUpdated(request.email(), user.getEmail())) {
            if (userRepo.existsByEmail(request.email())) {
                throw new OperationNotPermittedException("User with email " + request.email() + " already exists");
            }
            user.setEmail(request.email());
            updated = true;
        }


        if (request.password() != null && !request.password().isBlank() &&
                !passwordEncoder.matches(request.password(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.password()));
            updated = true;
        }

        if (isUpdated(request.role(), user.getRole().name())) {
            user.setRole(Role.valueOf(request.role()));
            updated = true;
        }
        if (request.file() != null && !request.file().isEmpty()) {
            if (user.getImage() != null && !user.getImage().isEmpty()) {
                imgService.deleteImage(user.getImage());
            }
            String path = imgService.addImage(request.file(), ImagesFolder.USER);
            user.setImage(path);
            updated = true;
        }

        return updated;
    }

    private PageResponse<UserResponse> createPageResponse(Page<User> page) {
        List<UserResponse> content = page.getContent().stream().map(userMapper::toResponse).toList();

        return PageResponse.<UserResponse>builder()
                .totalElements(page.getTotalElements())
                .number(page.getNumber())
                .last(page.isLast())
                .first(page.isFirst())
                .content(content)
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .build();
    }

    private boolean isUpdated(String newValue, String oldValue) {
        return newValue != null && !newValue.isBlank() && !newValue.equals(oldValue);
    }
}
