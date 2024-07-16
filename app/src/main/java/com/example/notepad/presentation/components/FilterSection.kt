package com.example.notepad.presentation.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.util.FilterType
import com.example.notepad.domain.util.SortType

@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    filterType: FilterType = FilterType.Date(SortType.Descending),
    onOrderChange: (FilterType) -> Unit,
    onColorFilterChange: (Int?) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Filter",
                style = MaterialTheme.typography.h6,
            )
            DefaultRadioButton(
                text = "Date",
                selected = filterType is FilterType.Date,
                onSelect = { onOrderChange(FilterType.Date(filterType.sortType)) }
            )
            DefaultRadioButton(
                text = "Importance",
                selected = filterType is FilterType.Importance,
                onSelect = { onOrderChange(FilterType.Importance(filterType.sortType)) }
            )
            DefaultRadioButton(
                text = "Title",
                selected = filterType is FilterType.Title,
                onSelect = { onOrderChange(FilterType.Title(filterType.sortType)) }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Order",
                style = MaterialTheme.typography.h6,
            )
            DefaultRadioButton(
                text = "Ascending",
                selected = filterType.sortType is SortType.Ascending,
                onSelect = {
                    onOrderChange(filterType.copy(SortType.Ascending))
                }
            )
            DefaultRadioButton(
                text = "Descending",
                selected = filterType.sortType is SortType.Descending,
                onSelect = {
                    onOrderChange(filterType.copy(SortType.Descending))
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Colors",
                style = MaterialTheme.typography.h6,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Gray, CircleShape)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = CircleShape
                        )
                        .clickable {
                            onColorFilterChange(null)
                        }
                ) {
                    Text(
                        text = "All",
                        style = MaterialTheme.typography.caption,
                        color = Color.White
                    )
                }
                Note.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color, CircleShape)
                            .border(
                                width = 2.dp,
                                color = Color.Black,
                                shape = CircleShape
                            )
                            .clickable {
                                onColorFilterChange(colorInt)
                            }
                    )
                }
            }
        }
    }
}
