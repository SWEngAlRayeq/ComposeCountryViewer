package app.country_viewer.domain.usecase

import app.country_viewer.data.model.Country
import app.country_viewer.domain.repository.CountryRepository

class GetCountryInfoUseCase(private val repository: CountryRepository) {

    suspend operator fun invoke(name: String): Country = repository.getCountry(name)

}