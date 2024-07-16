# AndroidNoteApp

AndroidNoteApp is a simple and intuitive note-taking application for Android devices. It allows users to create, edit, and delete notes efficiently. This project demonstrates essential Android development practices, including UI design, data storage, and user interaction handling.

## Features

- Create, edit, and delete notes
- Simple and user-friendly interface
- Notes are stored locally on the device
- Lightweight and efficient performance

## Technologies Used

- **Kotlin**: The primary programming language for Android development in this project
- **Android Studio**: The integrated development environment (IDE) used for developing and testing the application
- **ConstraintLayout**: Used for designing the user interface
- **Room**: An SQLite object mapping library to handle database operations
- **LiveData**: Lifecycle-aware data holder to build data-driven UIs
- **ViewModel**: Designed to store and manage UI-related data in a lifecycle-conscious way
- **MVVM Architecture**: Model-View-ViewModel architecture to separate concerns and improve code maintainability
- **Gradle**: Build automation tool for managing project dependencies and configurations

## Project Structure

- `app/src/main/java/com/example/androidnoteapp`: Contains the Kotlin source code
  - `MainActivity.kt`: The main activity handling user interactions and displaying the notes list
  - `Note.kt`: Data class representing a note
  - `NoteDao.kt`: Data access object interface for database operations
  - `NoteDatabase.kt`: Abstract class for the Room database
  - `NoteRepository.kt`: Repository class for abstracting data sources
  - `NoteViewModel.kt`: ViewModel for managing UI-related data
- `app/src/main/res/layout`: Contains the layout XML files
  - `activity_main.xml`: Layout file for the main activity
  - `note_item.xml`: Layout file for individual note items in the list
- `build.gradle.kts`: Gradle build script for managing project dependencies and build configurations
- `.idea`, `gradle/wrapper`, etc.: Configuration files and directories for the project setup

## Getting Started

### Prerequisites

- Android Studio
- Android device or emulator

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/mkilic20/AndroidNoteApp.git
