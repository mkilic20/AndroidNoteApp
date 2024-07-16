package com.example.notepad.domain.util

sealed class FilterType(val sortType: SortType) {
    class Date(sortType: SortType): FilterType(sortType)
    class Importance(sortType: SortType): FilterType(sortType)
    class Id(sortType: SortType): FilterType(sortType)
    class Title(sortType: SortType): FilterType(sortType)

    fun copy(sortType: SortType): FilterType {
        return when(this) {
            is Date -> Date(sortType)
            is Importance -> Importance(sortType)
            is Id -> Id(sortType)
            is Title -> Title(sortType)
        }
    }
}