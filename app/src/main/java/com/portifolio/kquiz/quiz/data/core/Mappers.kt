package com.portifolio.kquiz.quiz.data.core

import com.portifolio.kquiz.quiz.data.db.model.GameScoreDto
import com.portifolio.kquiz.quiz.data.web.model.CategoryDtoEnum
import com.portifolio.kquiz.quiz.data.web.model.DifficultyDtoEnum
import com.portifolio.kquiz.quiz.data.web.model.ResultDtoWrapper
import com.portifolio.kquiz.quiz.data.web.model.TypeDtoEnum
import com.portifolio.kquiz.quiz.domain.model.Answer
import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.GameScore
import com.portifolio.kquiz.quiz.domain.model.Question
import com.portifolio.kquiz.quiz.domain.model.TypeEnum
import java.net.URLDecoder

fun ResultDtoWrapper.QuestionDto.toQuestion(): Question {

    val incorrectAnswers = this.incorrect_answers.toMutableList().map {
        Answer(
            description = URLDecoder.decode(it),
            isCorrect = false
        )
    }

    return Question(
        description = URLDecoder.decode(this.question),
        answers = incorrectAnswers.plus(
            Answer(
                description = URLDecoder.decode(this.correct_answer),
                isCorrect = true
            )
        )
    )
}

fun CategoryEnum.toCategoryDtoEnum(): CategoryDtoEnum {

    return CategoryDtoEnum.valueOf(this.name)
}

fun CategoryDtoEnum.toCategoryEnum(): CategoryEnum {

    return CategoryEnum.valueOf(this.name)
}

fun DifficultyEnum.toDifficultyDtoEnum(): DifficultyDtoEnum {

    return DifficultyDtoEnum.valueOf(this.name)
}

fun DifficultyDtoEnum.toDifficultyEnum(): DifficultyEnum {

    return DifficultyEnum.valueOf(this.name)
}

fun TypeEnum.toTypeDtoEnum(): TypeDtoEnum {

    return TypeDtoEnum.valueOf(this.name)
}

fun TypeDtoEnum.toTypeEnum(): TypeEnum {

    return TypeEnum.valueOf(this.name)
}

fun GameScore.toGameScoreDto(): GameScoreDto {

    return GameScoreDto(
        id = this.id,
        category = this.category.toCategoryDtoEnum(),
        difficulty = this.difficulty.toDifficultyDtoEnum(),
        type = this.type.toTypeDtoEnum(),
        correctAnswers = this.correctAnswers,
        totalOfQuestions = this.totalOfQuestions,
    )
}

fun GameScoreDto.toGameScore(): GameScore {

    return GameScore(
        id = this.id,
        category = this.category.toCategoryEnum(),
        difficulty = this.difficulty.toDifficultyEnum(),
        type = this.type.toTypeEnum(),
        correctAnswers = this.correctAnswers,
        totalOfQuestions = this.totalOfQuestions,
    )
}


