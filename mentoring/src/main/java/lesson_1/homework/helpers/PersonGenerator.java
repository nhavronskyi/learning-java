package lesson_1.homework.helpers;

public class PersonGenerator {
    public static Person generatePerson(int id, String name) {
        return new Person(id, name);
    }

    public record Person(int id, String name) implements Comparable<Person> {
        @Override
        public int compareTo(Person o) {
            return o.id - this.id;
        }
    }

}
