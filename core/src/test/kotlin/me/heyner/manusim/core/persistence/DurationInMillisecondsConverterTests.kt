package me.heyner.manusim.core.persistence

import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.heyner.manusim.core.ManusimTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Duration
import java.util.UUID
import kotlin.test.Test

data class TestWithDurationEntityId(
    var id: UUID = UUID.randomUUID(),
)

@Entity
@Table(name = "test_with_duration_entity")
class TestWithDurationEntity : AbstractPersistableEntity<TestWithDurationEntityId>() {
    @EmbeddedId
    @AttributeOverride(name = "id", column = Column(name = "id", nullable = false))
    override var entityId = TestWithDurationEntityId()

    @Column(name = "duration_in_ms", nullable = false)
    @Convert(converter = DurationInMillisecondsConverter::class)
    var duration: Duration = Duration.ofSeconds(1)
}

interface TestWithDurationEntityRepository : JpaRepository<TestWithDurationEntity, TestWithDurationEntityId>

@ManusimTest
class DurationInMillisecondsConverterTests(
    @Autowired val testWithDurationEntityRepository: TestWithDurationEntityRepository,
) {
    private lateinit var testWithDurationEntity: TestWithDurationEntity

    @Test
    fun `given a test entity with a duration when saved then it can be fetched`() {
        testWithDurationEntity = TestWithDurationEntity()
        testWithDurationEntityRepository.save(testWithDurationEntity)
        val fetchedTestEntity = testWithDurationEntityRepository.findById(testWithDurationEntity.id)
        assert(fetchedTestEntity.isPresent)
        assert(fetchedTestEntity.get().duration == testWithDurationEntity.duration)
    }
}
