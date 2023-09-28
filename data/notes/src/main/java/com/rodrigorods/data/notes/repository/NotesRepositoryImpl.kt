package com.rodrigorods.data.notes.repository

import com.rodrigorods.data.notes.db.NoteEntity
import com.rodrigorods.data.notes.db.NotesDAO
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.domain.notes.repository.NoteRepository

class NotesRepositoryImpl(
    private val notesDAO: NotesDAO
): NoteRepository {
    override suspend fun getAllNotes(): List<Note> {
        return notesDAO.getAllNotes().map { it.toNote() }
    }

    private fun NoteEntity.toNote() = Note(
        id = this.id,
        title = this.title,
        description = this.description,
        creationDate = this.creationDate
    )

    override suspend fun createNote(): Long {
        return notesDAO.addNote(NoteEntity(0, "aaa", "bbb", System.currentTimeMillis()))
    }

    override suspend fun deleteNote() {
        notesDAO.deleteNote(0)
    }

    override suspend fun updateNote() {
        notesDAO.updateNote(NoteEntity(0, "", "", 0))
    }
}
