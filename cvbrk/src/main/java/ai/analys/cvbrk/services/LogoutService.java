package ai.analys.cvbrk.services;

import ai.analys.cvbrk.dao.TokenRepository;
import ai.analys.cvbrk.entity.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogoutService implements LogoutHandler {
    private final TokenRepository repToken;

    public LogoutService(TokenRepository repToken) {
        this.repToken = repToken;
    }

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String jwt = authHeader.substring(7);
        Token stored = repToken.findByToken(jwt).orElse(null);

        if (stored != null) {
            System.out.println(stored.getToken());
            stored.setExpired(true);
            stored.setRevoked(true);
            repToken.save(stored); // why in this save is not do
        }
        if (stored != null && stored.getRefreshToken() != null) {
            Token refresh = repToken.findByToken(stored.getRefreshToken()).orElse(null);
            if (refresh != null) {
                System.out.println(refresh.getToken());
                refresh.setExpired(true);
                refresh.setRevoked(true);
                repToken.save(refresh); // Persist the changes
            }
        }

    }
}
