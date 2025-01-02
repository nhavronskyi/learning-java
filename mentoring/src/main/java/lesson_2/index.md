# Lesson 2: Concurrency

learned:

- threads
- runnable

## Homework:

Theory:

The book for reading from O'Reily site:
**1. "Java Concurrency in Practice"**  
*Authors*: Brian Goetz, Tim Peierls, Joshua Bloch, Joseph Bowbeer, David Holmes, Doug Lea

- **Chapter 2: Thread Safety**  
  Describes the fundamental concepts of thread safety and atomicity, as well as the use of locks to protect shared
  state.

- **Chapter 3: Sharing Objects**  
  Covers issues related to visibility, object publication, and safely sharing data between threads.

10 Practical Tasks to Test Knowledge from Chapters 2 and 3 of "Java Concurrency in Practice":

**Chapter 2: Thread Safety**

**1. Implement a Thread-Safe Counter**

- Write a thread-safe class `Counter` that allows multiple threads to increment a counter without causing race
  conditions.

**2. Build a Synchronized Cache**

- Create a thread-safe in-memory cache using `HashMap` that supports `put()` and `get()` operations.

**3. Create a Read-Write Lock Example**

- Implement a thread-safe data store that allows multiple threads to read data concurrently but only one thread to
  write.

**4. Write a Deadlock-Free Account Transfer**

- Simulate a money transfer system where multiple threads can transfer funds between accounts. Ensure no deadlocks
  occur.

**5. Protect a Shared Resource with BlockingQueue**

- Implement a producer-consumer system using a thread-safe queue to protect shared resources.

**Chapter 3: Sharing Objects**

**6. Demonstrate Visibility Issues with Shared Variables**

- Write a program where one thread updates a shared variable and another thread reads it. Show how lack of
  synchronization causes incorrect results.

**7. Publish an Object Safely**

- Create a class with a shared `Map`. Safely publish this map to multiple threads so they can read but not modify it.

**8. Implement a Safe Lazy Initialization**

- Implement a thread-safe lazy initialization of a singleton object.

**9. Use ThreadLocal for Thread-Specific Data**

- Write a program where each thread stores its own copy of a variable without interfering with others.

**10. Create a Visibility Demonstration with `volatile`**

- Write a program where one thread updates a flag, and another thread acts based on the flag’s value. Show how
  `volatile` ensures visibility across threads.