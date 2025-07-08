package app.country_viewer.di

import app.country_viewer.data.api.CountryApi
import app.country_viewer.data.repository.CountryRepositoryImpl
import app.country_viewer.domain.repository.CountryRepository
import app.country_viewer.domain.usecase.GetCountryInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://restcountries.com/"

    @Provides
    @Singleton
    fun provideApi(): CountryApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryApi::class.java)


    @Provides
    @Singleton
    fun provideRepository(api: CountryApi): CountryRepository = CountryRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetCountryUseCase(repository: CountryRepository): GetCountryInfoUseCase =
        GetCountryInfoUseCase(repository)

}