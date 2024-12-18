package com.portifolio.kquiz.quiz.presentation.core

import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.TypeEnum
import kotlinx.serialization.Serializable

@Serializable
data object SelectionScreenRoute

@Serializable
data class QuestionScreenRoute(
    val numberOfQuestions: Int,
    val category: CategoryEnum,
    val difficulty: DifficultyEnum,
    val type: TypeEnum
)