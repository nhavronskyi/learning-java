package java_basics.video_2.generics.entity_container;

public class Main {
    public static void main(String[] args) {
        EntityContainer<User> userContainer = new EntityContainer<>();
        userContainer.addEntity(new User("test", 1));
        userContainer.addEntity(new User("test2", 1));

        userContainer.getEntities().forEach(System.out::println);
    }
}
