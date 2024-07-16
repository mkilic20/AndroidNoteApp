package com.example.notepad.presentation.note

data class EmptyNoteState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)