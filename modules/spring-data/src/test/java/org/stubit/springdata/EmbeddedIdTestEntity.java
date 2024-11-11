package org.stubit.springdata;

import static java.util.UUID.randomUUID;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import java.util.Objects;
import java.util.UUID;
import org.stubit.random.RandomString;

class EmbeddedIdTestEntity {

  @EmbeddedId private Id id;
  private String name;

  public EmbeddedIdTestEntity(Id id, String name) {
    this.id = id;
    this.name = name;
  }

  public static EmbeddedIdTestEntity randomEntity() {
    return new EmbeddedIdTestEntity(new Id(randomUUID()), RandomString.latinLetters(8));
  }

  public Id getId() {
    return id;
  }

  public void setId(Id id) {
    this.id = id;
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
    EmbeddedIdTestEntity entity = (EmbeddedIdTestEntity) o;
    return Objects.equals(id, entity.id) && Objects.equals(name, entity.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Embeddable
  static class Id {
    private UUID uuid;

    public Id() {}

    public Id(UUID uuid) {
      this.uuid = uuid;
    }

    public UUID getId() {
      return uuid;
    }

    public void setId(UUID uuid) {
      this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Id entity = (Id) o;
      return Objects.equals(uuid, entity.uuid);
    }

    @Override
    public int hashCode() {
      return Objects.hash(uuid);
    }
  }
}
