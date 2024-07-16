package com.example.notepad.domain.usecase

import com.example.notepad.domain.model.InvalidNoteException
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository

class CreateNote(
    private val repo: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("Empty Title")
        }
        if(note.description.isBlank()) {
            throw InvalidNoteException("Empty Description")
        }
        if(note.importance == -1) {
            throw InvalidNoteException("Empty Importance")
        }
        if(note.color == -1) {
            throw InvalidNoteException("Empty Color")
        }
        repo.upsertNote(note)
    }
}