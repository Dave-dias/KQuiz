package com.portifolio.kquiz.quiz.presentation.selection_screen

import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.GameScore
import com.portifolio.kquiz.quiz.domain.model.TypeEnum
import com.portifolio.kquiz.quiz.presentation.selection_screen.viewmodel.SelectionScreenState

object SelectionScreenPreviewData {

    val shortLengthGameScore = GameScore(
        category = CategoryEnum.ART,
        difficulty = DifficultyEnum.EASY,
        type = TypeEnum.TRUE_FALSE,
        correctAnswers = 2,
        totalOfQuestions = 5
    )

    val mediumLengthGameScore = GameScore(
        category = CategoryEnum.ENTERTAINMENT_CARTOON_ANIMATIONS,
        difficulty = DifficultyEnum.EASY,
        type = TypeEnum.MULTIPLE_CHOICE,
        correctAnswers = 12,
        totalOfQuestions = 15
    )

    private val defaultRecentGameScores = listOf(
        shortLengthGameScore,
        mediumLengthGameScore
    )

    val loadingSelectionScreenState = SelectionScreenState()

    val defaultSelectionScreenState = SelectionScreenState(
        isLoading = false,
        selectedCategory = CategoryEnum.ART,
        selectedDifficulty = DifficultyEnum.MEDIUM,
        selectedType = TypeEnum.MULTIPLE_CHOICE,
        recentGameScores = defaultRecentGameScores
    )
}