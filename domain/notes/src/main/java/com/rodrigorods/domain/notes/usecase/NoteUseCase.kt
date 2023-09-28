package com.rodrigorods.domain.notes.usecase

import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.domain.notes.repository.NoteRepository

interface NoteUseCase {
    suspend fun getAllNotes(): List<Note>
    suspend fun createNote(): Long
    suspend fun deleteNote(noteId: Long)
    suspend fun updateNote(noteId: Long, newTitle: String, newDescription: String)
}

class NoteUseCaseImpl(
    private val repository: NoteRepository
) : NoteUseCase {
    override suspend fun getAllNotes(): List<Note> {
        return repository.getAllNotes()
    }

    override suspend fun createNote(): Long {
        return repository.createNote()
    }

    override suspend fun deleteNote(noteId: Long) {
        repository.deleteNote(noteId)
    }

    override suspend fun updateNote(noteId: Long, newTitle: String, newDescription: String) {
        repository.updateNote(noteId, newTitle, newDescription)
    }
}
