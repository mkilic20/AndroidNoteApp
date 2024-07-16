package com.example.notepad.presentation.notes

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.util.FilterType
import com.example.notepad.domain.util.SortType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val filterType: FilterType =  FilterType.Date(SortType.Descending),
    val isFilterSectionVisible: Boolean = false,
    val searchQuery: String = ""
)
