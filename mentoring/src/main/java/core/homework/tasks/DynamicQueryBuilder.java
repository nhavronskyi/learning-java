package core.homework.tasks;

import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* Dynamic Query Builder: Implement a dynamic SQL query builder using Generics. The builder should accept criteria and return a query string while ensuring type safety for criteria values.
  f ex use select using builder pattern
  queryBuilder
       .{select}
       .{from}
       .{condition}
 */
@Getter
public class DynamicQueryBuilder<T> {
    private final List<T> list;

    public DynamicQueryBuilder(Builder<T> builder) {
        this.list = builder.list;
    }

    public static class Builder<T> {
        private List<T> list;

        public static <T> Builder<T>.FromStep from(List<T> list) {
            return new Builder<T>().new FromStep(list);
        }

        private void setList(List<T> list) {
            this.list = list;
        }

        public class FromStep {
            private List<T> list;

            public FromStep(List<T> list) {
                this.list = list;
            }

            public Builder<T>.FromStep select(Function<T, T> selector) {
                list = list.stream()
                        .map(selector)
                        .collect(Collectors.toList());
                return this;
            }

            public FromStep where(Predicate<T> selector) {
                list = list.stream()
                        .filter(selector)
                        .toList();
                return this;
            }

            public DynamicQueryBuilder<T> build() {
                setList(this.list);
                return new DynamicQueryBuilder<>(Builder.this);
            }
        }
    }
}
