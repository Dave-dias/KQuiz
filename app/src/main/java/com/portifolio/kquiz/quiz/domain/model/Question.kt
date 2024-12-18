package com.portifolio.kquiz.quiz.domain.model

data class Question(
    val description: String,
    val answers: List<Answer>,
    val wasAnswered: Boolean = false
)
