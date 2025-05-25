package nhavronskyi.hibernate.one.utils;

import java.util.Collection;

public class MapUtils {
    public static <T> T getValue(Collection<T> list) {
        return list.iterator()
            .next();
    }
}
