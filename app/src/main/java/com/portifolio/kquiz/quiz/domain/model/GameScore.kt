package com.portifolio.kquiz.quiz.domain.model

data class GameScore(
    val id: Int = 0,
    val category: CategoryEnum,
    val difficulty: DifficultyEnum,
    val type: TypeEnum,
    val correctAnswers: Int,
    val totalOfQuestions: Int
)