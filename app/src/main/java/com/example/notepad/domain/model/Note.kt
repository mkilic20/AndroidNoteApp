package com.example.notepad.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notepad.ui.theme.SoftBlueNoteColor
import com.example.notepad.ui.theme.SoftGreenNoteColor
import com.example.notepad.ui.theme.SoftPurpleNoteColor
import com.example.notepad.ui.theme.SoftRedNoteColor
import com.example.notepad.ui.theme.SoftYellowNoteColor

@Entity(tableName = "note")
data class Note(
    val title: String,
    val description: String,
    val dateAdded: Long,
    val importance: Int,
    val color: Int,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
){
    companion object {
        val noteColors = listOf(SoftRedNoteColor, SoftGreenNoteColor, SoftBlueNoteColor, SoftYellowNoteColor, SoftPurpleNoteColor)

    }
}
