package org.stubit.springdata;

import java.lang.reflect.Field;
import java.util.HashMap;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.repository.Repository;

/**
 * Simple abstract {@link Repository} stub implementation using a {@link HashMap} to store data.
 *
 * @see Repository
 */
@NullMarked
public abstract class RepositoryStub<T, ID> implements Repository<T, ID> {

  /** A {@link HashMap} to store the data. */
  protected HashMap<ID, T> data = new HashMap<>();

  /**
   * Extracts the {@link org.springframework.data.annotation.Id} or {@link jakarta.persistence.Id}
   * or {@link jakarta.persistence.EmbeddedId} annotated field value from the entity.
   *
   * @param entity the entity to extract the id from
   * @return the value of the {@code Id} or {@code EmbeddedId} annotated field.
   */
  protected ID getId(T entity) {
    for (Field field : entity.getClass().getDeclaredFields()) {
      if (field.getAnnotation(org.springframework.data.annotation.Id.class) != null
          || field.getAnnotation(jakarta.persistence.Id.class) != null
          || field.getAnnotation(jakarta.persistence.EmbeddedId.class) != null) {
        try {
          field.setAccessible(true);
          return (ID) field.get(entity);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }

    throw new IllegalArgumentException("No Id or EmbeddedId annotated field found in the entity");
  }
}
