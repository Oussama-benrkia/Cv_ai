package ai.analys.cvbrk.controllers;

import ai.analys.cvbrk.common.PageResponse;
import ai.analys.cvbrk.dto.CvRequest;
import ai.analys.cvbrk.dto.CvResponse;
import ai.analys.cvbrk.services.CvServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cvs")
public class CvController {

    private final CvServices cvServices;

    public CvController(CvServices cvServices) {
        this.cvServices = cvServices;
    }

    @PostMapping
    public ResponseEntity<CvResponse> createCv(@ModelAttribute CvRequest cvRequest) {
        Optional<CvResponse> createdCv = cvServices.createCv(cvRequest);
        return createdCv.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CvResponse> getCvById(@PathVariable Long id) {
        Optional<CvResponse> cvResponse = cvServices.findById(id);
        return cvResponse.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<PageResponse<CvResponse>> getAllCvsOfUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<CvResponse> response = cvServices.findAllofUser(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CvResponse>> getAllCvsOfUserWithoutPagination() {
        List<CvResponse> response = cvServices.findAllofUser();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCv(@PathVariable Long id) {
        cvServices.deleteCv(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<CvResponse>> searchCvsByTitre(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String titre
    ) {
        PageResponse<CvResponse> response = cvServices.findAllofUserByTitre(page, size, titre);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<CvResponse>> searchCvsByTitreWithoutPagination(@RequestParam String titre) {
        List<CvResponse> response = cvServices.findAllofUserByTitre(titre);
        return ResponseEntity.ok(response);
    }
}
