package com.example.notepad.data.di

import android.app.Application
import androidx.room.Room
import com.example.notepad.data.local.NoteDatabase
import com.example.notepad.data.repository.NoteRepositoryImpl
import com.example.notepad.domain.repository.NoteRepository
import com.example.notepad.domain.usecase.CreateNote
import com.example.notepad.domain.usecase.DeleteNote
import com.example.notepad.domain.usecase.GetNote
import com.example.notepad.domain.usecase.GetNoteByTitle
import com.example.notepad.domain.usecase.GetNotes
import com.example.notepad.domain.usecase.GetNotesByColor
import com.example.notepad.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME).build()
    }


    @Provides
    @Singleton
    fun provideNoteRepo(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repo: NoteRepository) : NoteUseCases {
        return NoteUseCases(createNote = CreateNote(repo),
            deleteNote = DeleteNote(repo),
            getNotes = GetNotes(repo),
            getNoteByTitle = GetNoteByTitle(repo),
            getNotesByColor = GetNotesByColor(repo),
            getNote = GetNote(repo)
            )
    }
}