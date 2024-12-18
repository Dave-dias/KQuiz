package com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel

import androidx.compose.runtime.Immutable
import com.portifolio.kquiz.quiz.domain.model.Question

@Immutable
data class QuestionScreenState(
    val correctAnswers: Int = 0,
    val totalOfQuestions: Int = 0,
    val question: Question?  = null,
    val timeLeft: Int = 0,
    val isLoading: Boolean = true,
    val isError : Boolean = false,
    val errorMessage: String = "",
    val isFinished: Boolean = false
)

