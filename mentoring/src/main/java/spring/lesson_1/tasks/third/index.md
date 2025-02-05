1. Conditional Dependency Injection:
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
