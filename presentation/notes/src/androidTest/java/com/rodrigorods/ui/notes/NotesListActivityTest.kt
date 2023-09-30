package com.rodrigorods.ui.notes

import androidx.appcompat.app.AppCompatActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.domain.notes.usecase.NoteUseCase
import com.rodrigorods.domain.notes.usecase.NoteUseCaseImpl
import com.rodrigorods.ui.notes.biometric.BiometricPromptProvider
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class NotesListActivityTest {

    private val useCase = mockk<NoteUseCaseImpl>()

    private val alwaysAuthorizedProvider = object : BiometricPromptProvider {
        override fun displayBiometricAuthDialog(
            activity: AppCompatActivity,
            onAuthenticationListener: (Boolean) -> Unit
        ) {
            onAuthenticationListener(true)
        }
    }

    @Before
    fun setup() {
        startKoin { modules(
            module {
                factory<NoteUseCase> { useCase }
                factory<BiometricPromptProvider> { alwaysAuthorizedProvider }
                viewModel { NotesListViewModel(get()) }
            })
        }

        coEvery {
            useCase.getAllNotes()
        } returns listOf(
            Note(1, "title position 1", "description position 1"),
            Note(2, "title position 2", "description position 2"),
        )
        coEvery { useCase.createNote() } returns 66L
        coJustRun { useCase.updateNote(any(), any(), any()) }
        coJustRun { useCase.deleteNote(any()) }
    }

    @Test
    fun onInit_displayInitialListOfCharacters() {
        launch {
            assertNoteTitleAtPosition("title position 1", 0)
            assertNoteDescriptionAtPosition("description position 2", 1)
            assertRecyclerViewSize(2)
        }
    }

    @Test
    fun onAddNewNotes_listOfNotesShouldIncreaseSize() {
        launch {
            assertRecyclerViewSize(2)
            clickOnAddNewNotes()
            assertRecyclerViewSize(3)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}
