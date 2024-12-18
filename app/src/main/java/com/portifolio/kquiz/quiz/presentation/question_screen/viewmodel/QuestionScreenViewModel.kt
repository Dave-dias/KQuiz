package com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.portifolio.kquiz.quiz.domain.model.Answer
import com.portifolio.kquiz.quiz.domain.model.GameScore
import com.portifolio.kquiz.quiz.domain.model.Question
import com.portifolio.kquiz.quiz.domain.use_case.GetQuestionsUseCase
import com.portifolio.kquiz.quiz.domain.use_case.SaveGameScoreUseCase
import com.portifolio.kquiz.quiz.presentation.core.QuestionScreenRoute
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.helper.TimerManager
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

@HiltViewModel
class QuestionScreenViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val saveGameScore: SaveGameScoreUseCase,
    private val timerManager: TimerManager
) : AndroidViewModel(application) {

    private var questionIterator: ListIterator<Question> = emptyList<Question>().listIterator()
    private val _effect = Channel<QuestionScreenEffect>()
    val effect = _effect.receiveAsFlow()
    private val _uiState = MutableStateFlow(QuestionScreenState())
    val uiState = _uiState
        .onStart {
            loadInitialData()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            QuestionScreenState()
        )

    fun processIntent(intent: QuestionScreenIntent) {

        viewModelScope.launch {

            when (intent) {

                is QuestionScreenIntent.NavigateBack, QuestionScreenIntent.FinishGame -> navigateBack()
                is QuestionScreenIntent.RetryLoading -> loadInitialData()
                is QuestionScreenIntent.SelectAnswer -> handleAnswer(intent.answer)
                is QuestionScreenIntent.NextQuestion -> nextQuestion()
            }
        }
    }

    private suspend fun loadInitialData() {

        _uiState.update {

            QuestionScreenState()
        }

        val totalOfQuestions = savedStateHandle.toRoute<QuestionScreenRoute>().numberOfQuestions

        val resultLoading = getQuestionsUseCase.invoke(
            amount = totalOfQuestions,
            category = savedStateHandle.toRoute<QuestionScreenRoute>().category,
            type = savedStateHandle.toRoute<QuestionScreenRoute>().type,
            difficulty = savedStateHandle.toRoute<QuestionScreenRoute>().difficulty
        )

        resultLoading.onSuccess { successResult ->

            questionIterator = successResult.listIterator()

            nextQuestion()

        }.onFailure { failure ->

            _uiState.update {

                it.copy(
                    isError = true,
                    errorMessage = failure.message ?: "Unknown error"
                )
            }
        }.also {

            _uiState.update {

                it.copy(
                    isLoading = false,
                    totalOfQuestions = totalOfQuestions
                )
            }
        }
    }

    private fun startNewTimer() {

        timerManager.startNewTimer(
            onTick = {
                handleTimerTick(it)
            },
            onFinish = {
                handleTimeRanOut()
            }
        )
    }

    private fun handleTimerTick(secondsToFinished: Int) {

        _uiState.update {

            it.copy(timeLeft = secondsToFinished)
        }
    }

    private fun handleTimeRanOut() {

        nextQuestion()
    }

    private fun handleAnswer(selectedAnswer: Answer) {

        viewModelScope.launch {

            timerManager.cancelTimer()

            _uiState.update { state ->

                state.copy(
                    question = state.question?.copy(
                        wasAnswered = true,
                        answers = state.question.answers.map { answer ->
                            answer.copy(isSelected = answer == selectedAnswer)
                        },
                    ),
                    correctAnswers = if (selectedAnswer.isCorrect) state.correctAnswers + 1 else state.correctAnswers
                )
            }
        }
    }

    private fun nextQuestion() = viewModelScope.launch {

        if (!questionIterator.hasNext()) {

            finishQuiz()
            return@launch
        }

        _uiState.update {

            it.copy(question = questionIterator.next())
        }

        startNewTimer()
    }

    private suspend fun finishQuiz() {

        _uiState.update {

            it.copy(isFinished = true)
        }

        saveGameResults()
    }

    private suspend fun saveGameResults() {

        saveGameScore(
            GameScore(
                correctAnswers = _uiState.value.correctAnswers,
                category = savedStateHandle.toRoute<QuestionScreenRoute>().category,
                difficulty = savedStateHandle.toRoute<QuestionScreenRoute>().difficulty,
                type = savedStateHandle.toRoute<QuestionScreenRoute>().type,
                totalOfQuestions = savedStateHandle.toRoute<QuestionScreenRoute>().numberOfQuestions,
            )
        )
    }

    private suspend fun navigateBack() {

        timerManager.cancelTimer()

        _effect.send(QuestionScreenEffect.NavigateBack)
    }
}