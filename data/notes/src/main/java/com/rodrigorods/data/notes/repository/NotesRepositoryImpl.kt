package com.rodrigorods.data.notes.repository

import com.rodrigorods.data.notes.db.NoteEntity
import com.rodrigorods.data.notes.db.NotesDAO
import com.rodrigorods.domain.notes.repository.NoteRepository

class NotesRepositoryImpl(
    private val notesDAO: NotesDAO
): NoteRepository {
    override suspend fun getAllNotes() {
        notesDAO.getAllNotes()
    }
    override suspend fun createNote() {
        notesDAO.addNote(NoteEntity(0, "", "", 0))
    }

    override suspend fun deleteNote() {
        notesDAO.deleteNote(0)
    }

    override suspend fun updateNote() {
        notesDAO.updateNote()
    }
}
