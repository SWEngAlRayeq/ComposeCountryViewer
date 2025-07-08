package app.country_viewer.presentation.country

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.country_viewer.data.model.Country
import app.country_viewer.domain.usecase.GetCountryInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryInfoUseCase
) : ViewModel() {

    var country by mutableStateOf<Country?>(null)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun fetchCountryInfo(name: String) {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                country = getCountryUseCase(name)
            } catch (e: Exception) {
                error = "Failed to load country info: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

}