package com.rodrigorods.ui.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            when(it) {
                is UIState.Waiting -> {}
                is UIState.DisplayingUI -> displayListOfNotes(it.notes)
                is UIState.AddedNewNote -> updateListWithNewNote(it.newNote)
                is UIState.EmptyList -> {}
            }
        }
    }

    private fun displayListOfNotes(notes: List<Note>) {
        binding.notesList.adapter = NotesAdapter(notes as MutableList<Note>)
    }

    private fun updateListWithNewNote(note: Note) {
        (binding.notesList.adapter as NotesAdapter).addNote(note)
    }
}
