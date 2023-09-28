package com.rodrigorods.data.notes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {
    @Query("SELECT * FROM Notes")
    suspend fun getAllNotes(): List<NoteEntity>

    @Insert
    suspend fun addNote(note: NoteEntity): Long

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("DELETE FROM Notes WHERE id=:noteId")
    suspend fun deleteNote(noteId: Long)
}
