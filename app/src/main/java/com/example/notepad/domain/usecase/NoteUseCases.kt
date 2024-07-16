package com.example.notepad.domain.usecase

data class NoteUseCases(
    val createNote: CreateNote,
    val deleteNote: DeleteNote,
    val getNoteByTitle: GetNoteByTitle,
    val getNotes: GetNotes,
    val getNotesByColor: GetNotesByColor,
    val getNote: GetNote
)