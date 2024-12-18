package com.portifolio.kquiz.quiz.data.web.service

import com.portifolio.kquiz.R
import com.portifolio.kquiz.core.providers.StringProvider
import com.portifolio.kquiz.quiz.data.core.toCategoryDtoEnum
import com.portifolio.kquiz.quiz.data.core.toDifficultyDtoEnum
import com.portifolio.kquiz.quiz.data.core.toQuestion
import com.portifolio.kquiz.quiz.data.core.toTypeDtoEnum
import com.portifolio.kquiz.quiz.data.web.IQuestionApi
import com.portifolio.kquiz.quiz.data.web.model.ResultDtoWrapper
import com.portifolio.kquiz.quiz.domain.model.CategoryEnum
import com.portifolio.kquiz.quiz.domain.model.DifficultyEnum
import com.portifolio.kquiz.quiz.domain.model.Question
import com.portifolio.kquiz.quiz.domain.model.TypeEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

private const val DEFAULT_STRING_ENCODE = "url3986"

class QuestionService(
    private val questionApi: IQuestionApi,
    private val stringProvider: StringProvider
) {

    suspend fun getQuestions(
        amount: Int,
        category: CategoryEnum,
        type: TypeEnum,
        difficulty: DifficultyEnum
    ): Result<List<Question>> = withContext(Dispatchers.IO) {

        try {

            val resultWrapper = questionApi.getQuestions(
                encode = DEFAULT_STRING_ENCODE,
                amount = amount.toString(),
                category = category.toCategoryDtoEnum().id,
                type = type.toTypeDtoEnum().description,
                difficulty = difficulty.toDifficultyDtoEnum().description
            )

            return@withContext if (resultWrapper.response_code == 0) {

                Result.success(
                    resultWrapper.results.map {
                        it.toQuestion()
                    }
                )
            } else {

                Result.failure(Exception(getErrorDescriptionFromResult(resultWrapper)))
            }

        } catch (e: HttpException) {

            return@withContext Result.failure(Exception(getErrorDescriptionFromHttpExeption(e)))

        } catch (e: Exception) {

            return@withContext Result.failure(e)
        }
    }

    private fun getErrorDescriptionFromResult(resultWrapper: ResultDtoWrapper) =
        stringProvider.getString(
            when (resultWrapper.response_code) {
                1 -> R.string.no_questions_were_found_please_try_changing_the_parameters
                2 -> R.string.an_invalid_parameter_was_specified
                3 -> R.string.token_not_found
                4 -> R.string.there_are_no_more_new_to_be_answered_please_try_changing_the_parameters
                5 -> R.string.too_many_requests_to_the_server_please_try_again_later
                else -> R.string.unknown_error
            }
        )

    private fun getErrorDescriptionFromHttpExeption(exception: HttpException) =
        when (exception.code()) {
            404 -> stringProvider.getString(R.string.route_was_not_found)
            429 -> stringProvider.getString(R.string.too_many_requests_to_the_server_please_try_again_later)
            else -> exception.message()
        }

}