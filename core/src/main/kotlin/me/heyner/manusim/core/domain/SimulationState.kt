package me.heyner.manusim.core.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class SimulationState(
    @Column(name = "pieces_pending")
    var piecesPending: Long = 0,
    @Column(name = "pieces_finished")
    var piecesFinished: Long = 0,
)
