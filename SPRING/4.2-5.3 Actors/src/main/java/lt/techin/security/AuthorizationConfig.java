//package lt.techin.security;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.AuthorizationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.function.Supplier;
//
//@Component
//public class AuthorizationConfig implements AuthorizationManager<RequestAuthorizationContext> {
//
//    @Override
//    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context){
//        HttpServletRequest request = context.getRequest();
//        String method = request.getMethod();
//        String path = request.getRequestURI();
//
//        if(method.equals("POST") && path.startsWith("/api/users")) {
//            return new AuthorizationDecision(true);
//        }
//        if(method.equals("GET") && path.startsWith("/api/users")){
//            return new AuthorizationDecision(hasRole(authentication.get(), "ADMIN"));
//        }
//        if (method.equals("PUT") || method.equals("DELETE") && path.startsWith("/api/users")){
//            return new AuthorizationDecision((hasRole(authentication.get(), "ADMIN")));
//        }
//
//        return new AuthorizationDecision(authentication.get().isAuthenticated());
//    }
//
//    private boolean hasRole(Authentication authentication, String role) {
//        return authentication.getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_"+role));
//    }
//}
