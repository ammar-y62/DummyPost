package com.example.postapost.di

import android.content.Context
import com.example.postapost.data.repositories.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object module {


    @Singleton
    @Provides
    fun provideNewsRepository(
        @ApplicationContext context: Context
    ): PostsRepository = PostsRepository(context)


}