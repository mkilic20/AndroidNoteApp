package com.example.notepad.domain.usecase

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNoteByTitle(private val repo : NoteRepository) {
    operator fun invoke(title: String): Flow<List<Note>> {
        return repo.getNoteByTitle(title)
    }
}