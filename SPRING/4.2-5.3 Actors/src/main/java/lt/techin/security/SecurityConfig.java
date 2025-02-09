package lt.techin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/users/{id}").access((authentication, context) ->
                                        new AuthorizationDecision(SecurityUtils.isAdminOrOwner(authentication.get(), Long.parseLong(context.getVariables().get("id")))))
                                .requestMatchers(HttpMethod.PUT, "/api/users/{id}").access((authentication, context) ->
                                        new AuthorizationDecision(SecurityUtils.isAdminOrOwner(authentication.get(), Long.parseLong(context.getVariables().get("id")))))
                                .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").access((authentication, context) ->
                                        new AuthorizationDecision(SecurityUtils.isAdminOrOwner(authentication.get(), Long.parseLong(context.getVariables().get("id")))))
                                .anyRequest().authenticated()
                        //.anyRequest().access(authorizationManager)
                        //.requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN") - /** can be used
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
