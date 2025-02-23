Theory:

- "Spring Security in Action" by Laurentiu Spilca
  A hands-on guide to implementing authentication and authorization in applications using Spring Security.
- "Pro Spring Security: Securing Spring Framework 6 and Boot 3-based Java Applications"  by Massimo Nardone, Carlo
  Scarioni
  This book delves deeper into Spring Security, providing numerous practical examples and case studies.

These assignments are based on the topics such as JWT security with a custom UserDetailsService, method-level security
with a custom PermissionEvaluator, role-based endpoint protection, integration testing of security configurations, and
additional authentication event logging.

Practical test for verifying knowledge and practicing skills in Java Security with Spring Security:

	- A deep understanding of and ability to configure Spring Security for stateless REST APIs using JWT.
	- Skills in implementing custom components (such as a UserDetailsService and a PermissionEvaluator) for advanced authentication and authorization.
	- Proficiency in method-level security using annotations and global security context configuration.
	- The ability to create and test secured endpoints through integration tests.
	- Techniques for addressing the N+1 query problem using strategies like JPQL fetch join, entity graphs, and batch fetching.

---

## **Task 1. JWT Security and Custom UserDetailsService**

**Objective:**  
Create a Spring Boot REST API that is secured using Spring Security in a stateless mode with JWT authentication and a
custom `UserDetailsService`.

**Requirements:**

	1. **Security Configuration:**
	   - Configure security using the modern `SecurityFilterChain` (do not extend `WebSecurityConfigurerAdapter`).
	   - Disable CSRF (since the application is stateless) and configure CORS.
	   - Set the session management to `SessionCreationPolicy.STATELESS`.
	   - Configure a JWT Resource Server to validate JWT tokens (using `.oauth2ResourceServer(...)`).

	2. **UserDetailsService:**
	   - Implement a custom `UserDetailsService` that loads user data (you may use an in-memory approach or a database repository).
	   - Ensure that the user has a set of roles/authorities required to access secured resources.

	3. **Endpoints:**
	   - Create two endpoints:
		 - **Public Endpoint:** e.g., `GET /api/public/hello` — accessible without authentication.
		 - **Private Endpoint:** e.g., `GET /api/private/hello` — accessible only to authenticated users.
	   - In the private endpoint, display the username retrieved from the `Authentication` object.

	4. **Testing:**
	   - Write integration tests (using tools like `MockMvc` or `WebTestClient`) that:
		 - Verify that the public endpoint is accessible without a token.
		 - Verify that the private endpoint returns an error (HTTP 401 Unauthorized) when no JWT or an invalid JWT is provided.
		 - Verify that access to the private endpoint is successful when a valid JWT is supplied.

---

## **Task 2. Method-Level Security with Custom PermissionEvaluator**

**Objective:**  
Implement method-level security using a custom `PermissionEvaluator` to allow access control based on custom logic for
domain objects.

**Requirements:**

	1. **Custom PermissionEvaluator:**
	   - Create a class `CustomPermissionEvaluator` that implements the `PermissionEvaluator` interface.
	   - Implement the `hasPermission(...)` method with your custom logic – for example, allow access if the user has an authority like `BOOK_READ` for an object of type `Book`.
	   - Register the custom `PermissionEvaluator` in your global method security configuration (for instance, in a class extending `GlobalMethodSecurityConfiguration`).

	2. **Secured Method:**
	   - Create a service (e.g., `BookService`) with a method secured by an annotation such as:
		 ```java
		 @PreAuthorize("hasPermission(#bookId, 'Book', 'READ')")
		 public Book getBook(Long bookId) { ... }
		 ```
	   - Implement the method to return a book (a stub or a real implementation is acceptable).

	3. **Testing:**
	   - Write unit or integration tests that:
		 - Verify that the `getBook` method is successfully executed for a user who has the required authority.
		 - Verify that an access denied error is thrown if the user lacks the required permission.

---

## **Task 3. Controller with Role-Based Security**

**Objective:**  
Create a REST controller with endpoints whose access is restricted based on roles (e.g., ROLE_USER, ROLE_ADMIN).

**Requirements:**

	1. **Controller Implementation:**
	   - Implement a controller (e.g., `SecureController`) with multiple endpoints:
		 - An endpoint accessible only to users with the ADMIN role (e.g., `GET /api/admin`).
		 - An endpoint accessible to all authenticated users (e.g., `GET /api/user`).
	   - Use annotations such as `@PreAuthorize` or `@Secured` to restrict access. For example:
		 ```java
		 @PreAuthorize("hasRole('ADMIN')")
		 @GetMapping("/api/admin")
		 public ResponseEntity<String> adminEndpoint() { ... }
		 ```

	2. **Testing:**
	   - Write integration tests that:
		 - Verify that a user with the ADMIN role can access the `/api/admin` endpoint.
		 - Verify that a user without the ADMIN role receives an access denied error when trying to access `/api/admin`.
		 - Verify that the endpoint accessible to all authenticated users works correctly.

---

## **Task 4. Integration Testing of Security Setup**

	**Objective:**  
	Perform integration tests to verify the correct functioning of your security configuration.

	**Requirements:**

	1. **JWT Generation Utility:**
	   - Implement a utility (or use an external tool) to generate JWT tokens with the necessary claims (such as username and roles).

	2. **Integration Tests:**
	   - Using `MockMvc`, `WebTestClient`, or another tool, write tests that:
		 - Send HTTP requests to both the public and private endpoints.
		 - Verify that a request to a private endpoint without a token returns a 401 Unauthorized status.
		 - Verify that a request with a valid JWT returns the expected response.
		 - *(Optional)* Verify proper behavior when an invalid or expired JWT is used.

---

## **Task 5. Advanced: Custom Authentication Event Logging**

**Objective:**  
Implement additional functionality to log authentication events, including both successful and failed login attempts.

**Requirements:**

	1. **Custom Authentication Filter / Listener:**
	   - Create a component (for example, by implementing `ApplicationListener<AuthenticationSuccessEvent>` and `ApplicationListener<AuthenticationFailureBadCredentialsEvent>`) to log authentication events.
	   - Log successful authentications (including user information and timestamp) and unsuccessful attempts.

	2. **Testing:**
	   - Write tests (using Mockito or other tools) that:
		 - Simulate a successful authentication and verify that the event is logged.
		 - Simulate a failed authentication and verify that the event is also logged (you can check console output or verify interactions with a mock logger).

---
