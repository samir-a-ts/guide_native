package com.ludev.guideproject.core.domain.di

import android.app.Application
import android.content.Context
import com.ludev.guideproject.features.intro.data.LaunchRepository
import com.ludev.guideproject.features.intro.domain.ILaunchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object Injection {
    @Provides
    fun provideLaunchRepository(application: Application): ILaunchRepository {
        return LaunchRepository(
            preferences = application.getSharedPreferences("guide_app", Context.MODE_PRIVATE)
        )
    }
}