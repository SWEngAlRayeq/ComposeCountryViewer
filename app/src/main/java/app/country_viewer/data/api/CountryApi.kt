package app.country_viewer.data.api

import app.country_viewer.data.model.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("v3.1/name/{country}")
    suspend fun getCountry(@Path("country") country: String): List<CountryDto>

}