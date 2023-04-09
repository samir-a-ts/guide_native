package com.ludev.guideproject.core.domain.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides


@Module
class ApplicationContextModule(private val context: Context) {
    @Provides
    fun context(): Context {return context}
}

@Module
class SharedPreferencesModule {
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("app_cache", Context.MODE_PRIVATE)
    }
}