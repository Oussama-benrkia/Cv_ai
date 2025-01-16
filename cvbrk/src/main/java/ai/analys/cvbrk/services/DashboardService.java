package ai.analys.cvbrk.services;

import ai.analys.cvbrk.dao.CvRepository;
import ai.analys.cvbrk.dao.PostRepository;
import ai.analys.cvbrk.dao.UserRepository;
import ai.analys.cvbrk.dto.DashboardDto;
import ai.analys.cvbrk.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CvRepository cvRepository;

    @Autowired
    private PostRepository postRepository;

    public DashboardDto getDashboardStats() {
        long totalUsers = userRepository.count();
        long totalCvs = cvRepository.count();
        long totalPosts = postRepository.count();
        long totalEtudiantUsers = userRepository.countByRole(Role.ETUDIANT);
        long totalRhUsers = userRepository.countByRole(Role.RH);

        return new DashboardDto(totalUsers, totalCvs, totalPosts, totalEtudiantUsers, totalRhUsers);
    }
}
