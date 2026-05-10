package me.heyner.manusim.core.application

import me.heyner.manusim.core.ManusimTest
import me.heyner.manusim.core.domain.MachineRepository
import me.heyner.manusim.core.domain.TimeGenerator
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import java.time.Duration
import kotlin.test.Test

@ManusimTest
class AddMachineUseCaseTests(
    @Autowired val addMachineUseCase: AddMachineUseCase,
    @Autowired val machineRepository: MachineRepository,
) {
    @Test
    fun `when a machine is added then it is persisted`() {
        addMachineUseCase.execute(
            TimeGenerator(
                Duration.ofSeconds(10),
                Duration.ofSeconds(20),
            ),
        )
        assertThat(machineRepository.findAll().isEmpty()).isFalse()
    }
}
