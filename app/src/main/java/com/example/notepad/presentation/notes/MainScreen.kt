package com.example.notepad.presentation.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notepad.R
import com.example.notepad.presentation.components.FilterSection
import com.example.notepad.presentation.components.NoteItem
import com.example.notepad.presentation.util.Screen
import kotlinx.coroutines.launch
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            Button(
                onClick = {
                    navController.navigate(Screen.NoteScreen.route)
                },
                colors = ButtonDefaults.buttonColors(Color.DarkGray)
            ) {
                Text(text = "Add a new note", style = MaterialTheme.typography.h6)
            }
        },
        scaffoldState = scaffoldState
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min), // Ensure row height is minimal required height
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo), // Replace with your actual logo resource ID
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(40.dp) // Adjust the size as needed
                            .padding(end = 8.dp) // Optional: add some padding to the right of the logo
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("KU")
                            addStyle(
                                style = SpanStyle(color = Color.Red),
                                start = 0,
                                end = 2
                            )
                            append("note")
                        },
                        style = MaterialTheme.typography.h4
                    )
                }
                TextButton(
                    onClick = {
                        viewModel.onEvent(NotesEvent.ToggleFilterSection)
                    },
                ) {
                    Text(text="Filters", style=MaterialTheme.typography.h6)
                }
            }
            if (state.isFilterSectionVisible) {
                FilterSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    filterType = state.filterType,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Filter(it))
                    },
                    onColorFilterChange = {
                        viewModel.onEvent(NotesEvent.FilterByColor(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.searchQuery,
                onValueChange = {
                    viewModel.onEvent(NotesEvent.SearchNoteByTitle(it))
                },
                label = { Text("Search by title") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.onSurface,
                    backgroundColor = MaterialTheme.colors.surface
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.NoteScreen.route +
                                            "?noteId=${note.id}&noteColor=${note.color}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note deleted"
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
