package me.heyner.manusim.core.application

import me.heyner.manusim.core.ManusimTest
import me.heyner.manusim.core.domain.Machine
import me.heyner.manusim.core.domain.MachineId
import me.heyner.manusim.core.domain.MachineRepository
import me.heyner.manusim.core.domain.Simulation
import me.heyner.manusim.core.domain.SimulationId
import me.heyner.manusim.core.domain.SimulationRepository
import me.heyner.manusim.core.exception.MachineNotFoundException
import me.heyner.manusim.core.exception.SimulationNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.Test

@ManusimTest
class AddMachineToSimulationUseCaseTests(
    @Autowired val addMachineToSimulationUseCase: AddMachineToSimulationUseCase,
    @Autowired val simulationRepository: SimulationRepository,
    @Autowired val machineRepository: MachineRepository,
) {
    private lateinit var testSimulation: Simulation

    private lateinit var testMachine: Machine

    @BeforeEach
    fun setup() {
        simulationRepository.deleteAll()
        machineRepository.deleteAll()
        testSimulation = Simulation()
        testMachine = Machine()
        simulationRepository.save(testSimulation)
        machineRepository.save(testMachine)
    }

    @Test
    fun `given machine and simulation already created when machine is added then it is persisted`() {
        addMachineToSimulationUseCase.execute(testSimulation.id, testMachine.id)
        assertThat(simulationRepository.findById(testSimulation.id).get().machine).isEqualTo(testMachine.id)
    }

    @Test
    fun `given machine and simulation already created when non existent machine then it throws`() {
        assertThrows<MachineNotFoundException> {
            addMachineToSimulationUseCase.execute(testSimulation.id, MachineId())
        }
    }

    @Test
    fun `given machine and simulation already created when non existent simulation then it throws`() {
        assertThrows<SimulationNotFoundException> {
            addMachineToSimulationUseCase.execute(SimulationId(), testMachine.id)
        }
    }
}
