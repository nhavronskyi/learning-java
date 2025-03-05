package security.lesson_1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import security.lesson_1.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class UserConfig {
    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return login -> repository.findByUsername(login)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + login + " not found"));
    }
}
