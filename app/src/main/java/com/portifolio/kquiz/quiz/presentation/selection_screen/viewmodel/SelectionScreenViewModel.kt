package com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.QuestionPerRoundEnum
import com.portifolio.kquiz.quiz.domain.model.TypeEnum
import com.portifolio.kquiz.quiz.domain.use_case.GetRecentGameScoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DEFAULT_AMOUNT_RECENT_SCORES = 15

@HiltViewModel
class SelectionScreenViewModel @Inject constructor(
    application: Application,
    private val getRecentGameScoresUseCase: GetRecentGameScoresUseCase
) : AndroidViewModel(application) {

    private val _effect = Channel<SelectionScreenEvent>()
    val effect = _effect.receiveAsFlow()
    private val _state = MutableStateFlow(SelectionScreenState())
    val state = _state
        .onStart {
            loadInitialData()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SelectionScreenState()
        )

    fun processIntent(intent: SelectionScreenIntent) {

        when (intent) {

            is SelectionScreenIntent.SelectedQuestionsPerRound -> onQuestionsPerRoundSelected(intent.questionPerRound)
            is SelectionScreenIntent.SelectedType -> onTypeSelected(intent.type)
            is SelectionScreenIntent.SelectedDifficulty -> onDifficultySelected(intent.difficulty)
            is SelectionScreenIntent.SelectedCategory -> onCategorySelected(intent.category)
            is SelectionScreenIntent.SelectedPlay -> onPlaySelected()
        }
    }

    private fun loadInitialData() {

        _state.update {

            it.copy(isLoading = true)
        }

        viewModelScope.launch {

            val recentGameScores = getRecentGameScoresUseCase(DEFAULT_AMOUNT_RECENT_SCORES)

            _state.update {

                it.copy(
                    isLoading = false,
                    recentGameScores = recentGameScores,
                )
            }
        }
    }

    private fun onQuestionsPerRoundSelected(questionsPerRound: QuestionPerRoundEnum) {

        _state.update {

            it.copy(
                selectedQuestionsPerRound = questionsPerRound
            )
        }
    }

    private fun onTypeSelected(type: TypeEnum) {

        _state.update {

            it.copy(
                selectedType = type
            )
        }
    }

    private fun onDifficultySelected(difficulty: DifficultyEnum) {

        _state.update {

            it.copy(
                selectedDifficulty = difficulty
            )
        }
    }

    private fun onCategorySelected(category: CategoryEnum) {

        _state.update {

            it.copy(
                selectedCategory = category
            )
        }
    }

    private fun onPlaySelected() {

        viewModelScope.launch {

            val currentState = _state.value

            _effect.send(
                SelectionScreenEvent.NavigateToQuestionScreen(
                    questionPerRound = currentState.selectedQuestionsPerRound.number,
                    type = currentState.selectedType,
                    difficulty = currentState.selectedDifficulty,
                    category = currentState.selectedCategory
                )
            )
        }
    }
}