package com.portifolio.kquiz.quiz.presentation.question_screen

import com.portifolio.kquiz.quiz.domain.model.Answer
import com.portifolio.kquiz.quiz.domain.model.Question
import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.QuestionScreenState

object QuestionScreenPreviewData {

    val multipleChoiceAnswers = listOf(
        Answer(
            description = "Rio de Janeiro",
            isCorrect = false
        ),
        Answer(
            description = "São Paulo",
            isCorrect = false
        ),
        Answer(
            description = "Brasília",
            isCorrect = true
        ),
        Answer(
            description = "Salvador",
            isCorrect = false
        )
    )

    val multipleAnswerQuestion = Question(
        description = "What is the capital of Brazil?",
        answers = multipleChoiceAnswers
    )

    val defaultQuestionScreenState = QuestionScreenState(
        isLoading = false,
        question = multipleAnswerQuestion,
        correctAnswers = 7,
        totalOfQuestions = 14
    )

    val loadingQuestionScreenState = QuestionScreenState()

    val errorQuestionScreenState = QuestionScreenState(
        isLoading = false,
        isError = true,
        errorMessage = "Unable to resolve host http://179.92.02.23:7070/api/questions",
    )
}