package com.example.notepad.data.repository
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.notepad.data.local.NoteDao
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override fun getNotes(sort: String): Flow<List<Note>> {
        val query = SimpleSQLiteQuery("SELECT * FROM note ORDER BY id $sort")
        return noteDao.getNotes(query)
    }

    override fun getNotesByDateAdded(sort: String): Flow<List<Note>> {
        val query = SimpleSQLiteQuery("SELECT * FROM note ORDER BY dateAdded $sort")
        return noteDao.getNotesByDateAdded(query)
    }

    override fun getNotesByImportance(sort: String): Flow<List<Note>> {
        val query = SimpleSQLiteQuery("SELECT * FROM note ORDER BY importance $sort")
        return noteDao.getNotesByImportance(query)
    }

    override fun getNotesByTitle(sort: String): Flow<List<Note>> {
        val query = SimpleSQLiteQuery("SELECT * FROM note ORDER BY title $sort")
        return noteDao.getNotesByImportance(query)
    }
    override fun getNotesByColor(color: Int): Flow<List<Note>> {
        val query = SimpleSQLiteQuery("SELECT * FROM note WHERE color = ? ORDER BY id", arrayOf(color))
        return noteDao.getNotesByColor(query)
    }

    override fun getNoteByTitle(query: String): Flow<List<Note>> {
        val newQuery = SimpleSQLiteQuery("SELECT * FROM note WHERE title LIKE '%' || ? || '%' ORDER BY id ASC", arrayOf(query))
        return noteDao.getNotesByImportance(newQuery)
    }

    override suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun getNote(id: Int): Note?{
        return noteDao.getNote(id)
    }
}