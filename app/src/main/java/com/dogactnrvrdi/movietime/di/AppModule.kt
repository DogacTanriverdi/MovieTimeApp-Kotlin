package com.dogactnrvrdi.movietime.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.remote.IMovieApi
import com.dogactnrvrdi.movietime.repo.IMovieRepository
import com.dogactnrvrdi.movietime.repo.MovieRepositoryImpl
import com.dogactnrvrdi.movietime.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.color.grey)
                .error(R.color.hint_grey)
        )
}