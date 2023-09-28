package com.rodrigorods.ui.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.ui.notes.databinding.NoteListItemBinding

class NotesAdapter(
    private val notesList: MutableList<Note>,
    private val onNoteClicked: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteListItemViewHolder>() {

    fun addNote(note: Note) {
        notesList.add(0, note)
        notifyItemInserted(0)
    }

    override fun getItemCount(): Int = notesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListItemViewHolder {
        val binding = NoteListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return NoteListItemViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: NoteListItemViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    inner class NoteListItemViewHolder(
        private val context: Context,
        private val binding: NoteListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.root.setOnClickListener { onNoteClicked.invoke(note) }

            binding.noteTitle.text = note.title.ifEmpty(context, R.string.default_title)
            binding.noteDescription.text =
                note.description.ifEmpty(context, R.string.default_description)
        }
    }

    private fun String.ifEmpty(context: Context, resId: Int): String {
        return this.ifEmpty { context.getString(resId) }
    }
}
