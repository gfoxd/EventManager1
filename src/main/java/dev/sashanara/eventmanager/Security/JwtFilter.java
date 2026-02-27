package dev.sashanara.eventmanager.Security;

import dev.sashanara.eventmanager.Users.UserEntity;
import dev.sashanara.eventmanager.Users.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtFilter(
            JwtUtil jwtUtil,
            UserRepository userRepository
    ) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String token = extractToken(request);

        if (token != null && jwtUtil.validateToken(token)) {
            String login = jwtUtil.getLoginFromToken(token);

            if (userRepository.findByLogin(login) != null) {
                UserEntity userEntity = userRepository.findByLogin(login);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userEntity.getLogin(),
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole()))
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                new RuntimeException("User not found");
            }
        }
        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
