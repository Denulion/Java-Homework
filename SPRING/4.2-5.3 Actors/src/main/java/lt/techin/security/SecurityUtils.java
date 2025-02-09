package lt.techin.security;

import lt.techin.model.User;
import org.springframework.security.core.Authentication;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static boolean isAdminOrOwner(Authentication authentication, long userId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        User user = (User) authentication.getPrincipal();
        return user.getId() == userId || user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}