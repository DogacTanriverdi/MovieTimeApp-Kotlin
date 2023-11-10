package com.dogactnrvrdi.movietime.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.common.Constants.BASE_URL
import com.dogactnrvrdi.movietime.data.source.local.MovieDao
import com.dogactnrvrdi.movietime.data.source.local.MovieDatabase
import com.dogactnrvrdi.movietime.data.repo.MovieRepositoryImpl
import com.dogactnrvrdi.movietime.data.source.remote.MovieApi
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
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

    /*
    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context) = ChuckerInterceptor.Builder(context).build()

    @Provides
    @Singleton
    fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor) = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                return@Interceptor chain.proceed(builder.build())
            }
        )
        addInterceptor(chuckerInterceptor)
        readTimeout(60, TimeUnit.SECONDS)
        connectTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
    }.build()

     */

    @Singleton
    @Provides
    fun provieMovieApi(): MovieApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: MovieApi,
        dao: MovieDao
    ): MovieRepository = MovieRepositoryImpl(api, dao)

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.color.grey)
                .error(R.color.hint_grey)
        )

    @Singleton
    @Provides
    fun provideMovieDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "movieDb"
    ).allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.getMovieDao()
}