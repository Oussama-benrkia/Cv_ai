package ai.analys.cvbrk.services;

import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dao.CvRepository;
import ai.analys.cvbrk.dto.CvRequest;
import ai.analys.cvbrk.dto.CvResponse;
import ai.analys.cvbrk.entity.Cv;
import ai.analys.cvbrk.entity.User;
import ai.analys.cvbrk.mapper.CvMapper;
import ai.analys.cvbrk.pdf.FileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CvServices {

    private final FileService fileService;
    private final UserService userService;
    private final CvRepository cvRepository;
    private final CvMapper cvMapper;

    public CvServices(FileService fileService, UserService userService, CvRepository cvRepository, CvMapper cvMapper) {
        this.fileService = fileService;
        this.userService = userService;
        this.cvRepository = cvRepository;
        this.cvMapper = cvMapper;
    }

    private PageResponse<CvResponse> createPageResponse(Page<Cv> page) {
        List<CvResponse> list = page.getContent().stream()
                .map(cvMapper::toResponse)
                .toList();
        return PageResponse.<CvResponse>builder()
                .content(list)
                .totalElements(page.getTotalElements())
                .number(page.getNumber())
                .last(page.isLast())
                .first(page.isFirst())
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .build();
    }

    private Cv findCvById(Long id) {
        return cvRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CV with ID " + id + " not found"));
    }

    public Optional<CvResponse> createCv(CvRequest cvRequest) {
        User currentUser = userService.getCurrentUser();
        String filePath = fileService.addFile(cvRequest.file(), cvRequest.titre());
        Cv cv = Cv.builder()
                .titre(cvRequest.titre())
                .etudiant(currentUser)
                .path(filePath)
                .build();
        Cv savedCv = cvRepository.save(cv);
        return Optional.ofNullable(cvMapper.toResponse(savedCv));
    }

    public Optional<CvResponse> findById(Long id) {
        Cv cv = findCvById(id);
        return Optional.ofNullable(cvMapper.toResponse(cv));
    }

    public PageResponse<CvResponse> findAllofUser(int page, int size) {
        User currentUser = userService.getCurrentUser();
        Pageable pageable = PageRequest.of(page, size);
        Page<Cv> cvs = cvRepository.findAllByEtudiant(currentUser, pageable);
        return createPageResponse(cvs);
    }

    public List<CvResponse> findAllofUser() {
        User currentUser = userService.getCurrentUser();
        List<Cv> cvs = cvRepository.findAllByEtudiant(currentUser);
        return cvs.stream().map(cvMapper::toResponse).toList();
    }

    public void deleteCv(Long id) {
        Cv cv = findCvById(id);
        cvRepository.delete(cv);
        fileService.deleteFile(cv.getPath());
    }

    public PageResponse<CvResponse> findAllofUserByTitre(int page, int size, String titre) {
        User currentUser = userService.getCurrentUser();
        Pageable pageable = PageRequest.of(page, size);
        Page<Cv> cvs = cvRepository.findAllByEtudiantAndTitreContainingIgnoreCase(currentUser, titre, pageable);
        return createPageResponse(cvs);
    }

    public List<CvResponse> findAllofUserByTitre(String titre) {
        User currentUser = userService.getCurrentUser();
        List<Cv> cvs = cvRepository.findAllByEtudiantAndTitreContainingIgnoreCase(currentUser, titre);
        return cvs.stream().map(cvMapper::toResponse).toList();
    }
}
