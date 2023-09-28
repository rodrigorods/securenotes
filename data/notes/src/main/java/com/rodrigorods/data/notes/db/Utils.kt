package com.rodrigorods.data.notes.db

import android.content.Context
import androidx.room.Room
import com.rodrigorods.data.notes.NoteDatabase

fun provideDatabase(context: Context): NoteDatabase {
    val databaseName = "SecureNotesDatabase"
    return Room.databaseBuilder(context, NoteDatabase::class.java, databaseName)
        .fallbackToDestructiveMigration()
        .build()
}

fun provideNotesDao(database: NoteDatabase) = database.notesDao()

