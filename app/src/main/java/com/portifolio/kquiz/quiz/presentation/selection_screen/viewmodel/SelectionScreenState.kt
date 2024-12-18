package com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel

import androidx.compose.runtime.Immutable
import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.GameScore
import com.portifolio.kquiz.quiz.domain.model.QuestionPerRoundEnum
import com.portifolio.kquiz.quiz.domain.model.TypeEnum

@Immutable
data class SelectionScreenState(
    val isLoading: Boolean = true,
    val recentGameScores: List<GameScore> = emptyList(),
    val questionPerRoundEnum: List<QuestionPerRoundEnum> = QuestionPerRoundEnum.entries,
    val selectedQuestionsPerRound: QuestionPerRoundEnum = QuestionPerRoundEnum.FIVE,
    val types: List<TypeEnum> = TypeEnum.entries,
    val selectedType: TypeEnum = TypeEnum.TRUE_FALSE,
    val difficulties: List<DifficultyEnum> = DifficultyEnum.entries,
    val selectedDifficulty: DifficultyEnum = DifficultyEnum.EASY,
    val categories: List<CategoryEnum> = CategoryEnum.entries.sortedBy { it.categoryName },
    val selectedCategory: CategoryEnum = CategoryEnum.ANIMALS
)