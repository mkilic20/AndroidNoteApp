package com.example.notepad.domain.usecase

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository
import com.example.notepad.domain.util.FilterType
import com.example.notepad.domain.util.SortType
import kotlinx.coroutines.flow.Flow

class GetNotes(private val repo: NoteRepository) {
    operator fun invoke(
        filterType: FilterType = FilterType.Id(SortType.Ascending)
    ): Flow<List<Note>>? {
        if (filterType.sortType == SortType.Ascending) {
            return when(filterType) {
                is FilterType.Importance -> repo.getNotesByImportance("ASC")
                is FilterType.Date -> repo.getNotesByDateAdded("ASC")
                is FilterType.Id -> repo.getNotes("ASC")
                is FilterType.Title -> repo.getNotesByTitle("ASC")
            }
        }
        else if (filterType.sortType == SortType.Descending) {
            return when(filterType) {
                is FilterType.Importance -> repo.getNotesByImportance("DESC")
                is FilterType.Date -> repo.getNotesByDateAdded("DESC")
                is FilterType.Id -> repo.getNotes("DESC")
                is FilterType.Title -> repo.getNotesByTitle("DESC")
            }
        }
        else return null
    }

}