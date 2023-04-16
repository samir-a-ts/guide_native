package com.ludev.guideproject.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ludev.guideproject.features.places_list.domain.PlacesListApi
import com.ludev.guideproject.features.places_list.domain.PlacesListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@HiltAndroidApp
class GuideApplication : Application()

@Module
@InstallIn(ActivityComponent::class)
class SharedPreferencesModule {
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_cache", Context.MODE_PRIVATE)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://test-backend-flutter.surfstudio.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePlacesListApi(retrofit: Retrofit): PlacesListApi {
        return retrofit.create(PlacesListApi::class.java)
    }

    @Provides
    fun providePlacesListRepository(placesListApi: PlacesListApi): PlacesListRepository {
        return PlacesListRepository(placesListApi)
    }
}
