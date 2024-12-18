package com.portifolio.kquiz.quiz.data.web

import com.portifolio.kquiz.quiz.data.web.model.ResultDtoWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface IQuestionApi {

    @GET("/api.php")
    suspend fun getQuestions(
        @Query("amount") amount: String,
        @Query("category") category: Int,
        @Query("type") type: String,
        @Query("difficulty") difficulty: String,
        @Query("encode") encode: String
    ) : ResultDtoWrapper
}