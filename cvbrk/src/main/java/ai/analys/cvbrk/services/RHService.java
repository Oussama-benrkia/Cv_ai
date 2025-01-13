package ai.analys.cvbrk.services;

import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dao.RhRepository;
import ai.analys.cvbrk.dto.UserRequest;
import ai.analys.cvbrk.dto.UserResponse;
import ai.analys.cvbrk.entity.Rh;
import ai.analys.cvbrk.entity.User;
import ai.analys.cvbrk.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RHService {

    private final RhRepository rhRepository;
    private final UserMapper userMapper;

    @Autowired
    public RHService(RhRepository rhRepository, UserMapper userMapper) {
        this.rhRepository = rhRepository;
        this.userMapper = userMapper;
    }
    private Rh findRhById(Long id) {
        return (Rh) rhRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rh with ID " + id + " not found"));
    }
    private PageResponse<UserResponse> createPageResponse(Page<User> page) {
        List<UserResponse> list = page.getContent().stream()
                .map(userMapper::toResponse)
                .toList();
        return PageResponse.<UserResponse>builder()
                .content(list)
                .totalElements(page.getTotalElements())
                .number(page.getNumber())
                .last(page.isLast())
                .first(page.isFirst())
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .build();
    }
    public Optional<UserResponse> findById(Long id) {
        return Optional.ofNullable(userMapper.toResponse(findRhById(id)));
    }
    public PageResponse<UserResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> res = rhRepository.findAll(pageable);
        return createPageResponse(res);
    }
    public List<UserResponse> findAll() {
        return rhRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }
    public Optional<UserResponse> save(UserRequest request) {
        Rh rh = (Rh) userMapper.toEntity(request);
        rh = rhRepository.save(rh);
        return Optional.ofNullable(userMapper.toResponse(rh));
    }
    public Optional<UserResponse> update(UserRequest request, Long id) {
        Rh rh = findRhById(id);
        rh.setNom(request.getNom());
        rh.setEmail(request.getEmail());
        rh.setPassword(request.getPassword());
        rh.setImage(request.getImage());
        rh = rhRepository.save(rh);
        return Optional.ofNullable(userMapper.toResponse(rh));
    }
    public Optional<UserResponse> delete(Long id) {
        Rh rh = findRhById(id);
        rhRepository.delete(rh);
        return Optional.ofNullable(userMapper.toResponse(rh));
    }
}
