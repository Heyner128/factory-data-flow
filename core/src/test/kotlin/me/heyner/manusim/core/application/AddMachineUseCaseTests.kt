package me.heyner.manusim.core.application

import me.heyner.manusim.core.ManusimTest
import me.heyner.manusim.core.domain.MachineRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.Test

@ManusimTest
class AddMachineUseCaseTests(
    @Autowired val addMachineUseCase: AddMachineUseCase,
    @Autowired val machineRepository: MachineRepository,
) {
    @BeforeEach
    fun setup() {
        machineRepository.deleteAll()
    }

    @Test
    fun `when a machine is added then it is persisted`() {
        addMachineUseCase.execute()
        assertThat(machineRepository.findAll().isEmpty()).isFalse()
    }
}
