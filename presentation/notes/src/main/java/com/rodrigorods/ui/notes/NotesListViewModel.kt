package com.rodrigorods.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.domain.notes.usecase.NoteUseCase
import kotlinx.coroutines.launch

sealed class UIState {
    object Waiting : UIState()
    data class DisplayingUI(val notes: List<Note>) : UIState()
    data class DisplayingObfuscatedUI(val notes: List<Note>) : UIState()
    data class AddedNewNote(val newNote: Note) : UIState()
    object EmptyList : UIState()
}

class NotesListViewModel(
    private val useCase: NoteUseCase
): ViewModel() {

    var isUserAuthenticated = false
        private set
        get() = field

    private val _uiState = MutableLiveData<UIState>(UIState.Waiting)
    val uiState: LiveData<UIState> = _uiState

    fun getAllNotes() {
        viewModelScope.launch {
            val notesList = useCase.getAllNotes()
            val state =
                if (isUserAuthenticated) UIState.DisplayingUI(notesList)
                else UIState.DisplayingObfuscatedUI(notesList)

            _uiState.postValue(state)
        }
    }

    fun addNote() {
        viewModelScope.launch {
            val addedNoteId = useCase.createNote()
            _uiState.postValue(UIState.AddedNewNote(
                Note(
                    id = addedNoteId
                ))
            )
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            useCase.deleteNote(noteId)
        }
    }

    fun editNote(noteId: Long, newTitle: String, newDescription: String) {
        viewModelScope.launch {
            useCase.updateNote(noteId, newTitle, newDescription)
        }
    }

    fun setUserAuthentication(authenticated: Boolean) {
        isUserAuthenticated = authenticated
        getAllNotes()
    }
}
