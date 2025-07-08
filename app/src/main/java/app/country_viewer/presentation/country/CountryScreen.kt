package app.country_viewer.presentation.country

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.country_viewer.data.model.Country
import coil.compose.AsyncImage

@Composable
fun CountryScreen(viewModel: CountryViewModel = hiltViewModel()) {
    val country = viewModel.country
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    var countryInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 36.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = countryInput,
            onValueChange = { countryInput = it },
            label = { Text("🌐 Enter Country Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (countryInput.isNotBlank()) {
                    viewModel.fetchCountryInfo(countryInput.trim())
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🔍 Get Country Info")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when {
            isLoading -> CircularProgressIndicator()
            error != null -> Text(error, color = Color.Red)
            country != null -> CountryInfoCard(country)
        }
    }
}

@Composable
fun CountryInfoCard(country: Country) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = country.flagUrl,
                contentDescription = "Country Flag",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Text("🏳️ Country: ${country.name}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("🗣️ Languages: ${country.languages.joinToString()}", fontSize = 16.sp)
            Text("💰 Currencies: ${country.currencies.joinToString()}", fontSize = 16.sp)
            Text("👥 Population: ${country.population}", fontSize = 16.sp)
            Text("🌍 Region: ${country.region}", fontSize = 16.sp)
            Text("🌎 Subregion: ${country.subregion}", fontSize = 16.sp)
        }
    }
}
