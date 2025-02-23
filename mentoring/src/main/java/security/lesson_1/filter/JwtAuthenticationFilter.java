package security.lesson_1.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import security.lesson_1.service.JwtService;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) {

        String authHeader = request.getHeader("Authorization");

        if (!ObjectUtils.isEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            setUserSession(request, jwtService.getLoginFromToken(jwt), SecurityContextHolder.getContext(), jwt);
        }

        filterChain.doFilter(request, response);
    }

    private void setUserSession(HttpServletRequest request, String userEmail, SecurityContext context, String jwt) {
        if (ObjectUtils.isEmpty(userEmail) && context.getAuthentication() == null) {
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        tokenSetUp(request, context, jwt, userDetails);
    }

    private void tokenSetUp(HttpServletRequest request, SecurityContext context, String jwt, UserDetails userDetails) {
        if (jwtService.validateToken(jwt)) {
            var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
        }
    }
}
