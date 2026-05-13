package me.heyner.manusim.core.domain

import me.heyner.manusim.test.annotation.ManusimUnitTest
import org.assertj.core.api.Assertions.assertThat
import java.time.Duration
import kotlin.test.Test

@ManusimUnitTest
class TimeGeneratorTests {
    val timeGenerator =
        TimeGenerator(
            from = Duration.ofSeconds(1),
            to = Duration.ofSeconds(10),
            seed = 123456789,
        )

    @Test
    fun `when time is requested then it is generated`() {
        val time = timeGenerator.generate()
        assertThat(time).isBetween(Duration.ofSeconds(1), Duration.ofSeconds(10))
    }

    @Test
    fun `when time is requested then it is generated with a random offset`() {
        val time = timeGenerator.generate()
        assertThat(time).isNotEqualTo(timeGenerator.generate())
    }
}
