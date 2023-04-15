package com.ludev.guideproject.core.domain.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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

object RetrofitLibraryProvider {
    private val baseUrl = "https://test-backend-flutter.surfstudio.ru"

    private var retrofit: Retrofit? = null

    @Synchronized
    fun getInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!
    }
}


@Module
class RetrofitModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitLibraryProvider.getInstance()
    }
}