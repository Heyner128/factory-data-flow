package me.heyner.manusim.core.application

import me.heyner.manusim.core.domain.Event
import me.heyner.manusim.core.domain.EventType
import me.heyner.manusim.core.domain.Execution
import me.heyner.manusim.core.domain.ExecutionRepository
import me.heyner.manusim.core.domain.Machine
import me.heyner.manusim.core.domain.MachineRepository
import me.heyner.manusim.core.domain.Simulation
import me.heyner.manusim.core.domain.SimulationId
import me.heyner.manusim.core.domain.SimulationRepository
import me.heyner.manusim.core.domain.Status
import me.heyner.manusim.core.exception.SimulationExecutionException
import me.heyner.manusim.core.stereotype.UseCase
import java.time.Duration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@UseCase
class RunSimulationUseCase(
    private val simulationRepository: SimulationRepository,
    private val executionRepository: ExecutionRepository,
    private val machineRepository: MachineRepository,
) {
    private val executor: ExecutorService = Executors.newVirtualThreadPerTaskExecutor()

    fun execute(
        simulationId: SimulationId,
        duration: Duration,
    ): Execution {
        val simulation: Simulation =
            simulationRepository
                .findById(simulationId)
                .get()
        if (simulation.machine == null) {
            throw SimulationExecutionException(
                "Simulation with id $simulationId is empty",
            )
        }
        val machine: Machine = machineRepository.findById(simulation.machine!!).get()
        if (simulation.status != Status.NOT_RUNNING) {
            throw SimulationExecutionException(
                "Simulation with id $simulationId is already running",
            )
        }
        val execution =
            executionRepository.save(
                Execution(simulation.id),
            )

        simulation.status = Status.RUNNING
        executor.execute {
            execution.start(duration)
            scheduleProductionEvent(execution, machine)
            while (!execution.events.isEmpty()) {
                processProductionEvent(execution, machine)
            }
            simulation.status = Status.NOT_RUNNING
            simulationRepository.save(simulation)
        }
        simulationRepository.save(simulation)
        return execution
    }

    private fun processProductionEvent(
        execution: Execution,
        machine: Machine,
    ) {
        Thread.sleep(machine.timeGenerator.from.toMillis())
        execution.durationElapsed = execution.durationElapsed.plus(machine.timeGenerator.from)
        execution.state!!.piecesFinished++
        scheduleProductionEvent(execution, machine)
        execution.events.removeFirst()
        executionRepository.save(execution)
    }

    private fun scheduleProductionEvent(
        execution: Execution,
        machine: Machine,
    ) {
        if (execution.durationElapsed < execution.duration) {
            execution.events.add(
                Event(
                    EventType.PIECES_PRODUCTION,
                    machine.timeGenerator.from,
                ),
            )
        }
    }
}
