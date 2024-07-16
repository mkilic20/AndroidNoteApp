package com.example.notepad.presentation.note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.domain.model.InvalidNoteException
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.usecase.NoteUseCases

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(EmptyNoteState(
        hint = "Title"
    ))
    val noteTitle: State<EmptyNoteState> = _noteTitle

    private val _noteDescription = mutableStateOf(EmptyNoteState(
        hint = "Description"
    ))
    val noteDescription: State<EmptyNoteState> = _noteDescription

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _noteImportance = mutableStateOf(5) // Default importance
    val noteImportance: State<Int> = _noteImportance

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteDescription.value = _noteDescription.value.copy(
                            text = note.description,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                        _noteImportance.value = note.importance
                    }
                }
            }
        }
    }

    fun onEvent(event: NoteEvent) {
        when(event) {
            is NoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is NoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is NoteEvent.EnteredContent -> {
                _noteDescription.value = _noteDescription.value.copy(
                    text = event.value
                )
            }
            is NoteEvent.ChangeContentFocus -> {
                _noteDescription.value = _noteDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteDescription.value.text.isBlank()
                )
            }
            is NoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is NoteEvent.ChangeImportance -> {
                _noteImportance.value = event.importance
            }
            is NoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.createNote(
                            Note(
                                title = noteTitle.value.text,
                                description = noteDescription.value.text,
                                dateAdded = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId ?: 0,
                                importance = noteImportance.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch(e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}
