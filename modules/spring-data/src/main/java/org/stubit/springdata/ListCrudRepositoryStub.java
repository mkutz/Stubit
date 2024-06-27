package org.stubit.springdata;

import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;

/**
 * A stub implementation of {@link ListCrudRepositoryStub} that stores data in memory.
 *
 * @see CrudRepositoryStub
 * @see ListCrudRepository
 */
public abstract class ListCrudRepositoryStub<T, ID> extends CrudRepositoryStub<T, ID>
    implements ListCrudRepository<T, ID> {

  @Override
  public <S extends T> @NonNull List<S> saveAll(@NonNull Iterable<S> entities) {
    return asList(super.saveAll(entities));
  }

  @Override
  public @NonNull List<T> findAll() {
    return data.values().stream().toList();
  }

  @Override
  public @NonNull List<T> findAllById(@NonNull Iterable<ID> ids) {
    return asList(super.findAllById(ids));
  }

  private static <T> List<T> asList(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false).toList();
  }
}
