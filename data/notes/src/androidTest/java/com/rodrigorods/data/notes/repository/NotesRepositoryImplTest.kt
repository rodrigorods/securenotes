package com.rodrigorods.data.notes.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rodrigorods.data.notes.NoteDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesRepositoryImplTest {

    private lateinit var repository: NotesRepositoryImpl
    private lateinit var context: Context
    private lateinit var database: NoteDatabase

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        repository = NotesRepositoryImpl(database.notesDao())
    }

    @Test
    fun getAllNotes() = runBlocking {
        repository.createNote()
        repository.createNote()
        Assert.assertEquals(repository.getAllNotes().size, 2)
    }

    @Test
    fun createNote() = runBlocking {
        val addedId = repository.createNote()
        Assert.assertNotEquals(addedId, 0)
    }

    @Test
    fun deleteNotes() = runBlocking {
        longArrayOf(
            repository.createNote(), repository.createNote()
        ).forEach {
            repository.deleteNote(it)
        }

        Assert.assertEquals(repository.getAllNotes().size, 0)
    }

    @Test
    fun updateNote() = runBlocking {
        longArrayOf(
            repository.createNote(), repository.createNote()
        ).forEach {
            repository.updateNote(it, "newTitle", "bbb")
        }

        val allNotes = repository.getAllNotes()
        Assert.assertEquals(allNotes.size, 2)
        Assert.assertEquals(allNotes[1].title, "newTitle")
    }

    @Before
    fun tearDown() {
        database.close()
    }
}
