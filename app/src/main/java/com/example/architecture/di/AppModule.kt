package com.example.architecture.di

import com.example.architecture.data.remote.api.AnimalApi
import com.example.architecture.data.remote.api.Const
import com.example.architecture.data.repository.AnimalRepositoryImpl
import com.example.architecture.domain.repository.AnimalRepository
import com.example.architecture.domain.use_case.GetAnimalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAnimalApi(retrofit: Retrofit): AnimalApi {
        return retrofit.create(AnimalApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAnimalRepository(api: AnimalApi): AnimalRepository {
        return AnimalRepositoryImpl(api)
    }

    @Provides
    fun providesAnimalUseCase(repository: AnimalRepository): GetAnimalUseCase {
        return GetAnimalUseCase(repository)
    }

}