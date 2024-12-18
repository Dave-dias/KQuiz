package com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel

import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.TypeEnum

sealed class SelectionScreenEvent {

    data class NavigateToQuestionScreen(
        val questionPerRound: Int,
        val type: TypeEnum,
        val difficulty: DifficultyEnum,
        val category: CategoryEnum
    ) : SelectionScreenEvent()
}