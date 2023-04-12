package com.ludev.guideproject.core.domain.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


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

@Module
class RetrofitModule {
    private val baseUrl = "https://test-backend-flutter.surfstudio.ru";
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()
    }
}