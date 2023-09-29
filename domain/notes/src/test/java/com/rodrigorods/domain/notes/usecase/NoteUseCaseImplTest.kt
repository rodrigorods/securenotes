package com.rodrigorods.domain.notes.usecase

import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.domain.notes.repository.NoteRepository
import io.mockk.Runs
import io.mockk.awaits
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NoteUseCaseImplTest {

    private val repository = mockk<NoteRepository>()
    private val useCase = NoteUseCaseImpl(repository)

    @Before
    fun setup() {
        coJustRun { repository.createNote() }
        coJustRun { repository.updateNote(any(), any(), any()) }
        coJustRun { repository.deleteNote(any()) }
    }

    @Test
    fun getAllNotes_returnExpectedListOfNotes() = runBlocking {
        val expectedResult = emptyList<Note>()
        coEvery { repository.getAllNotes() } returns expectedResult

        assertEquals(useCase.getAllNotes(), expectedResult)
    }

    @Test
    fun createNote_verifyRepositoryFunctionWasCalled() = runBlocking {
        useCase.createNote()
        coVerify { repository.createNote() }
    }

    @Test
    fun deleteNote_shouldDeleteCorrectNoteId() = runBlocking {
        val expectedIdToDeletion = 99L
        useCase.deleteNote(expectedIdToDeletion)
        coVerify { repository.deleteNote(expectedIdToDeletion) }
    }

    @Test
    fun updateNote_shouldUpdateWithExpectedParams() = runBlocking {
        useCase.updateNote(1L, "expectedTitle", "expectedDescription")
        coVerify {
            repository.updateNote(1L, "expectedTitle", "expectedDescription")
        }
    }
}