package com.rodrigorods.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.ui.notes.databinding.NoteListItemBinding

class NotesAdapter(
    private val notesList: MutableList<Note>
) : RecyclerView.Adapter<NotesAdapter.NoteListItemViewHolder>() {

    fun addNote(note : Note) {
        notesList.add(0, note)
        notifyItemInserted(0)
    }

    override fun getItemCount() : Int = notesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListItemViewHolder {
        val binding = NoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NoteListItemViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    inner class NoteListItemViewHolder(
        private val binding: NoteListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.noteTitle.text = note.title
            binding.noteDescription.text = note.description
        }
    }
}
