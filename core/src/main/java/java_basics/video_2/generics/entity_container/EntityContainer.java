package java_basics.video_2.generics.entity_container;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class EntityContainer<T extends BaseEntity> {
    private final Set<T> entities = new HashSet<>();

    public void addEntity(T entity) {
        entities.add(entity);
    }
}
