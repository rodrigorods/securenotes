package com.rodrigorods.ui.notes

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.ui.notes.databinding.ActivityNotesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesListBinding

    private val viewModel: NotesListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
        viewModel.getAllNotes()

        binding.addNoteButton.setOnClickListener { viewModel.addNote() }
    }

    private fun observeData() {
        viewModel.uiState.observe(this) {
            when (it) {
                is UIState.Waiting -> displayFullScreenProgress()
                is UIState.DisplayingUI -> displayListOfNotes(it.notes)
                is UIState.AddedNewNote -> updateListWithNewNote(it.newNote)
                is UIState.EmptyList -> {}
            }
        }
    }

    private fun displayFullScreenProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.notesList.visibility = View.GONE
    }

    private fun displayListOfNotes(notes: List<Note>) {
        binding.progressBar.visibility = View.GONE
        binding.notesList.visibility = View.VISIBLE

        binding.notesList.adapter = NotesAdapter(notes as MutableList<Note>) { clickedNote ->
            startForResult.launch(EditNoteActivity.getIntent(this, clickedNote))
        }
    }

    private fun updateListWithNewNote(note: Note) {
        (binding.notesList.adapter as NotesAdapter).addNote(note)
        binding.notesList.scrollToPosition(0)
    }

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == EditNoteActivity.RESULT_NOTE_DELETED) {
            val deletedId = result.data?.getLongExtra(EditNoteActivity.EXTRA_NOTE_ID, 0)
            (binding.notesList.adapter as NotesAdapter).removeFromList(deletedId)
        } else if (result.resultCode == EditNoteActivity.RESULT_NOTE_UPDATED) {
            val updatedId = result.data?.getLongExtra(EditNoteActivity.EXTRA_NOTE_ID, 0)
            val newTitle = result.data?.getStringExtra(EditNoteActivity.EXTRA_NOTE_TITLE) ?: ""
            val newDescription = result.data?.getStringExtra(EditNoteActivity.EXTRA_NOTE_DESCRIPTION) ?: ""
            (binding.notesList.adapter as NotesAdapter).updateNote(updatedId, newTitle, newDescription)
        }
    }
}
