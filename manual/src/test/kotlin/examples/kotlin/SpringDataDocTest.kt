package examples.kotlin

import org.assertj.core.api.Assertions.assertThat
import kotlin.invoke

import org.junit.jupiter.api.Test
import org.springframework.data.annotation.Id
import org.springframework.data.repository.CrudRepository
import org.stubit.springdata.CrudRepositoryStub
import java.util.UUID
import java.util.UUID.randomUUID
import java.util.stream.StreamSupport.stream

class SpringDataDocTest {

  @Test
  fun findAll() {
    // tag::create[]
    val repository = TestCrudRepositoryStub()
    // end::create[]
    // tag::fillSingle[]
    val savedEntity = repository.save(TestEntity(randomUUID(), "first entity"))
    // end::fillSingle[]
    // tag::fillMultiple[]
    val savedEntities =
        repository.saveAll(
            listOf(
                TestEntity(randomUUID(), "second entity"),
                TestEntity(randomUUID(), "third entity")))
    // end::fillMultiple[]
    // tag::inject[]
    val testService = TestService(repository)
    // end::inject[]

    // tag::verify[]
    assertThat(testService.getAll()).contains(savedEntity).containsAll(savedEntities)
    assertThat(repository.findByName("first entity")).isNotNull().isEqualTo(savedEntity)
    // end::verify[]

    // tag::clear[]
    repository.deleteAll()
    // end::clear[]
  }

  private
  // tag::service[]
  class TestService(
    private val repository: TestCrudRepository
  ) {

    fun getAll() = repository.findAll().toList()

    fun getByName(name: String) = repository.findByName(name)!!
  }

  // end::service[]

  // tag::repository[]
  interface TestCrudRepository : CrudRepository<TestEntity, UUID> {

    fun findByName(name: String): TestEntity?
  }

  // end::repository[]

  private
  // tag::implement[]
  class TestCrudRepositoryStub : CrudRepositoryStub<TestEntity, UUID>(), TestCrudRepository {

    override fun findByName(name: String): TestEntity? {
      return data.values.find { e -> e.name.equals(name) }
    }
  }

  // end::implement[]

  data class TestEntity(
      @Id var uuid: UUID,
      var name: String
  )
}
