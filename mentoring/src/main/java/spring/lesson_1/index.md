Theory:

1. Dependency Injection and Inversion of Control (IoC):
    - Explaining the concepts of Dependency Injection and IoC.
    - Understanding how Spring implements IoC and Dependency Injection.

2. Spring Bean Configuration:
    - Configuring Spring beans using XML and annotations.
    - Understanding the Bean lifecycle and scope.

3. Autowiring:
    - Exploring different types of autowiring: byName, byType, constructor, and autodetect.
    - Configuring autowiring in Spring beans.

4. Spring Container:
    - Understanding the role of the Spring container.
    - Exploring different types of Spring containers: BeanFactory vs. ApplicationContext.
    - Consider several implementations of ApplicationContext: ClassPathXmlApplicationContext,
      FileSystemXmlApplicationContext, AnnotationConfigApplicationContext, etc., catering to different application
      deployment scenarios.

Naming Beans:
https://docs.spring.io/spring-framework/reference/core/beans/definition.html#beans-beanname

Using depends-on:
https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-dependson.html

Lazy-initialized Beans:
https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-lazy-init.html

Fine-tuning Annotation-based Autowiring with @Primary
https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired-primary.html

Fine-tuning Annotation-based Autowiring with Qualifiers
https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired-qualifiers.html

Using @Value
https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/value-annotations.html

AOP Concepts
https://docs.spring.io/spring-framework/reference/core/aop/introduction-defn.html
https://docs.spring.io/spring-framework/reference/core/aop/introduction-proxies.html
https://docs.spring.io/spring-framework/reference/core/aop/ataspectj.html
https://docs.spring.io/spring-framework/reference/core/aop/proxying.html

	https://www.baeldung.com/spring-aop

--------------------------------------------------------------------------------------------------------------------

Coding tasks:

1. Custom Dependency Injection Container:
   Implement a lightweight DI container from scratch. This involves creating classes to manage object creation,
   dependency resolution, and injection based on metadata provided through annotations or configuration files.

   use template below:

   /**
   Container class manages bindings and object creation.
   bind methods register bindings between interfaces/classes and their implementations.
   make method resolves dependencies and instantiates objects.
   createInstance method is a helper method to create instances by resolving dependencies recursively.
   */

   interface Database {
   // Define methods for database interface
   }

   class MySQLDatabase implements Database {
   // Implement methods for MySQL database
   }

   class UserRepository {
   private Database db;

   	public UserRepository(Database db) {
   		this.db = db;
   	}

   	// Other methods for UserRepository
   }

   class Container {
   private Map<Class<?>, Object> bindings = new HashMap<>();

   	public void bind(Class<?> cls, Object instance) {
   		bindings.put(cls, instance);
   	}

   	public <T> void bind(Class<T> cls) {
   		try {
   			//implement here
   			...
   		} catch (Exception e) {
   			throw new RuntimeException("Failed to create instance for " + cls.getName(), e);
   		}
   	}

   	public <T> T make(Class<T> cls) {
   		if (!bindings.containsKey(cls)) {
   			throw new RuntimeException("No binding found for " + cls.getName());
   		}
   		Object binding = bindings.get(cls);
   		if (binding instanceof Class<?>) {
   			return createInstance((Class<?>) binding);
   		} else {
   			return cls.cast(binding);
   		}
   	}

   	private <T> T createInstance(Class<T> cls) {
   		try {
   			//implement here
   			...
   		} catch (Exception e) {
   			throw new RuntimeException("Failed to create instance for " + cls.getName(), e);
   		}
   	}
   }

   public class Main {
   public static void main(String[] args) {
   Container container = new Container();
   container.bind(Database.class, new MySQLDatabase());
   container.bind(UserRepository.class);

   		UserRepository userRepo = container.make(UserRepository.class);
   		System.out.println(userRepo); // Output: UserRepository@<hashcode>
   		System.out.println(userRepo.getDb()); // Output: MySQLDatabase@<hashcode>
   	}
   }

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

