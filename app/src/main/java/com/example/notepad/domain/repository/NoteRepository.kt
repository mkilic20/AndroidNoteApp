package com.example.notepad.domain.repository

import com.example.notepad.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotesByDateAdded(sort: String): Flow<List<Note>>
    fun getNotesByImportance(sort: String): Flow<List<Note>>
    fun getNotesByTitle(sort: String): Flow<List<Note>>
    fun getNotes(sort: String): Flow<List<Note>>
    fun getNotesByColor(color: Int): Flow<List<Note>>
    fun getNoteByTitle(query: String): Flow<List<Note>>
    suspend fun upsertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getNote(id: Int): Note?
}
