package app.country_viewer.data.model

data class Country(
    val name: String,
    val population: Long,
    val flagUrl: String,
    val languages: List<String>,
    val currencies: List<String>,
    val region: String,
    val subregion: String
)