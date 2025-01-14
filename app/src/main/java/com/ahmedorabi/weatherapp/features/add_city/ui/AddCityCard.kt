package com.ahmedorabi.weatherapp.features.add_city.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmedorabi.weatherapp.R
import com.ahmedorabi.weatherapp.features.home_screen.EnableGpsButton
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import timber.log.Timber
import java.util.Locale

@SuppressLint("MissingPermission")
@Composable
fun AddCityCard(
    modifier: Modifier = Modifier,
    cityName: MutableState<String>,
    searchCity : (String) -> Unit
) {


    val context = LocalContext.current

    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    // var locationText by remember { mutableStateOf("Fetching location...") }
    // var cityName by remember { mutableStateOf("Fetching city...") }


    // Permission request launcher
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Get current location

            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                    Timber.e("subAdminArea " + addresses?.firstOrNull()?.subAdminArea)
                    Timber.e("addresses " + addresses?.firstOrNull()?.toString())

                    cityName.value = if (!addresses.isNullOrEmpty()) {
                        addresses[0].subAdminArea ?: "City not found"
                    } else {
                        "Enter City name"
                    }
                    searchCity.invoke(cityName.value)
                    //  locationText = "Lat: ${it.latitude}, Lng: ${it.longitude}"
                    Timber.e("cityName " +  cityName.value)
                } ?: run {
                    // locationText = "Location not available"

                    cityName.value = "Enter City name"
                    Timber.e("cityName " +  cityName.value)
                }
            }
        } else {
            cityName.value = "Enter City name"
            Timber.e("locationText " +  cityName.value)

        }
    }

    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }



    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(2.dp)
        ) {

            IconButton(onClick = {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Refresh",
                    tint = MaterialTheme.colorScheme.primary // Optional: customize color
                )
            }

            Spacer(Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value =  cityName.value,
            onValueChange = {  cityName.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 4.dp),
            placeholder = { Text(text = "Add City") },
            textStyle = TextStyle(
                color = colorResource(id = R.color.black),
                fontSize = 17.sp,
                fontFamily = FontFamily.SansSerif
            )
        )

        Spacer(modifier = Modifier.height(10.dp))


        Button(
            onClick = {
                searchCity.invoke(cityName.value)
            },
            modifier = Modifier
                .width(100.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(
                text = "Search",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun EnableLocation(context: Context){
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)


}