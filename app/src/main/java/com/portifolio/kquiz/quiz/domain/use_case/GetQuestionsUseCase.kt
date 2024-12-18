package com.portifolio.kquiz.quiz.domain.use_case

import com.portifolio.kquiz.quiz.data.web.service.QuestionService
import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.Question
import com.portifolio.kquiz.quiz.domain.model.TypeEnum
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val questionService: QuestionService,
) {

    suspend operator fun invoke(
        amount: Int,
        category: CategoryEnum,
        type: TypeEnum,
        difficulty: DifficultyEnum
    ): Result<List<Question>> {

        return questionService.getQuestions(
            amount,
            category,
            type,
            difficulty
        )
    }
}