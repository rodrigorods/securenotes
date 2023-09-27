package com.rodrigorods.data.notes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDAO {
    @Query("SELECT * FROM Notes")
    suspend fun getAllNotes(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteEntity)

    @Update(entity = NoteEntity::class)
    suspend fun updateNote()

    @Query("DELETE FROM Notes WHERE id=:noteId")
    suspend fun deleteNote(noteId: Long)
}
