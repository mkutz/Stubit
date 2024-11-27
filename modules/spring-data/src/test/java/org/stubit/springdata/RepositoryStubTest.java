package org.stubit.springdata;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class RepositoryStubTest {

  @Test
  void getId_sping_data_Id() {
    class TestSpringDataIdEntity {
      @org.springframework.data.annotation.Id private UUID uuid = randomUUID();
    }
    class TestRepositoryStub extends RepositoryStub<TestSpringDataIdEntity, UUID> {}

    var repositoryStub = new TestRepositoryStub();

    var testEntity = new TestSpringDataIdEntity();

    assertThat(repositoryStub.getId(testEntity)).isEqualTo(testEntity.uuid);
  }

  @Test
  void getId_jakarta_Id() {
    class TestEmbeddedIdEntity {
      @jakarta.persistence.Id private UUID uuid = randomUUID();
    }
    class TestRepositoryEmbeddedIdStub extends RepositoryStub<TestEmbeddedIdEntity, UUID> {}

    var repositoryStub = new TestRepositoryEmbeddedIdStub();

    var testEmbeddedIdEntity = new TestEmbeddedIdEntity();

    assertThat(repositoryStub.getId(testEmbeddedIdEntity)).isEqualTo(testEmbeddedIdEntity.uuid);
  }

  @Test
  void getId_jakarta_EmbeddedId() {
    class TestEmbeddedIdEntity {
      @jakarta.persistence.EmbeddedId private UUID uuid = randomUUID();
    }
    class TestRepositoryEmbeddedIdStub extends RepositoryStub<TestEmbeddedIdEntity, UUID> {}

    var repositoryStub = new TestRepositoryEmbeddedIdStub();

    var testEmbeddedIdEntity = new TestEmbeddedIdEntity();

    assertThat(repositoryStub.getId(testEmbeddedIdEntity)).isEqualTo(testEmbeddedIdEntity.uuid);
  }

  @Test
  void getId_no_id() {
    class TestNoIdEntity {
      private final UUID uuid = randomUUID();

      public UUID getUuid() {
        return uuid;
      }
    }
    class TestRepositoryNoIdStub extends RepositoryStub<TestNoIdEntity, UUID> {}

    var repositoryStub = new TestRepositoryNoIdStub();

    var testNoIdEntity = new TestNoIdEntity();

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> repositoryStub.getId(testNoIdEntity))
        .withMessage("No Id or EmbeddedId annotated field found in the entity");
  }
}
