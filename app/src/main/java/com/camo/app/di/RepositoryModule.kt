package com.camo.app.di

import android.content.Context
import com.camo.app.repository.ProfileRepository
import com.camo.app.repository.TimelineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTimelineRepository(
        @ApplicationContext context: Context
    ): TimelineRepository {
        return TimelineRepository(context)
    }

    @Singleton
    @Provides
    fun provideProfileRepository(
        @ApplicationContext context: Context
    ): ProfileRepository {
        return ProfileRepository(context)
    }

}