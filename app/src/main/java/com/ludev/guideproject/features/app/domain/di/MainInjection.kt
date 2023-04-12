package com.ludev.guideproject.features.app.domain.di

import com.ludev.guideproject.core.domain.di.RetrofitModule
import com.ludev.guideproject.features.places_list.domain.PlacesListRepository
import com.ludev.guideproject.features.places_list.presentation.state.PlacesListViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Component(
    modules = [
        RetrofitModule::class,
        PlacesListModule::class,
    ]
)
interface MainComponent {
    fun initialize(placesListViewModel: PlacesListViewModel)

    fun placesList(): PlacesListRepository
}

@Module
class PlacesListModule {
    @Provides
    fun bindRepository(retrofit: Retrofit): PlacesListRepository {
        return retrofit.create(PlacesListRepository::class.java)
    }
}