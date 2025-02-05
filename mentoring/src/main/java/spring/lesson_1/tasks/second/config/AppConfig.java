package spring.lesson_1.tasks.second.config;

import org.springframework.context.annotation.Bean;
import spring.lesson_1.tasks.second.service.MessageService;

public class AppConfig {

    @Bean
    public MessageService messageServiceJava() {
        return new MessageService("Hello, Spring Java Configuration!");
    }
}
