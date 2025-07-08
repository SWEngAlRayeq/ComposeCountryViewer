package app.country_viewer.domain.repository

import app.country_viewer.data.model.Country

interface CountryRepository {

    suspend fun getCountry(name: String): Country

}