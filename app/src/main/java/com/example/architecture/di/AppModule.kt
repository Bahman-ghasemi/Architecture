package com.example.architecture.di

import com.example.architecture.model.remote.AnimalApi
import com.example.architecture.model.remote.Const
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
}