package com.portifolio.kquiz.quiz.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class QuestionPerRoundEnum(val number: Int) {

    FIVE(5),
    TEN( 10),
    TWENTY(20),
    THIRTY(30);
}