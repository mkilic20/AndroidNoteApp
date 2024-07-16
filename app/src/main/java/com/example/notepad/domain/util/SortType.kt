package com.example.notepad.domain.util

sealed class SortType {
    object Ascending: SortType()
    object Descending: SortType()
}