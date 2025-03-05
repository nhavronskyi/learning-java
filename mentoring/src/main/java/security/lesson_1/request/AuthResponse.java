package security.lesson_1.request;

import org.springframework.http.HttpStatus;

public record AuthResponse(String token, HttpStatus status) {
}
