package com.portifolio.kquiz.core.di

import android.content.Context
import com.portifolio.kquiz.core.providers.StringProvider
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
    fun provideStringProvider(
        @ApplicationContext context: Context
    ): StringProvider {
        return AndroidStringProvider(context)
    }
}