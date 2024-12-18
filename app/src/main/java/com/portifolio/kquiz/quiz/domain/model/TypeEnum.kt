package com.portifolio.kquiz.quiz.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class TypeEnum(val description: String) {

    TRUE_FALSE("Binary"),
    MULTIPLE_CHOICE("Multiple");
}