package com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel

import com.portifolio.kquiz.quiz.domain.model.Answer

sealed class QuestionScreenIntent {

    data object RetryLoading: QuestionScreenIntent()
    data object NavigateBack: QuestionScreenIntent()
    data class SelectAnswer(val answer: Answer) : QuestionScreenIntent()
    data object NextQuestion : QuestionScreenIntent()
    data object FinishGame : QuestionScreenIntent()
}