package me.heyner.factorydataflow.machinemanager.domain

import me.heyner.factorydataflow.machinemanager.TestcontainersConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import kotlin.test.Test

@Import(TestcontainersConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductionEventRepositoryTests {
    @Autowired
    lateinit var machineRepository: MachineRepository

    @Autowired
    lateinit var productionEventRepository: ProductionEventRepository

    lateinit var testMachine: Machine
    lateinit var testProductionEvent: ProductionEvent

    @BeforeEach
    fun setup() {
        testMachine = machineRepository.save(Machine("testMachine"))
        testProductionEvent = ProductionEvent(testMachine.entityId)
    }

    @Test
    fun `given a production event when saving then it should be retrievable by id`() {
        productionEventRepository.save(testProductionEvent)
        val foundEvent = productionEventRepository.findById(testProductionEvent.id).getOrNull()
        assertThat(foundEvent).isEqualTo(testProductionEvent)
    }

    @Test
    fun `given a production event when updating quality then it should be retrievable by id`() {
        productionEventRepository.save(testProductionEvent)
        testProductionEvent.quality = ProductionEventQuality.SCRAP
        productionEventRepository.save(testProductionEvent)
        val foundEvent = productionEventRepository.findById(testProductionEvent.id).getOrNull()
        assertThat(foundEvent).isEqualTo(testProductionEvent)
    }

    @Test
    fun `given a production event when updating end date then it should be retrievable by id`() {
        productionEventRepository.save(testProductionEvent)
        testProductionEvent.endDate = OffsetDateTime.now()
        productionEventRepository.save(testProductionEvent)
        val foundEvent = productionEventRepository.findById(testProductionEvent.id).getOrNull()
        assertThat(foundEvent).isEqualTo(testProductionEvent)
    }
}
