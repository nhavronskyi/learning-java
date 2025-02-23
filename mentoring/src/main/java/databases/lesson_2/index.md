Hands-on tasks to assess and build advanced JPA skills:

- Advanced entity mapping and relationship management.
- Query optimization using fetch joins and dynamic query construction.
- Implementing entity listeners for auditing.
- Addressing the N+1 query problem using various strategies (JPQL fetch join, entity graphs, batch fetching).
- Optionally, implementing caching to reduce database load.

---

## Task 1. Advanced Entity Mapping for a Library Management System

**Objective:**  
Create a domain model for a library management system using advanced JPA features.

**Requirements:**

	1. **Entities and Relationships:**
	   - Create the following entities:
		 - **Author** – an author who can write multiple books.
		 - **Book** – a book that belongs to one author and one publisher.
		 - **Publisher** – a publisher that publishes many books.
	   - Configure the following relationships:
		 - **Author — Book:**  
		   - One author has many books (`@OneToMany` in *Author* and `@ManyToOne` in *Book*).  
		   - Implement cascading and orphan removal so that when an author is deleted, the related books are also removed.
		 - **Publisher — Book:**  
		   - A publisher publishes many books (`@OneToMany` in *Publisher* and `@ManyToOne` in *Book*).

	2. **Relationship Management Methods:**
	   - In the **Author** and **Publisher** entities, implement methods such as `addBook(Book book)` and `removeBook(Book book)` to manage bidirectional relationships properly.

	3. **Testing:**
	   - Write unit tests that:
		 - Create instances of *Author*, *Book*, and *Publisher*.
		 - Verify that when a book is added to an author and a publisher, the relationships are correctly established.
		 - Confirm that when a book or an author/publisher is removed, the corresponding changes (such as orphan removal) take effect.

---

## Task 2. Dynamic Queries Using Criteria API

**Objective:**  
Build dynamic queries based on input parameters.

**Requirements:**

	1. **Search Method:**
	   - Create a method in a repository (e.g., `BookRepository`) that searches for books based on dynamic parameters. The method should accept:
		 - `String titlePart` – a partial title of the book.
		 - `Long publisherId` – the identifier of the publisher.
	   - Use the **Criteria API** to build the query dynamically:
		 - If `titlePart` is provided, add a condition to search for titles (using a case-insensitive `LIKE`).
		 - If `publisherId` is provided, add a condition filtering by the publisher’s ID.
	   - If no parameters are provided, return all books.

	2. **Testing:**
	   - Write a set of unit tests that verify:
		 - Searching for books by a partial title.
		 - Searching for books by publisher ID.
		 - Searching for books using both parameters.
		 - Searching with no parameters (should return all books).

---

## Task 3. Implementing JPA Entity Listeners for Auditing

**Objective:**  
Use JPA lifecycle events to automatically audit entity changes.

**Requirements:**

	1. **Audit Fields in the Entity:**
	   - Add two new fields to the **Book** entity: `LocalDateTime createdDate` and `LocalDateTime lastModifiedDate`.
	   - Create an entity listener class (e.g., `BookAuditListener`) with methods annotated with `@PrePersist` and `@PreUpdate`:
		 - In the `@PrePersist` method, set the current time in both audit fields.
		 - In the `@PreUpdate` method, update the `lastModifiedDate` field.
	   - Apply the listener to the **Book** entity using the annotation:
		 ```java
		 @EntityListeners(BookAuditListener.class)
		 ```

	2. **Testing:**
	   - Write integration tests that:
		 - Create a new book and save it, verifying that the audit fields are populated.
		 - Update an existing book and verify that the `lastModifiedDate` is updated (and differs from `createdDate`).
		 - Optionally, log the listener method calls for visual confirmation.

---

## Task 4 (Optional). Query Caching

**Objective:**  
Implement caching for query results to improve performance.

**Requirements:**

	1. **Caching Configuration:**
	   - Configure Spring Cache (using, for example, EhCache or Caffeine).
	   - Annotate a repository method (e.g., one that returns a list of authors or books) with `@Cacheable`.
	   - Implement a method that returns data from the database.

	2. **Testing:**
	   - Write a test that:
		 - Calls the method to retrieve data.
		 - Measures or logs the number of SQL queries executed.
		 - Confirms that repeated calls to the method do not result in additional database queries (verified via logs or a query counter).

---

## Task 5. Additional: Solving the N+1 Query Problem Using Different Strategies

**Objective:**  
Address the N+1 query problem with various strategies and verify the effectiveness through tests.

**Requirements:**

	1. **Strategy A – Using JPQL Fetch Join:**
	   - Create a repository method that retrieves authors and their books with a fetch join.
	   - Example JPQL:
		 ```java
		 String jpql = "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books";
		 ```
	   - Write an integration test that verifies the number of SQL queries executed (using SQL logs) is minimized (ideally, one query).

	2. **Strategy B – Using Entity Graphs:**
	   - Define a JPA entity graph in the **Author** entity that includes the books.
	   - Create a repository method that uses the entity graph to load authors with books.
	   - Write a test that ensures the associated entities are eagerly loaded without additional queries.

	3. **Strategy C – Batch Fetching:**
	   - Configure Hibernate batch fetching for the collection association (e.g., in `persistence.xml` or application properties, set properties such as `hibernate.default_batch_fetch_size`).
	   - Write a repository method that retrieves authors without a fetch join.
	   - Write an integration test that verifies, by checking SQL logs, that batch fetching is applied to load the books efficiently.

	4. **Testing:**
	   - For each strategy, write tests using StepVerifier (or similar integration test tools) to:
		 - Load a set of authors with their books.
		 - Verify that the books are loaded (i.e., the association is not lazy-loaded individually for each author).
		 - Optionally, verify the number of SQL queries executed (this may require using a tool or custom logging/interception).

---
