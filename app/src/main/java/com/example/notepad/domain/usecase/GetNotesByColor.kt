package com.example.notepad.domain.usecase

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesByColor(private val repo : NoteRepository) {
    operator fun invoke(color: Int): Flow<List<Note>> {
        return repo.getNotesByColor(color)
    }
}