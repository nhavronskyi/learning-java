2. Create a simple Spring bean class named MessageService with a method getMessage() that returns a string message.
   Implement three different configurations for the MessageService bean:
    - Configuration using XML (applicationContext.xml).
    - Configuration using Java configuration (AppConfig.java).
    - Configuration using a mix of XML and Java configuration.
      Write a main application class Application that demonstrates the usage of different ApplicationContext
      implementations:
    - Use ClassPathXmlApplicationContext to load the XML-based configuration.
    - Use FileSystemXmlApplicationContext to load the XML-based configuration from an external file.
    - Use AnnotationConfigApplicationContext to load the Java-based configuration.
    - Use a mix of XML and Java configuration using appropriate ApplicationContext implementation.
      Each usage scenario should print the message obtained from the MessageService bean to the console.
