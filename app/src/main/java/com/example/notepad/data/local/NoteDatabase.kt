package com.example.notepad.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notepad.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase: RoomDatabase(){
    abstract val dao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}