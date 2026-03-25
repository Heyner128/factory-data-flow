package me.heyner.factorydataflow.machinemanager.domain

import me.heyner.factorydataflow.machinemanager.TestcontainersConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import kotlin.jvm.optionals.getOrNull
import kotlin.test.Test

@Import(TestcontainersConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ManufacturingLineNodeRepositoryTests {
    @Autowired
    lateinit var machineRepository: MachineRepository

    @Autowired
    lateinit var manufacturingLineRepository: ManufacturingLineRepository

    @Autowired
    lateinit var manufacturingLineNodeRepository: ManufacturingLineNodeRepository

    lateinit var testMachine: Machine
    lateinit var testLine: ManufacturingLine
    lateinit var testNode: ManufacturingLineNode

    @BeforeEach
    fun setup() {
        testMachine = machineRepository.save(Machine("testMachine"))
        testLine = manufacturingLineRepository.save(ManufacturingLine("testLine"))
        testNode = ManufacturingLineNode(ManufacturingLineNodeId(testMachine.entityId, testLine.entityId))
    }

    @Test
    fun `given a node when saving then it should be retrievable by id`() {
        manufacturingLineNodeRepository.save(testNode)
        val foundNode = manufacturingLineNodeRepository.findById(testNode.id).getOrNull()
        assertThat(foundNode).isEqualTo(testNode)
    }

    @Test
    fun `given a node when updating next machine then it should be retrievable by id`() {
        manufacturingLineNodeRepository.save(testNode)
        val nextMachine = machineRepository.save(Machine("nextMachine"))
        testNode.nextMachine = nextMachine.entityId
        manufacturingLineNodeRepository.save(testNode)
        val foundNode = manufacturingLineNodeRepository.findById(testNode.id).getOrNull()
        assertThat(foundNode).isEqualTo(testNode)
    }
}
