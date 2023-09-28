package com.rodrigorods.domain.notes.repository

import com.rodrigorods.domain.notes.model.Note

interface NoteRepository {
    suspend fun getAllNotes(): List<Note>
    suspend fun createNote(): Long
    suspend fun deleteNote()
    suspend fun updateNote()
}