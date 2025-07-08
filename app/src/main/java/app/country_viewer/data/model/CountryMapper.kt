package app.country_viewer.data.model

fun CountryDto.toCountry(): Country {
    return Country(
        name = name.common,
        population = population,
        flagUrl = flags.png,
        languages = languages?.values?.toList() ?: emptyList(),
        currencies = currencies?.values?.map { it.name } ?: emptyList(),
        region = region,
        subregion = subregion ?: "N/A"
    )
}