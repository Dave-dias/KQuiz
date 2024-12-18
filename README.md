# KQuiz

KQuiz is a trivia app built with modern Android development practices, using Jetpack Compose for the UI and Kotlin as the primary programming language. The app allows users to answer trivia questions and track their progress in a user-friendly interface.

<img src="https://github.com/user-attachments/assets/5068d2b5-a670-40c1-9cc3-5d72998c0e67" width="150" />

<img src="https://github.com/user-attachments/assets/1e4b4651-c2eb-49e1-90b9-5209d181c76e" width="150" />

<img src="https://github.com/user-attachments/assets/ca9fd38b-47af-4419-8484-d20249c32039" width="150" />

<img src="https://github.com/user-attachments/assets/0e8e9cc6-8d50-4e50-86ea-eb4908797b8d" width="150" />

<img src="https://github.com/user-attachments/assets/6cd2f350-30f9-48f8-8f51-993afdfa7b27" width="150" />

## Features

- **Dynamic Trivia Questions**: Users can answer a variety of trivia questions.
- **State Management**: Handles different UI states like loading, errors, and completed quizzes.
- **Modern UI**: Built with Jetpack Compose for a declarative and flexible UI experience.
- **Interactive Components**: Includes customized buttons, app bars, and lazy-loaded lists for questions.

## Tech Stack

- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Navigation**: Jetpack Navigation Component
- **State Management**: Compose's state handling

## Project Structure

The project follows a feature-based structure:

```
KQuiz/
├── core/                 # Shared utilities and resources
├── quiz/                 # Quiz-related screens and logic
│   ├── presentation/     # UI components, ViewModels, and preview utilities
│   ├── data/             # Data models and repository (if applicable)
│   └── domain/           # Business logic and use cases
```

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Dave-dias/KQuiz.git
   ```
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

## Usage

- Launch the app and start answering trivia questions.
- Navigate through the questions using the "Next" button.
- Retry if you encounter errors or finish the quiz to see your score.

## License

All rights reserved. This project is for portfolio demonstration purposes only and is not open for redistribution or modification.

This project is a portfolio demonstration and is not open for contributions. Thank you for your interest!
