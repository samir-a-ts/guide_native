package com.ludev.guideproject.features.app.domain.di

import com.ludev.guideproject.features.app.presentation.activities.MainActivity
import com.ludev.guideproject.features.places_list.domain.PlacesListRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Component(
    modules = [
        PlacesListModule::class,
    ]
)
interface MainComponent {
    fun initialize(activity: MainActivity)

    fun placesList(): PlacesListRepository
}

@Module
class PlacesListModule {
    @Provides
    fun bindRepository(): PlacesListRepository {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://test-backend-flutter.surfstudio.ru")
            .build()

        return retrofit.create(PlacesListRepository::class.java)
    }
}