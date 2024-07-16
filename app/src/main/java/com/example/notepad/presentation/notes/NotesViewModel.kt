package com.example.notepad.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.domain.usecase.NoteUseCases
import com.example.notepad.domain.util.FilterType
import com.example.notepad.domain.util.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var getNotesJob: Job? = null

    init {
        getNotes(FilterType.Date(SortType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Filter -> {
                if (state.value.filterType::class == event.filterType::class &&
                    state.value.filterType.sortType == event.filterType.sortType
                ) {
                    return
                }
                getNotes(event.filterType)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                }
            }

            is NotesEvent.ToggleFilterSection -> {
                _state.value =
                    state.value.copy(isFilterSectionVisible = !state.value.isFilterSectionVisible)
            }

            is NotesEvent.SearchNoteByTitle -> {
                updateSearchQuery(event.title)
            }

            is NotesEvent.FilterByColor -> {
                getNotesByColor(event.color)
            }
        }
    }

    private fun getNotes(filterType: FilterType) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(filterType)
            ?.onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    filterType = filterType
                )
            }
            ?.launchIn(viewModelScope)
    }

    private fun updateSearchQuery(query: String) {
        _state.value = state.value.copy(searchQuery = query)
        if (query.isBlank()) {
            getNotes(state.value.filterType)
        } else {
            searchNotesByTitle(query)
        }
    }

    private fun searchNotesByTitle(title: String) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNoteByTitle(title)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getNotesByColor(color: Int?) {
        if ((color == 9) or (color == null)) {getNotes(state.value.filterType)}
        else if (color != null) {
            getNotesJob?.cancel()
            getNotesJob = noteUseCases.getNotesByColor(color)
                .onEach { notes ->
                    _state.value = state.value.copy(
                        notes = notes
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}