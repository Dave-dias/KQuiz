package com.portifolio.kquiz.quiz.data.di

import android.content.Context
import com.portifolio.kquiz.core.providers.impl.AndroidStringProvider
import com.portifolio.kquiz.quiz.data.db.KQuizDataBase
import com.portifolio.kquiz.quiz.data.db.repository.GameScoreRepository
import com.portifolio.kquiz.quiz.data.db.repository.dao.GameScoreDao
import com.portifolio.kquiz.quiz.data.web.IQuestionApi
import com.portifolio.kquiz.quiz.data.web.service.QuestionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_TRIVIA_URL = "https://opentdb.com/"

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_TRIVIA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): KQuizDataBase {

        return KQuizDataBase.buildDatabase(context)
    }

    @Provides
    fun provideGameScoreDao(dataBase: KQuizDataBase) = dataBase.gameScoreDao()

    @Provides
    fun provideGameScoreRepository(gameScoreDao: GameScoreDao) = GameScoreRepository(gameScoreDao)

    @Provides
    fun provideQuestionApi(retrofit: Retrofit): IQuestionApi = retrofit.create(IQuestionApi::class.java)

    @Provides
    fun provideQuestionService(
        questionApi: IQuestionApi,
        stringProvider: AndroidStringProvider
    ): QuestionService {

        return QuestionService(
            questionApi = questionApi,
            stringProvider = stringProvider
        )
    }
}