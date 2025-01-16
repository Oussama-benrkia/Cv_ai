package ai.analys.cvbrk.dto;


// DTO class
public class DashboardDto {

    private long totalUsers;
    private long totalCvs;
    private long totalPosts;
    private long totalEtudiantUsers;
    private long totalRhUsers;

    public DashboardDto(long totalUsers, long totalCvs, long totalPosts, long totalEtudiantUsers, long totalRhUsers) {
        this.totalUsers = totalUsers;
        this.totalCvs = totalCvs;
        this.totalPosts = totalPosts;
        this.totalEtudiantUsers = totalEtudiantUsers;
        this.totalRhUsers = totalRhUsers;
    }

    // Getters and Setters

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalCvs() {
        return totalCvs;
    }

    public void setTotalCvs(long totalCvs) {
        this.totalCvs = totalCvs;
    }

    public long getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(long totalPosts) {
        this.totalPosts = totalPosts;
    }

    public long getTotalEtudiantUsers() {
        return totalEtudiantUsers;
    }

    public void setTotalEtudiantUsers(long totalEtudiantUsers) {
        this.totalEtudiantUsers = totalEtudiantUsers;
    }

    public long getTotalRhUsers() {
        return totalRhUsers;
    }

    public void setTotalRhUsers(long totalRhUsers) {
        this.totalRhUsers = totalRhUsers;
    }
}

