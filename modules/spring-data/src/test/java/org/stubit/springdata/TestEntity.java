package org.stubit.springdata;

import static java.util.UUID.randomUUID;

import java.util.Objects;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.stubit.random.RandomString;

class TestEntity {

  @Id private UUID uuid;
  private String name;

  public TestEntity(UUID uuid, String name) {
    this.uuid = uuid;
    this.name = name;
  }

  public static TestEntity randomEntity() {
    return new TestEntity(randomUUID(), RandomString.latinLetters(8));
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
