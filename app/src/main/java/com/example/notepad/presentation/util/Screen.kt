package com.example.notepad.presentation.util

sealed class Screen(val route: String) {
    object NotesScreen: Screen("notes_screen")
    object NoteScreen: Screen("note_screen")
}