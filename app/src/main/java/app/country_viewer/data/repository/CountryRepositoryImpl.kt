package app.country_viewer.data.repository

import app.country_viewer.data.api.CountryApi
import app.country_viewer.data.model.Country
import app.country_viewer.data.model.toCountry
import app.country_viewer.domain.repository.CountryRepository

class CountryRepositoryImpl(
    private val api: CountryApi
) : CountryRepository {
    override suspend fun getCountry(name: String): Country {
        val result = api.getCountry(name)
        return result.first().toCountry()
    }
}