package com.ahmedorabi.weatherapp.features.home_screen



import android.app.Activity
import android.content.Context
import android.content.IntentSender
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

@Composable
fun EnableGpsButton(activity: Activity) {

    // Create Location Settings Request
    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        1000L // 1-second interval (can be adjusted)
    ).build()

    val locationSettingsRequest = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
        .setAlwaysShow(true) // Ensures the dialog is always shown
        .build()

    // GPS Request Launcher
    val locationRequestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
      //  gpsEnabled = result.resultCode == Activity.RESULT_OK
    }

    val client = LocationServices.getSettingsClient(activity)
    val task = client.checkLocationSettings(locationSettingsRequest)

    task.addOnSuccessListener {
        // GPS is already enabled
       // gpsEnabled = true
    }

    task.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            try {
                val intentSenderRequest = IntentSenderRequest.Builder(exception.resolution).build()
                locationRequestLauncher.launch(intentSenderRequest)
            } catch (sendEx: IntentSender.SendIntentException) {
                sendEx.printStackTrace()
            }
        }
    }

//    Button(onClick = {
//
//    }) {
//        Text(text = if (gpsEnabled) "GPS Enabled" else "Enable GPS")
//    }
}