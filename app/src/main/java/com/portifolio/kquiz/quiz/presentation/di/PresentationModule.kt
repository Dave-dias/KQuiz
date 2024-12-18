package com.portifolio.kquiz.quiz.presentation.di

import com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.helper.TimerManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val TIME_TO_ANSWER: Int = 40
private const val TICK_INTERVAL: Int = 1

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideTimerManager() = TimerManager(TIME_TO_ANSWER, TICK_INTERVAL)
}