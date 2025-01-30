Custom Dependency Injection Container:
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
