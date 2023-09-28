package com.rodrigorods.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodrigorods.domain.notes.model.Note
import com.rodrigorods.domain.notes.usecase.NoteUseCase

sealed class UIState {
    object Waiting : UIState()
    object DisplayingUI : UIState()
    object EmptyList : UIState()
}

class NotesListViewModel(
    private val useCase: NoteUseCase
): ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes


}