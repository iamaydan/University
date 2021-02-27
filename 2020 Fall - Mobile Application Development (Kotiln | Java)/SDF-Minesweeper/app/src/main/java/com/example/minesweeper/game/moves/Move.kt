package com.example.minesweeper.game.moves

import com.example.minesweeper.game.Board

interface Move {
    enum class Type {
        Reveal,
        Flag,
        RemoveFlag
    }

    fun execute(board: Board, changeSet: Board.ChangeSet)
}
