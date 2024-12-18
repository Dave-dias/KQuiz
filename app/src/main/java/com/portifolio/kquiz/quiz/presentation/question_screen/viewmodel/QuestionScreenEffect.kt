package com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel

sealed class QuestionScreenEffect {

    data object NavigateBack : QuestionScreenEffect()
}