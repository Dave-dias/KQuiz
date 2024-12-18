package com.portifolio.kquiz.quiz.data.web.model

data class ResultDtoWrapper(
    @Suppress("PropertyName", "PropertyName", "PropertyName") val response_code: Int,
    val results: List<QuestionDto>
) {

    data class QuestionDto(
        val type: String,
        val difficulty: String,
        val category: String,
        val question: String,
        val correct_answer: String,
        val incorrect_answers: List<String>
    )
}
