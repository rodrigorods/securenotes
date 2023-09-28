package com.rodrigorods.ui.notes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.ui.notes.databinding.ActivityEditNoteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditNoteBinding

    private val viewModel: NotesListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.editText?.setText(getStringExtra(EXTRA_NOTE_TITLE))
        binding.description.editText?.setText(getStringExtra(EXTRA_NOTE_DESCRIPTION))

        binding.deleteNoteButton.setOnClickListener { deleteNote(getNoteId()!!) }
        binding.editNoteButton.setOnClickListener { editNote(getNoteId()!!) }
    }

    private fun deleteNote(noteId: Long) {
        viewModel.deleteNote(noteId)
        onBackPressed()
    }

    private fun editNote(noteId: Long) {
        viewModel.editNote(
            noteId,
            binding.title.editText?.text.toString(),
            binding.description.editText?.text.toString()
        )
        onBackPressed()
    }

    private fun getStringExtra(extraName: String) =
        intent.extras?.getString(extraName, "").toString()
    private fun getNoteId() = intent.extras?.getLong(EXTRA_NOTE_ID)

    companion object {

        private const val EXTRA_NOTE_ID = "xA@Dxca!"
        private const val EXTRA_NOTE_TITLE = "@(*HANSD>K<BJ@"
        private const val EXTRA_NOTE_DESCRIPTION = "!!@OIUgcbaxxsS"

        fun getIntent(context: Context, note: Note): Intent {
            return Intent(context, EditNoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE_ID, note.id)
                putExtra(EXTRA_NOTE_TITLE, note.title)
                putExtra(EXTRA_NOTE_DESCRIPTION, note.description)
            }
        }
    }
}
