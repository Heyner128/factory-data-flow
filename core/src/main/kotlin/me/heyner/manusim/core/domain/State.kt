package me.heyner.manusim.core.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class State(
    @Column(name = "pieces_pending")
    var piecesPending: Int = 0,
    @Column(name = "pieces_finished")
    var piecesFinished: Int = 0,
)
