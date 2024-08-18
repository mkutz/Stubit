package org.stubit.springdata;

import java.util.List;
import java.util.stream.StreamSupport;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.repository.ListCrudRepository;

/**
 * A stub implementation of {@link ListCrudRepositoryStub} that stores data in memory.
 *
 * @see CrudRepositoryStub
 * @see ListCrudRepository
 */
@NullMarked
public abstract class ListCrudRepositoryStub<T, ID> extends CrudRepositoryStub<T, ID>
    implements ListCrudRepository<T, ID> {

  @Override
  public <S extends T> List<S> saveAll(Iterable<S> entities) {
    return asList(super.saveAll(entities));
  }

  @Override
  public List<T> findAll() {
    return data.values().stream().toList();
  }

  @Override
  public List<T> findAllById(Iterable<ID> ids) {
    return asList(super.findAllById(ids));
  }

  private static <T> List<T> asList(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false).toList();
  }
}
