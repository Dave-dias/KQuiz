package com.portifolio.kquiz.quiz.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.portifolio.kquiz.quiz.data.web.model.CategoryDtoEnum
import com.portifolio.kquiz.quiz.data.web.model.DifficultyDtoEnum
import com.portifolio.kquiz.quiz.data.web.model.TypeDtoEnum

@Entity(tableName = "game_score")
data class GameScoreDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: CategoryDtoEnum,
    val difficulty: DifficultyDtoEnum,
    val type: TypeDtoEnum,
    val correctAnswers: Int,
    val totalOfQuestions: Int
)