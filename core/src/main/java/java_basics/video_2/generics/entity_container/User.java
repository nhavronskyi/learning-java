package java_basics.video_2.generics.entity_container;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
public class User extends BaseEntity {
    private final String name;
    private final Integer id;
}
