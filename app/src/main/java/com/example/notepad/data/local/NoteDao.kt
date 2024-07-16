package com.example.notepad.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Upsert
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.notepad.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @RawQuery(observedEntities = [Note::class])
    fun getNotes(query: SupportSQLiteQuery): Flow<List<Note>>

    @RawQuery(observedEntities = [Note::class])
    fun getNotesByDateAdded(query: SupportSQLiteQuery): Flow<List<Note>>
    @RawQuery(observedEntities = [Note::class])
    fun getNotesByTitle(query: SupportSQLiteQuery): Flow<List<Note>>

    @RawQuery(observedEntities = [Note::class])
    fun getNotesByImportance(query: SupportSQLiteQuery): Flow<List<Note>>
    //@Query("SELECT * FROM note WHERE color = (:color) ORDER BY id")
    @RawQuery(observedEntities = [Note::class])
    fun getNotesByColor(query: SupportSQLiteQuery): Flow<List<Note>>
    //@Query("SELECT * FROM note WHERE title LIKE '%' || (:query) || '%' ORDER BY id ASC")
    @RawQuery(observedEntities = [Note::class])
    fun getNoteByTitle(query: SupportSQLiteQuery): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = (:id)")
    suspend fun getNote(id: Int): Note?

}