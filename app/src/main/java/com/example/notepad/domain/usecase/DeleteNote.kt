package com.example.notepad.domain.usecase

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository

class DeleteNote(
    private val repo: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repo.deleteNote(note)
    }
}