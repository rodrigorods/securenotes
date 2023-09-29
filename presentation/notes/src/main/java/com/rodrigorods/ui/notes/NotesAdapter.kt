package com.rodrigorods.ui.notes

import android.content.Context
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.ui.notes.databinding.NoteListItemBinding

class NotesAdapter(
    private val notesList: MutableList<Note>,
    private val onNoteClicked: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteListItemViewHolder>() {

    var obfuscateEntries: Boolean = true
        set(value) {
            field = value
            notifyItemRangeChanged(0, notesList.size)
        }

    fun addNote(note: Note) {
        notesList.add(0, note)
        notifyItemInserted(0)
    }

    fun removeFromList(deletedId: Long?) {
        val updatedIndex = notesList.indexOfLast { it.id == deletedId }
        notesList.removeIf { it.id == deletedId }
        notifyItemRemoved(updatedIndex)
    }

    fun updateNote(updatedId: Long?, newTitle: String, newDescription: String) {
        val updatedIndex = notesList.indexOfLast { it.id == updatedId }
        notesList[updatedIndex] = notesList[updatedIndex].copy(
            title = newTitle,
            description = newDescription
        )
        notifyItemChanged(updatedIndex)
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

            if (obfuscateEntries) {
                binding.noteTitle.hideData()
                binding.noteDescription.hideData()
            }
        }

        private fun TextView.hideData() {
            this.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    private fun String.ifEmpty(context: Context, resId: Int): String {
        return this.ifEmpty { context.getString(resId) }
    }
}
