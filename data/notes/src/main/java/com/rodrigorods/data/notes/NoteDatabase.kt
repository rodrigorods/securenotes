package com.rodrigorods.data.notes

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodrigorods.data.notes.db.NoteEntity
import com.rodrigorods.data.notes.db.NotesDAO

@Database(
    entities = [NoteEntity::class], version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDAO
}