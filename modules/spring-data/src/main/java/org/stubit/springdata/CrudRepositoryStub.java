package org.stubit.springdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

/**
 * A stub implementation of {@link CrudRepository} that stores data in memory.
 *
 * @see RepositoryStub
 * @see CrudRepository
 */
public abstract class CrudRepositoryStub<T, ID> extends RepositoryStub<T, ID>
    implements CrudRepository<T, ID> {

  @Override
  public @NonNull <S extends T> S save(@NonNull S entity) {
    data.put(getId(entity), entity);
    return entity;
  }

  @Override
  public @NonNull <S extends T> Iterable<S> saveAll(@NonNull Iterable<S> entities) {
    entities.forEach(this::save);
    return entities;
  }

  @Override
  public @NonNull Optional<T> findById(@NonNull ID id) {
    return Optional.ofNullable(data.get(id));
  }

  @Override
  public boolean existsById(@NonNull ID id) {
    return data.containsKey(id);
  }

  @Override
  public @NonNull Iterable<T> findAll() {
    return data.values();
  }

  @Override
  public @NonNull Iterable<T> findAllById(@NonNull Iterable<ID> ids) {
    List<T> found = new ArrayList<>();
    ids.forEach(
        id -> {
          if (!data.containsKey(id)) {
            throw new IllegalArgumentException("No entity with id %s".formatted(id));
          }
          found.add(data.get(id));
        });
    return found;
  }

  @Override
  public long count() {
    return data.size();
  }

  @Override
  public void deleteById(@NonNull ID id) {
    data.remove(id);
  }

  @Override
  public void delete(@NonNull T entity) {
    data.remove(getId(entity));
  }

  @Override
  public void deleteAllById(@NonNull Iterable<? extends ID> ids) {
    ids.forEach(data::remove);
  }

  @Override
  public void deleteAll(@NonNull Iterable<? extends T> entities) {
    entities.forEach(entity -> data.remove(getId(entity)));
  }

  @Override
  public void deleteAll() {
    data.clear();
  }
}
