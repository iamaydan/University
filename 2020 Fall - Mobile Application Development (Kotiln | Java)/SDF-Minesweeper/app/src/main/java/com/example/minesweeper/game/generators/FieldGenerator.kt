package com.example.minesweeper.game.generators

import com.example.minesweeper.game.Field

interface FieldGenerator {
    fun generate(rows: Int, columns: Int, args: FieldGenerationArguments): Field
}

data class FieldGenerationArguments(
    val mines: Int
)
