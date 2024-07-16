package com.example.notepad.presentation.notes

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.util.FilterType

sealed class NotesEvent {
    data class Filter(val filterType: FilterType): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object ToggleFilterSection: NotesEvent()
    data class SearchNoteByTitle(val title: String): NotesEvent()
    data class FilterByColor(val color: Int?) : NotesEvent()
}