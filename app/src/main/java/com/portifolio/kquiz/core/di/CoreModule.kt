package com.portifolio.kquiz.core.di

import android.content.Context
import com.portifolio.kquiz.core.providers.impl.AndroidStringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideAndroidStringProvider(
        @ApplicationContext context: Context
    ): AndroidStringProvider {
        return AndroidStringProvider(context)
    }
}