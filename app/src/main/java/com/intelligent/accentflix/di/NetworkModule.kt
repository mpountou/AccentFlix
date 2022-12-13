package com.intelligent.accentflix.di



import com.intelligent.accentflix.data.remote.api.RemoteApiInterface
import com.intelligent.accentflix.data.remote.repositories.RemoteMovieRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Moshi
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
                                        .add(KotlinJsonAdapterFactory())
                                        .build()

    private const val BASE_URL = "https://imdb-api.com/API/"
    // Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
                                                            .addConverterFactory(MoshiConverterFactory.create(moshi))
                                                            .baseUrl(BASE_URL)
                                                            .build()
    // Imdb Remote Api Interface
    @Singleton
    @Provides
    fun provideRemoteApiInterface(retrofit: Retrofit): RemoteApiInterface {
        return retrofit.create(RemoteApiInterface::class.java)
    }

    // Imdb Remote Repository
    @Singleton
    @Provides
    fun provideRemoteMovieRepository(
        imdbApiInterface: RemoteApiInterface,
    ): RemoteMovieRepository {
        return RemoteMovieRepository(imdbApiInterface)
    }

}