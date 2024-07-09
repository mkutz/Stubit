package org.stubit.springdata;

import static java.util.UUID.randomUUID;
import static java.util.stream.StreamSupport.stream;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

class SpringDataDocTest {

  @Test
  void findAll() {
    // tag::create[]
    TestCrudRepositoryStub repository = new TestCrudRepositoryStub();
    // end::create[]
    // tag::fillSingle[]
    TestEntity savedEntity = repository.save(new TestEntity(randomUUID(), "first entity"));
    // end::fillSingle[]
    // tag::fillMultiple[]
    Iterable<TestEntity> savedEntities =
        repository.saveAll(
            List.of(
                new TestEntity(randomUUID(), "second entity"),
                new TestEntity(randomUUID(), "third entity")));
    // end::fillMultiple[]
    // tag::inject[]
    TestService testService = new TestService(repository);
    // end::inject[]

    // tag::verify[]
    assertThat(testService.getAll()).contains(savedEntity).containsAll(savedEntities);
    assertThat(repository.findByName("first entity")).isPresent().contains(savedEntity);
    // end::verify[]

    // tag::clear[]
    repository.deleteAll();
    // end::clear[]
  }

  private static
  // tag::service[]
  class TestService {

    private final TestCrudRepository repository;

    @Autowired
    public TestService(TestCrudRepository repository) {
      this.repository = repository;
    }

    public List<TestEntity> getAll() {
      return stream(repository.findAll().spliterator(), false).toList();
    }

    public TestEntity getByName(String name) {
      return repository.findByName(name).orElseThrow();
    }
  }

  // end::service[]

  // tag::repository[]
  interface TestCrudRepository extends CrudRepository<TestEntity, UUID> {

    Optional<TestEntity> findByName(String name);
  }

  // end::repository[]

  private static
  // tag::implement[]
  class TestCrudRepositoryStub extends CrudRepositoryStub<TestEntity, UUID>
      implements TestCrudRepository {

    @Override
    public Optional<TestEntity> findByName(String name) {
      return data.values().stream().filter(e -> e.getName().equals(name)).findFirst();
    }
  }

  // end::implement[]

  static class TestEntity {

    @Id private UUID uuid;
    private String name;

    public TestEntity(UUID uuid, String name) {
      this.uuid = uuid;
      this.name = name;
    }

    public UUID getUuid() {
      return uuid;
    }

    public void setUuid(UUID uuid) {
      this.uuid = uuid;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      TestEntity entity = (TestEntity) o;
      return Objects.equals(uuid, entity.uuid) && Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(uuid, name);
    }
  }
}
