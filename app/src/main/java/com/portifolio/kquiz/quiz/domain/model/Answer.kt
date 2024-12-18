package com.portifolio.kquiz.quiz.domain.model

data class Answer(
    val description: String,
    val isCorrect: Boolean,
    val isSelected: Boolean = false
)
