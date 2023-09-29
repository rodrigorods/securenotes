package com.rodrigorods.data.notes.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rodrigorods.data.notes.NoteDatabase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilsKtTest {

    private lateinit var context: Context
    private lateinit var database: NoteDatabase

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
    }

    @Test
    fun provideNotesDao_returnExpectedDaoObject() {
        Assert.assertTrue(
            provideNotesDao(database) is NotesDAO
        )
    }
}