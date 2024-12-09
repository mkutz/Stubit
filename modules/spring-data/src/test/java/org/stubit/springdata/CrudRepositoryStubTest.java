package org.stubit.springdata;

import static java.util.UUID.randomUUID;
import static java.util.stream.StreamSupport.stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CrudRepositoryStubTest {

  @Test
  void save() {
    var repository = new TestCrudRepositoryStub();

    TestEntity entity = repository.save(TestEntity.randomEntity());

    assertThat(repository.findById(entity.getUuid())).isPresent().get().isEqualTo(entity);
  }

  @Test
  void save_embeddedId() {
    var repository = new EmbeddedIdTestCrudRepositoryStub();

    EmbeddedIdTestEntity entity = repository.save(EmbeddedIdTestEntity.randomEntity());

    assertThat(repository.findById(entity.getId())).isPresent().get().isEqualTo(entity);
  }

  @Test
  void saveAll() {
    var repository = new TestCrudRepositoryStub();

    var entities =
        repository.saveAll(List.of(TestEntity.randomEntity(), TestEntity.randomEntity()));

    assertThat(repository.findAllById(idsOf(entities))).isEqualTo(entities);
  }

  @Test
  void findById() {
    var repository = new TestCrudRepositoryStub();
    var entity = repository.save(TestEntity.randomEntity());

    assertThat(repository.findById(entity.getUuid())).isPresent().get().isEqualTo(entity);
  }

  @Test
  void existsById() {
    var repository = new TestCrudRepositoryStub();
    var entity = repository.save(TestEntity.randomEntity());

    assertThat(repository.existsById(entity.getUuid())).isTrue();
  }

  @Test
  void findAll() {
    var repository = new TestCrudRepositoryStub();
    Iterable<TestEntity> entities =
        repository.saveAll(List.of(TestEntity.randomEntity(), TestEntity.randomEntity()));

    assertThat(repository.findAll()).containsAll(entities);
  }

  @Test
  void findAllById() {
    var repository = new TestCrudRepositoryStub();
    var entities = List.of(TestEntity.randomEntity(), TestEntity.randomEntity());
    repository.saveAll(entities);

    assertThat(repository.findAllById(idsOf(entities))).containsAll(entities);
  }

  @Test
  void findAllById_not_found() {
    var repository = new TestCrudRepositoryStub();
    var knownEntity = repository.save(TestEntity.randomEntity());
    var unknownId = randomUUID();
    var ids = List.of(knownEntity.getUuid(), unknownId);

    assertThat(repository.findAllById(ids)).containsExactly(knownEntity);
  }

  @Test
  void count() {
    var repository = new TestCrudRepositoryStub();
    List<TestEntity> entitiesList = List.of(TestEntity.randomEntity(), TestEntity.randomEntity());
    repository.saveAll(entitiesList);

    assertThat(repository.count()).isEqualTo(entitiesList.size());
  }

  @Test
  void deleteById() {
    var repository = new TestCrudRepositoryStub();
    var entity = repository.save(TestEntity.randomEntity());
    assumeThat(repository.existsById(entity.getUuid())).isTrue();

    repository.deleteById(entity.getUuid());

    assertThat(repository.existsById(entity.getUuid())).isFalse();
  }

  @Test
  void delete() {
    var repository = new TestCrudRepositoryStub();
    var entity = repository.save(TestEntity.randomEntity());
    assumeThat(repository.existsById(entity.getUuid())).isTrue();

    repository.delete(entity);

    assertThat(repository.existsById(entity.getUuid())).isFalse();
  }

  @Test
  void delete_embeddedId() {
    var repository = new EmbeddedIdTestCrudRepositoryStub();
    var entity = repository.save(EmbeddedIdTestEntity.randomEntity());
    assumeThat(repository.existsById(entity.getId())).isTrue();

    repository.delete(entity);

    assertThat(repository.existsById(entity.getId())).isFalse();
  }

  @Test
  void deleteAllById() {
    var repository = new TestCrudRepositoryStub();
    var entities =
        repository.saveAll(List.of(TestEntity.randomEntity(), TestEntity.randomEntity()));

    repository.deleteAllById(idsOf(entities));

    assertThat(repository.count()).isZero();
  }

  @Test
  void deleteAll_entities() {
    var repository = new TestCrudRepositoryStub();
    var entities =
        repository.saveAll(List.of(TestEntity.randomEntity(), TestEntity.randomEntity()));

    repository.deleteAll(entities);

    assertThat(repository.count()).isZero();
  }

  @Test
  void deleteAll_entities_embeddedId() {
    var repository = new EmbeddedIdTestCrudRepositoryStub();
    var entities =
        repository.saveAll(
            List.of(EmbeddedIdTestEntity.randomEntity(), EmbeddedIdTestEntity.randomEntity()));

    repository.deleteAll(entities);

    assertThat(repository.count()).isZero();
  }

  @Test
  void deleteAll() {
    var repository = new TestCrudRepositoryStub();
    repository.saveAll(List.of(TestEntity.randomEntity(), TestEntity.randomEntity()));

    repository.deleteAll();

    assertThat(repository.count()).isZero();
  }

  private static class TestCrudRepositoryStub extends CrudRepositoryStub<TestEntity, UUID> {}

  private static class EmbeddedIdTestCrudRepositoryStub
      extends CrudRepositoryStub<EmbeddedIdTestEntity, EmbeddedIdTestEntity.Id> {}

  private static List<UUID> idsOf(Iterable<TestEntity> entities) {
    return stream(entities.spliterator(), false).map(TestEntity::getUuid).toList();
  }
}
