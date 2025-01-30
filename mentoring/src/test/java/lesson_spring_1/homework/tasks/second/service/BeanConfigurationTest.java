package lesson_spring_1.homework.tasks.second.service;

import lesson_spring_1.homework.tasks.second.config.AppConfig;
import lesson_spring_1.homework.tasks.second.config.AppConfigXml;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeanConfigurationTest {
    private final String applicationContextFileLocation = "lesson_spring_1/homework/tasks/second/applicationContext.xml";

    @Test
    void xmlConfigurationTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(applicationContextFileLocation);

        MessageService messageService = (MessageService) context.getBean("messageServiceXml");

        context.close();
        assertEquals("Hello, Spring XML Configuration!", messageService.message());
    }

    @Test
    void fileSystemXmlApplicationContextTest() {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/" + applicationContextFileLocation);

        MessageService messageService = (MessageService) context.getBean("messageServiceXml");

        context.close();
        assertEquals("Hello, Spring XML Configuration!", messageService.message());
    }

    @Test
    void javaConfigurationTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MessageService messageService = (MessageService) context.getBean("messageServiceJava");

        context.close();
        assertEquals("Hello, Spring Java Configuration!", messageService.message());
    }

    @Test
    void mixedConfigurationTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, AppConfigXml.class);

        MessageService messageServiceJava = (MessageService) context.getBean("messageServiceJava");
        MessageService messageServiceXml = (MessageService) context.getBean("messageServiceXml");

        assertEquals("Hello, Spring Java Configuration!", messageServiceJava.message());
        assertEquals("Hello, Spring XML Configuration!", messageServiceXml.message());
    }
}
