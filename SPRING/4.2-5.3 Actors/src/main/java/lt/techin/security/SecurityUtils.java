package lt.techin.security;

import lt.techin.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static boolean isAdminOrOwner(Authentication authentication, long userId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

//        User user = (User) authentication.getPrincipal();
//        return user.getId() == userId || user.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals("SCOPE_ROLE_ADMIN"));

        if (!(authentication.getPrincipal() instanceof Jwt jwt)) {
            return false;
        }

        List<String> roles = jwt.getClaimAsStringList("scope");

        Long tokenUserId = jwt.getClaim("user_id");

        return roles.contains("SCOPE_ROLE_ADMIN") || (tokenUserId != null && tokenUserId == userId);
    }
}