Video: https://www.youtube.com/watch?v=i5LZqWFMyVw&list=PLqt5_5aU1KQLFZH-Rltag_AvHtQvDHhzG&index=2

## why ArrayList extend AbstractList and implement List?

![classes and interfaces.png](../../../resources/screenshots/classes%20and%20interfaces.png)

there are few reasons why it's done in that way:

1. better documentation how list works
2. if try to get interfaces from ArrayList using reflections there would be a List interface, but if we remove List from
   implements it would not be there using reflection

### We should understand how annotations works because it would help us to understand how frameworks like Spring, Lombok and Hibernate works from inside

! most of Lombok annotations are using **SOURCE retention policy**

## Memory in Java

![memory model.png](../../../resources/screenshots/memory%20model.png)
![memory model 2.png](../../../resources/screenshots/memory%20model%202.png)

## Generics

