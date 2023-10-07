package com.dogactnrvrdi.movietime.di

import com.dogactnrvrdi.movietime.remote.IMovieApi
import com.dogactnrvrdi.movietime.repo.IMovieRepository
import com.dogactnrvrdi.movietime.repo.MovieRepositoryImpl
import com.dogactnrvrdi.movietime.util.Constants.BASE_URL
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
    fun provieMovieApi(): IMovieApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMovieApi::class.java)

    @Singleton
    @Provides
    fun provideMovieRepository(api: IMovieApi) : IMovieRepository = MovieRepositoryImpl(api)

}