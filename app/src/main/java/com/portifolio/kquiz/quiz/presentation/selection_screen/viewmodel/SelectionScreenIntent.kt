package com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel

import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.QuestionPerRoundEnum
import com.portifolio.kquiz.quiz.domain.model.TypeEnum

sealed class SelectionScreenIntent {

    data class SelectedQuestionsPerRound(val questionPerRound: QuestionPerRoundEnum): SelectionScreenIntent()
    data class SelectedType(val type: TypeEnum): SelectionScreenIntent()
    data class SelectedDifficulty(val difficulty: DifficultyEnum): SelectionScreenIntent()
    data class SelectedCategory(val category: CategoryEnum): SelectionScreenIntent()
    data object SelectedPlay: SelectionScreenIntent()
}