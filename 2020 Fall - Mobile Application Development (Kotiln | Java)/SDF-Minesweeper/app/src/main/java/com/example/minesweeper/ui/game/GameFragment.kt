package com.example.minesweeper.ui.game

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.minesweeper.KEY_BOARD
import com.example.minesweeper.PREFS_NAME
import com.example.minesweeper.R
import com.example.minesweeper.game.Board
import com.example.minesweeper.game.GameSettings
import com.example.minesweeper.game.generators.FieldGenerationArguments
import com.example.minesweeper.game.generators.RandomFieldGenerator
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment : Fragment() {
    private val prefs by lazy {
        requireContext().getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    private val generator = RandomFieldGenerator()
    private lateinit var settings: GameSettings

    private lateinit var board: Board
    private var started = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        initialSetup(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_game, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        resetLayout()

        updateHeader()

        board_view.board = board
        settings = GameSettings.load(prefs)
        board_view.settings = settings


        val itemsWon = arrayOf<CharSequence>("Restart", "New Game")
        val itemsLost = arrayOf<CharSequence>("Restart", "New Game", "Undo")




        board_view.moveListener = object : BoardView.OnMoveListener {
            override fun onMove(board: Board, state: Board.State) {
                when (state) {
                    Board.State.Win -> {
                        board_view.isEnabled = false
                        AlertDialog.Builder(requireActivity())
                            .setTitle("You won!")
                            .setItems(itemsWon) { dialog, item ->
                                dialog.dismiss()
                                if (itemsWon[item] == "Restart")
                                    restartGame()
                                if (itemsWon[item] == "New Game")
                                    newGame()
                            }.show()
                    }
                    Board.State.Loss -> {
                        board_view.isEnabled = false
                        AlertDialog.Builder(requireActivity())
                            .setTitle("You lost. Try again?")
                            .setItems(itemsLost) { dialog, item ->
                                dialog.dismiss()
                                if (itemsLost[item] == "Restart")
                                    restartGame()
                                if (itemsLost[item] == "New Game")
                                    newGame()
                                if (itemsLost[item] == "Undo")
                                    undo()
                            }.show()
                    }
                    Board.State.Neutral -> {
                        board_view.isEnabled = true

                        updateHeader()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.game_menu, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_gameFragment_to_settingsFragment)
            R.id.action_new -> newGame()
            R.id.action_restart -> restartGame()
            R.id.action_undo -> undo()
            else -> return false
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.v("Minesweeper", "saving")
        with(outState) {
            putParcelable(KEY_BOARD, board)
            // TODO persist entire state
        }
    }

    private fun updateHeader() {
        text_remaining.text =
            getString(R.string.remaining_flags).format(board.mines - board.flagged)
    }

    private fun initialSetup(bundle: Bundle?) {
        started = false

        val stored = bundle?.getParcelable<Board>(KEY_BOARD)
        Log.v("Minesweeper", stored.toString())

        settings = GameSettings.load(prefs)
        val field = generator.generate(
            settings.rows,
            settings.columns,
            FieldGenerationArguments(settings.mines)
        )

        started = false
        board = Board(field)
    }

    fun restartGame() {
        board.restart()
        resetLayout()
    }

    fun newGame() {
        initialSetup(null)
        resetLayout()
    }

    private fun resetLayout() {
        board_view.board = board
        updateHeader()
    }

    fun undo() {
        if (!board_view.undo()) Toast.makeText(
            requireContext(),
            R.string.empty_undo_stack,
            Toast.LENGTH_SHORT
        ).show()
    }
}
