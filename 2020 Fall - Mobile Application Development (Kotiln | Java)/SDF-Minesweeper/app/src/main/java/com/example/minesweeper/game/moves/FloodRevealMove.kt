package com.example.minesweeper.game.moves

import com.example.minesweeper.Point
import com.example.minesweeper.eightNeighbors
import com.example.minesweeper.game.Board
import java.util.*
import kotlin.collections.HashSet

class FloodRevealMove(private val row: Int, private val column: Int) : Move {
    override fun execute(board: Board, changeSet: Board.ChangeSet) {
        if (board.isMine(row, column)) {
            changeSet.reveal(row, column)
            return
        }

        val points = ArrayDeque<Point>()
        val seen = HashSet<Point>()

        points.push(Point(row, column))

        while (!points.isEmpty()) {
            val top = points.pop()
            val (row, column) = top
            if (top in seen || row !in 0 until board.rows || column !in 0 until board.columns) continue
            seen.add(top)

            if (!board.isMine(row, column)) {
                changeSet.unflag(row, column)
                changeSet.reveal(row, column)
            }

            val neighbors = board.eightNeighbors(row, column)
            if (
                neighbors.none {
                    board.isMine(
                        it.first,
                        it.second
                    )
                }
            ) neighbors.filter { it !in seen }.forEach(points::push)
        }
    }
}
