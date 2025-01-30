package lesson_spring_1.homework.tasks.second.config;

import lesson_spring_1.homework.tasks.second.service.MessageService;
import org.springframework.context.annotation.Bean;

public class AppConfig {

    @Bean
    public MessageService messageServiceJava() {
        return new MessageService("Hello, Spring Java Configuration!");
    }
}
