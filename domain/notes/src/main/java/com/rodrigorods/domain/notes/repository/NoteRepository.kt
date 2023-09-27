package com.rodrigorods.domain.notes.repository

interface NoteRepository {
    suspend fun getAllNotes()
    suspend fun createNote()
    suspend fun deleteNote()
    suspend fun updateNote()
}