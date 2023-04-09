package com.ludev.guideproject.features.intro.domain.di

import android.content.Context
import com.ludev.guideproject.core.domain.di.ApplicationContextModule
import com.ludev.guideproject.core.domain.di.SharedPreferencesModule
import com.ludev.guideproject.core.presentation.activities.SplashActivity
import dagger.Component

@Component(
    modules = [
        ApplicationContextModule::class,
        SharedPreferencesModule::class,
    ]
)
interface SplashComponent {
    fun context(): Context

    fun initialize(app: SplashActivity)
}