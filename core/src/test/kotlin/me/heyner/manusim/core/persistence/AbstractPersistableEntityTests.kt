package me.heyner.manusim.core.persistence

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.heyner.manusim.core.ManusimTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
import kotlin.test.Test

data class TestPersistableEntityId(
    var id: UUID = UUID.randomUUID(),
)

@Entity
@Table(name = "test_persistable_entity")
class TestPersistableEntity : AbstractPersistableEntity<TestPersistableEntityId>() {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    override var entityId = TestPersistableEntityId()
}

interface TestPersistableEntityRepository : JpaRepository<TestPersistableEntity, TestPersistableEntityId>

@ManusimTest
class AbstractPersistableEntityTests(
    @Autowired val testPersistableEntityRepository: TestPersistableEntityRepository,
) {
    lateinit var testPersistableEntity: TestPersistableEntity

    @BeforeEach
    fun setup() {
        testPersistableEntity = TestPersistableEntity()
    }

    @Test
    fun `given a test entity when saved then it can be fetched`() {
        testPersistableEntityRepository.save(testPersistableEntity)
        val fetchedTestEntity = testPersistableEntityRepository.findById(testPersistableEntity.id)
        assert(fetchedTestEntity.isPresent)
    }

    @Test
    fun `given a test entity when saved then it is not new`() {
        val savedEntity = testPersistableEntityRepository.save(testPersistableEntity)
        assertThat(savedEntity.isNew).isFalse()
        assertThat(savedEntity.id).isEqualTo(testPersistableEntity.id)
    }
}
