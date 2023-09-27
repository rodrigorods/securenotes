package com.rodrigorods.domain.notes.usecase

import com.rodrigorods.domain.notes.repository.NoteRepository

interface NoteUseCase {
    suspend fun getAllNotes()
    suspend fun createNote()
    suspend fun deleteNote()
    suspend fun updateNote()
}

class NoteUseCaseImpl(
    private val repository: NoteRepository
): NoteUseCase {
    override suspend fun getAllNotes() {
        repository.getAllNotes()
    }
    override suspend fun createNote() {
        repository.createNote()
    }

    override suspend fun deleteNote() {
        repository.deleteNote()
    }

    override suspend fun updateNote() {
        repository.updateNote()
    }
}
