package com.example.notepad.domain.usecase

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNote(id)
    }
}