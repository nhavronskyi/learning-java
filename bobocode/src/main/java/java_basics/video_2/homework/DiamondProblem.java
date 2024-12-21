package java_basics.video_2.homework;

interface A {
    default void greet() {
        System.out.println("Hello from A");
    }
}

interface B {
    default void greet() {
        System.out.println("Hello from B");
    }
}

// Class DiamondProblem implements both A and B
public class DiamondProblem implements A, B {

    public static void main(String[] args) {
        DiamondProblem obj = new DiamondProblem();
        obj.greet();
    }

    // Override to resolve ambiguity
    @Override
    public void greet() {
        // You can choose which default method to use
        A.super.greet(); // Call greet() from A
        B.super.greet();
        // B.super.greet(); // Uncomment to call greet() from B
        System.out.println("Hello from diamond");
    }
}
