package org.stubit.springdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.repository.CrudRepository;

/**
 * A stub implementation of {@link CrudRepository} that stores data in memory.
 *
 * @see RepositoryStub
 * @see CrudRepository
 */
@NullMarked
public abstract class CrudRepositoryStub<T, ID> extends RepositoryStub<T, ID>
    implements CrudRepository<T, ID> {

  @Override
  public <S extends T> S save(S entity) {
    data.put(getId(entity), entity);
    return entity;
  }

  @Override
  public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
    entities.forEach(this::save);
    return entities;
  }

  @Override
  public Optional<T> findById(ID id) {
    return Optional.ofNullable(data.get(id));
  }

  @Override
  public boolean existsById(ID id) {
    return data.containsKey(id);
  }

  @Override
  public Iterable<T> findAll() {
    return data.values();
  }

  @Override
  public Iterable<T> findAllById(Iterable<ID> ids) {
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
  public void deleteById(ID id) {
    data.remove(id);
  }

  @Override
  public void delete(T entity) {
    data.remove(getId(entity));
  }

  @Override
  public void deleteAllById(Iterable<? extends ID> ids) {
    ids.forEach(data::remove);
  }

  @Override
  public void deleteAll(Iterable<? extends T> entities) {
    entities.forEach(entity -> data.remove(getId(entity)));
  }

  @Override
  public void deleteAll() {
    data.clear();
  }
}
