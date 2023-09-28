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
    data class AddedNewNote(val newNote: Note) : UIState()
    object EmptyList : UIState()
}

class NotesListViewModel(
    private val useCase: NoteUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<UIState>(UIState.Waiting)
    val uiState: LiveData<UIState> = _uiState

    fun getAllNotes() {
        viewModelScope.launch {
            val notesList = useCase.getAllNotes()
            _uiState.postValue(UIState.DisplayingUI(notesList))
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
}
