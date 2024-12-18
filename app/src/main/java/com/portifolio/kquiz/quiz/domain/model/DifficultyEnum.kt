package com.portifolio.kquiz.quiz.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class DifficultyEnum(val description: String) {

    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");
}