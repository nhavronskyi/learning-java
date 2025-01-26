Result:

    [x] practical done
    [x] theory done

Theory:

	https://learning.oreilly.com/library/view/java-concurrency-in/0321349601/ch06.xhtml#ch06lev1sec2 (6.2. The Executor Framework)
	https://learning.oreilly.com/library/view/java-concurrency-in/0321349601/ch08.xhtml#ch08lev1sec1 (Chapter 8. Applying Thread Pools)

Practical tasks:

	1. Modify the blocking queue to allow dynamic adjustment of the maximum capacity while the queue is in use.
	For given code template please create implementation and test it:

```java
    public class DynamicCapacityBlockingQueue<T> {
		...
    BlockingQueue<T> queue;
		...
    ReentrantLock lock;
		...
    int maxCapacity;

    public DynamicCapacityBlockingQueue(int initialCapacity) {
        this.queue = new ...//choose the most appropriate implementation
        this.lock = new ReentrantLock();
        this.maxCapacity = initialCapacity;
    }

    //here we can dynamically change capacity for our queue
    public void setMaxCapacity(int newMaxCapacity) {
	...
    }

    public void put(T item) throws InterruptedException {
	...
    }

    public T take() throws InterruptedException {
	...
    }

    // Other methods like size(), isEmpty(), etc., can be implemented similarly.

    public static void main(String[] args) {
        DynamicCapacityBlockingQueue<String> dynamicQueue = new DynamicCapacityBlockingQueue<>(5);

        // Use the queue as usual
        try {
            dynamicQueue.put("Item 1");
            dynamicQueue.put("Item 2");
            dynamicQueue.put("Item 3");

            // Adjust max capacity dynamically
            dynamicQueue.setMaxCapacity(10);

            dynamicQueue.put("Item 4");
            dynamicQueue.put("Item 5");
            dynamicQueue.put("Item 6");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

	2. using the template above please add behavior for Custom Exception Handling to your blocking queue implementation:
	   Add custom exception handling to your blocking queue, such as defining specific exceptions for timeouts or interruption please use:

```java
      class QueueFullException extends RuntimeException {
    public QueueFullException(String message) {
        super(message);
    }
}

class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String message) {
        super(message);
    }
}
```

	3.  Blocking Multiple Consumers:
	Create a scenario where multiple consumer threads block on a synchronous queue waiting for an element to be produced. Ensure that only one of them gets the element.
	use the below template to make implementation:

```java
    public class MultipleConsumersExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> queu = new ...//choose write implementation of the queue

        // Producer thread
        Thread producerThread = new Thread(() -> {
	...
        });

        // Consumer threads
        for (int consumerId = 1; consumerId <= 3; consumerId++) {
	...
            consumerThread.start();
        }

        producerThread.start();

        try {
            producerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

	Option2: implement the task above using one of implementations for ExecutorService.

	4. Executors:

	Task 1: Concurrent Matrix Multiplication
	Implement a matrix multiplication program that performs the multiplication of two matrices concurrently using an ExecutorService. Divide the multiplication task into smaller subtasks and assign them to different threads. Ensure proper synchronization to aggregate the results accurately.

	Task 2: Parallel File Processing
	Develop a program that reads multiple files concurrently using an ExecutorService. Each file should be processed by a separate thread. Implement a task that counts the occurrences of specific words in each file and produces a report on the total word count.

