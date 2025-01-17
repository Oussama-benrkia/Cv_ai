package ai.analys.cvbrk.controllers;

import ai.analys.cvbrk.dto.DashboardDto;
import ai.analys.cvbrk.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardDto> getDashboardStats() {
        DashboardDto dashboardDto = dashboardService.getDashboardStats();
        return ResponseEntity.ok(dashboardDto);
    }
}