3. Conditional Dependency Injection:
   Implement a mechanism to conditionally inject dependencies based on certain criteria. For example, inject different
   implementations of a service depending on the environment (development, testing, production) or runtime conditions.

   use template below:

   /**
    - We define two implementations of the `Database` interface: `MySQLDatabase` and `MockDatabase`.
    - We create a `UserRepository` class that depends on the `Database` interface.
    - Using Spring's `@Conditional` annotation, we conditionally register beans based on certain conditions. In this
      case, `mockDatabase()` bean is registered if `UseMockDatabaseCondition` matches, and `realDatabase()` bean is
      registered if `UseRealDatabaseCondition` matches.
    - `UseMockDatabaseCondition` and `UseRealDatabaseCondition` classes define the conditions to use mock or real
      databases. You can implement your own logic here to determine the conditions.
    - In the `main` method, we create an `AnnotationConfigApplicationContext` with the `AppConfig` class, where all the
      bean configurations are defined.
    - We retrieve the `UserRepository` bean from the context and perform operations on it. The actual implementation
      injected will depend on the condition defined in `UseMockDatabaseCondition` or `UseRealDatabaseCondition`.
      */

   interface Database {
   // Define methods for database interface
   void connect();
   }

   class MySQLDatabase implements Database {
   @Override
   public void connect() {
   System.out.println("Connecting to MySQL database...");
   }
   }

   class MockDatabase implements Database {
   @Override
   public void connect() {
   System.out.println("Connecting to mock database...");
   }
   }

   class UserRepository {
   private Database db;

   	public UserRepository(Database db) {
   		this.db = db;
   	}

   	// Other methods for UserRepository
   	public void performDatabaseOperation() {
   		db.connect();
   	}
   }

   @Configuration
   class AppConfig {

   	//implement here bean configuration for MockDatabase, MySQLDatabase, UserRepository classes
   	//...
   }

   class UseMockDatabaseCondition implements org.springframework.context.annotation.Condition {
   @Override
   public boolean matches(org.springframework.context.annotation.ConditionContext conditionContext,
   org.springframework.core.type.AnnotatedTypeMetadata annotatedTypeMetadata) {
   // Your condition to use mock database
   return true;
   }
   }

   class UseRealDatabaseCondition implements org.springframework.context.annotation.Condition {
   @Override
   public boolean matches(org.springframework.context.annotation.ConditionContext conditionContext,
   org.springframework.core.type.AnnotatedTypeMetadata annotatedTypeMetadata) {
   // Your condition to use real database
   return false;
   }
   }

   public class Main {
   public static void main(String[] args) {
   AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

   		UserRepository userRepository = context.getBean(UserRepository.class);
   		userRepository.performDatabaseOperation(); // Output depends on the condition

   		context.close();
   	}
   }

4. Conditional Bean Configuration:
   Extend Spring's conditional bean configuration feature to support more complex conditions based on environment
   variables, system properties, or other runtime factors. Customize the conditions under which beans are instantiated
   and configured.

   use template below:

   /**
    - We define two implementations of the Database interface: MySQLDatabase and MockDatabase.
    - We create a UserRepository class that depends on the Database interface.
    - Using Spring's @Conditional annotation, we conditionally register beans based on the custom condition implemented
      in EnvironmentCondition class.
    - The EnvironmentCondition class checks environment variables, system properties, or any other runtime factors to
      determine whether the bean should be instantiated. In this case, it checks if the active profile is "production".
    - In the main method, we create an AnnotationConfigApplicationContext, set the active profile to "production", and
      then register and refresh the context with the AppConfig class.
    - We retrieve the UserRepository bean from the context and perform operations on it. The actual implementation
      injected will depend on the condition defined in EnvironmentCondition.
      */

   interface Database {
   // Define methods for database interface
   void connect();
   }

   class MySQLDatabase implements Database {
   @Override
   public void connect() {
   System.out.println("Connecting to MySQL database...");
   }
   }

   class MockDatabase implements Database {
   @Override
   public void connect() {
   System.out.println("Connecting to mock database...");
   }
   }

   class UserRepository {
   private Database db;

   	public UserRepository(Database db) {
   		this.db = db;
   	}

   	// Other methods for UserRepository
   	public void performDatabaseOperation() {
   		db.connect();
   	}
   }

   @Configuration
   class AppConfig {

   	//configure here MySQLDatabase and UserRepository
   	//...
   }

   class EnvironmentCondition implements org.springframework.context.annotation.Condition {
   @Override
   public boolean matches(org.springframework.context.annotation.ConditionContext conditionContext,
   org.springframework.core.type.AnnotatedTypeMetadata annotatedTypeMetadata) {
   Environment environment = conditionContext.getEnvironment();

   		// Check environment variables, system properties, or any other runtime factors
   		String profile = environment.getProperty("spring.profiles.active");
   		return "production".equals(profile);
   	}
   }

   public class Main {
   public static void main(String[] args) {
   AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

   		// Set active profile
   		context.getEnvironment().setActiveProfiles("production");
   		context.register(AppConfig.class);
   		context.refresh();

   		UserRepository userRepository = context.getBean(UserRepository.class);
   		userRepository.performDatabaseOperation(); // Output depends on the condition

   		context.close();
   	}
   }

5. Alias Conflict Resolution Task:
   Write a scenario where two beans are defined with conflicting aliases in separate configuration files. Demonstrate
   how Spring resolves this conflict and ensure that aliases from both files are successfully recognized by the
   application context.

6. Using Aliases for Dependency Injection Task:
   Define multiple beans of the same type but with different names and aliases. Then, demonstrate how these aliases can
   be used for dependency injection in other beans.

7. Cyclic Dependency Detection Task:
   Create a scenario where two beans have dependencies on each other, leading to a cyclic dependency. Use the depends-on
   attribute to resolve the cyclic dependency and ensure proper initialization order.

8. Lazy Loading with Dependency Injection Task:
   Create a scenario where a lazy-initialized bean depends on another bean. Show how lazy loading works in conjunction
   with dependency injection, ensuring that dependencies are resolved only when needed.

9. Nested Dependencies with Qualifiers Task:
   Create a scenario where a bean has dependencies on other beans, and these dependencies are also of the same type. Use
   qualifiers to specify which dependency should be injected into the parent bean. Ensure proper wiring of nested
   dependencies based on qualifiers.

10. Caching Aspect Task:
    Implement a caching aspect with Spring AOP to improve application performance by caching method results. Intercept
    method calls and cache return values based on specific conditions or annotations.

11. Validation Aspect Task:
    Develop a validation aspect using Spring AOP to perform input validation on method parameters. Intercept method
    calls and validate input parameters against predefined rules or annotations. 
