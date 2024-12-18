package com.portifolio.kquiz.quiz.domain.di

import com.portifolio.kquiz.quiz.data.db.repository.GameScoreRepository
import com.portifolio.kquiz.quiz.data.web.service.QuestionService
import com.portifolio.kquiz.quiz.domain.use_case.GetQuestionsUseCase
import com.portifolio.kquiz.quiz.domain.use_case.GetRecentGameScoresUseCase
import com.portifolio.kquiz.quiz.domain.use_case.SaveGameScoreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetQuestionsUseCase(
        questionService: QuestionService
    ) = GetQuestionsUseCase(questionService)

    @Provides
    fun provideGetRecentGameScoresUseCase(
        gameScoreRepository: GameScoreRepository
    ) = GetRecentGameScoresUseCase(gameScoreRepository)

    @Provides
    fun provideSaveGameScoreUseCase(
        gameScoreRepository: GameScoreRepository
    ) = SaveGameScoreUseCase(gameScoreRepository)
}