package com.example.minesweeper.game.moves

import com.example.minesweeper.forEachEightNeighbor
import com.example.minesweeper.game.Board

class ChordMove(private val row: Int, private val column: Int) : Move {
    override fun execute(board: Board, changeSet: Board.ChangeSet) {
        if (!board.isRevealed(row, column)) return
        board.forEachEightNeighbor(row, column) { row, column ->
            if (!board.isFlagged(row, column)) changeSet.reveal(row, column)
        }
    }
}
