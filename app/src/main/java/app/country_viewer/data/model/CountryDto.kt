package app.country_viewer.data.model

data class CountryDto(
    val name: NameDto,
    val population: Long,
    val flags: FlagsDto,
    val languages: Map<String, String>?,
    val currencies: Map<String, CurrencyDto>?,
    val region: String,
    val subregion: String?
)

data class NameDto(val common: String)
data class FlagsDto(val png: String)
data class CurrencyDto(val name: String)