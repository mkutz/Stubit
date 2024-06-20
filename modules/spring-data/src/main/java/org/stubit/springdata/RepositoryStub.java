package org.stubit.springdata;

import java.util.HashMap;
import org.springframework.data.repository.Repository;

/**
 * Simple abstract {@link Repository} stub implementation using a {@link HashMap} to hold data.
 *
 * @see Repository
 * @param <T> the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 */
abstract class RepositoryStub<T, ID> implements Repository<T, ID> {

  protected HashMap<ID, T> data = new HashMap<>();

  abstract <S extends T> ID getId(S entity);
}
